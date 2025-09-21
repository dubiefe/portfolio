import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PresentationOrale {

    private static ArrayList<Depeche> lectureDepeches(String nomFichier) {
        //creation d'un tableau de dépêches
        ArrayList<Depeche> depeches = new ArrayList<>();
        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String id = ligne.substring(3);
                ligne = scanner.nextLine();
                String date = ligne.substring(3);
                ligne = scanner.nextLine();
                String categorie = ligne.substring(3);
                ligne = scanner.nextLine();
                String lignes = ligne.substring(3);
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        lignes = lignes + '\n' + ligne;
                    }
                }
                Depeche uneDepeche = new Depeche(id, date, categorie, lignes);
                depeches.add(uneDepeche);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depeches;
    }


    public static void classementDepechesOral(ArrayList<Depeche> depeches, ArrayList<Categorie> categories) {
        ArrayList<PaireChaineEntier> catTotal = new ArrayList<>();
        for (int a = 0; a < categories.size(); a++) {
            catTotal.add(new PaireChaineEntier(categories.get(a).getNom(), 0));
        } //catTotal contient le nom de chaque catégories avec un compteur
        Classification.triBulleAmeliore(catTotal); //tri pour utiliser la recherche dichotomique
        int j = 0;
        while (j < depeches.size()) { //pour chaque dépêche
            int i = 0;
            ArrayList<PaireChaineEntier> scores = new ArrayList<>(); //enregistrement des scores de chaque dépêche
            while (i < categories.size()) {
                scores.add(new PaireChaineEntier(categories.get(i).getNom(), categories.get(i).score(depeches.get(j))));
                i++;
            } //score contient le score pour chaque catégories pour la dépêche
            String categorieMax = UtilitairePaireChaineEntier.chaineMax(scores); //catégorie de la dépêche selon notre lexique
            if (depeches.get(j).getCategorie().compareTo(categorieMax) == 0) { //si la catégorie trouvée est la catégorie d'origine alors on incrémente
                int indice = UtilitairePaireChaineEntier.indicePourChaineTri(catTotal, categorieMax);
                catTotal.set(indice, new PaireChaineEntier(catTotal.get(indice).getChaine(), catTotal.get(indice).getEntier() + 1));
            } //incrémentation du compteur de catTotal pour la bonne catégorie
            j++;
        } //id et catégorie principale pour chaque dépêche
        for (int b = 0; b < catTotal.size(); b++) {
            System.out.println((String.format("%-30s", catTotal.get(b).getChaine()) + String.format( "%10s" ,catTotal.get(b).getEntier()) + "%"));
        } //impression du taux de réussite pour chaque catégorie
        System.out.println((String.format("%-30s" ,  "MOYENNE") + String.format("%10s" , UtilitairePaireChaineEntier.moyenne(catTotal)) + "%")); //impression de la moyenne de réussite globale
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //Chargement des dépêches en mémoire
        System.out.println("chargement des dépêches");
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");
        for (int i = 0; i < depeches.size(); i++) {
            depeches.get(i).afficher();
        }

        //déclaration des catégories
        Categorie environnementScience = new Categorie("ENVIRONNEMENT-SCIENCES");
        Categorie culture = new Categorie("CULTURE");
        Categorie economie = new Categorie("ECONOMIE");
        Categorie politique = new Categorie("POLITIQUE");
        Categorie sport = new Categorie("SPORTS");

        //arraylist de catégories
        ArrayList<Categorie> categories = new ArrayList<>(Arrays.asList(environnementScience, culture, economie, politique, sport));
        long middleTime = System.currentTimeMillis();

        long startTimeManuel = System.currentTimeMillis();
        System.out.println();
        System.out.println("-----------------------------------------------------");
        System.out.println("Classification des dépêches avec les lexiques manuels");
        System.out.println("-----------------------------------------------------");
        System.out.println();
        // Test pour les fichiers manuels
        depeches = lectureDepeches("./test.txt");
        environnementScience.initLexique("./Environnement-Sciences2.0.txt");
        Classification.triBulleAmeliore(environnementScience.getLexique());
        culture.initLexique("./Culture2.0.txt");
        Classification.triBulleAmeliore(culture.getLexique());
        economie.initLexique("./Economie3.0.txt");
        Classification.triBulleAmeliore(economie.getLexique());
        politique.initLexique("./Politique3.0.txt");
        Classification.triBulleAmeliore(politique.getLexique());
        sport.initLexique("./Sport2.0.txt");
        Classification.triBulleAmeliore(sport.getLexique());
        classementDepechesOral(depeches, categories);
        long endTimeManuel = System.currentTimeMillis();

        System.out.println();
        System.out.println("La classification des dépêches avec les lexiques manuels prend " + ((middleTime-startTime) + (endTimeManuel-startTimeManuel)) + "ms");

        long startTimeAuto = System.currentTimeMillis();
        System.out.println();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Génération des lexiques automatiques et classification des dépêches");
        System.out.println("-------------------------------------------------------------------");
        System.out.println();

        depeches = lectureDepeches("./depeches.txt");
        Classification.generationLexique(depeches, "ENVIRONNEMENT-SCIENCES", "./Environnement-Sciences-Auto.txt");
        Classification.generationLexique(depeches, "CULTURE", "./Culture-Auto.txt");
        Classification.generationLexique(depeches, "ECONOMIE", "./Economie-Auto.txt");
        Classification.generationLexique(depeches, "POLITIQUE", "./Politique-Auto.txt");
        Classification.generationLexique(depeches, "SPORTS", "./Sport-Auto.txt");


        environnementScience.initLexique("./Environnement-Sciences-Auto.txt");
        culture.initLexique("./Culture-Auto.txt");
        economie.initLexique("./Economie-Auto.txt");
        politique.initLexique("./Politique-Auto.txt");
        sport.initLexique("./Sport-Auto.txt");

        depeches = lectureDepeches("./test.txt");
        classementDepechesOral(depeches, categories);
        long endTimeAuto = System.currentTimeMillis();
        System.out.println();
        System.out.println("La génération des lexiques automatiques et la classification des dépêches prend " +
                ((middleTime-startTime) + (endTimeAuto-startTimeAuto)) + "ms");
    }
}
