import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Classification {


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


    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        ArrayList<PaireChaineEntier> catTotal = new ArrayList<>();
        for (int a = 0; a < categories.size(); a++) {
            catTotal.add(new PaireChaineEntier(categories.get(a).getNom(), 0));
        } //catTotal contient le nom de chaque catégories avec un compteur
        triBulleAmeliore(catTotal); //tri pour utiliser la recherche dichotomique
        try {
            FileWriter file = new FileWriter(nomFichier); //création du fichier txt
            int j = 0;
            while (j < depeches.size()) { //pour chaque dépêche
                int i = 0;
                ArrayList<PaireChaineEntier> scores = new ArrayList<>(); //enregistrement des scores de chaque dépêche
                while (i < categories.size()) {
                    scores.add(new PaireChaineEntier(categories.get(i).getNom(), categories.get(i).score(depeches.get(j))));
                    i++;
                } //score contient le score pour chaque catégories pour la dépêche
                String categorieMax = UtilitairePaireChaineEntier.chaineMax(scores); //catégorie de la dépêche selon notre lexique
                file.write(depeches.get(j).getId() + ":" + categorieMax + "\n"); //impression dans le fichier txt
                if (depeches.get(j).getCategorie().compareTo(categorieMax) == 0) { //si la catégorie trouvée est la catégorie d'origine alors on incrémente
                    int indice = UtilitairePaireChaineEntier.indicePourChaineTri(catTotal, categorieMax);
                    catTotal.set(indice, new PaireChaineEntier(catTotal.get(indice).getChaine(), catTotal.get(indice).getEntier() + 1));
                } //incrémentation du compteur de catTotal pour la bonne catégorie
                j++;
            } //id et catégorie principale pour chaque dépêche
            for (int b = 0; b < catTotal.size(); b++) {
                file.write(String.format("%-30s", catTotal.get(b).getChaine()) + String.format( "%10s" ,catTotal.get(b).getEntier()) + "%" + "\n");
            } //impression du taux de réussite pour chaque catégorie
            file.write(String.format("%-30s" ,  "MOYENNE") + String.format("%10s" , UtilitairePaireChaineEntier.moyenne(catTotal)) + "%"); //impression de la moyenne de réussite globale
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void classementDepechesCompteur(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        int compteur = 0;
        ArrayList<PaireChaineEntier> catTotal = new ArrayList<>();
        for (int a = 0; a < categories.size(); a++) {
            catTotal.add(new PaireChaineEntier(categories.get(a).getNom(), 0));
        } //catTotal contient le nom de chaque catégories avec un compteur
        triBulleAmeliore(catTotal); //tri pour utiliser la recherche dichotomique
        try {
            FileWriter file = new FileWriter(nomFichier); //création du fichier txt
            int j = 0;
            while (j < depeches.size()) { //pour chaque dépêche
                int i = 0;
                ArrayList<PaireChaineEntier> scores = new ArrayList<>(); //enregistrement des scores de chaque dépêche
                while (i < categories.size()) {
                    scores.add(new PaireChaineEntier(categories.get(i).getNom(), categories.get(i).scoreCompteur(depeches.get(j)).getResultat()));
                    compteur = compteur + categories.get(i).scoreCompteur(depeches.get(j)).getCompteur();
                    i++;
                } //score contient le score pour chaque catégories pour la dépêche
                String categorieMax = UtilitairePaireChaineEntier.chaineMax(scores); //catégorie de la dépêche selon notre lexique
                file.write(depeches.get(j).getId() + ":" + categorieMax + "\n"); //impression dans le fichier txt
                if (depeches.get(j).getCategorie().compareTo(categorieMax) == 0) { //si la catégorie trouvée est la catégorie d'origine alors on incrémente
                    int indice = UtilitairePaireChaineEntier.indicePourChaineTri(catTotal, categorieMax);
                    catTotal.set(indice, new PaireChaineEntier(catTotal.get(indice).getChaine(), catTotal.get(indice).getEntier() + 1));
                } //incrémentation du compteur de catTotal pour la bonne catégorie
                j++;
            } //id et catégorie principale pour chaque dépêche
            for (int b = 0; b < catTotal.size(); b++) {
                file.write(String.format("%-30s", catTotal.get(b).getChaine()) + String.format( "%10s" ,catTotal.get(b).getEntier()) + "%" + "\n");
            } //impression du taux de réussite pour chaque catégorie
            file.write(String.format("%-30s" ,  "MOYENNE") + String.format("%10s" , UtilitairePaireChaineEntier.moyenne(catTotal)) + "%"); //impression de la moyenne de réussite globale
            file.close();
            System.out.println("Le classement a fait " + compteur + " comparaisons !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();
        int i = 0;
        while (i < depeches.size()) { //pour chaque dépêche
            if (depeches.get(i).getCategorie().compareTo(categorie) == 0) {
                int j = 0;
                while (j < depeches.get(i).getMots().size()) { //pour chaque mot de la dépêche à l'indice i
                    String mot = depeches.get(i).getMots().get(j);
                    if (UtilitairePaireChaineEntier.indicePourChaineTri(resultat, mot) == -1 && mot.length() > 2) {
                        resultat.add(new PaireChaineEntier(mot, 0));
                        triInsertionDerniereValeur(resultat);
                    }
                    j++;
                } //chaque mot de la dépêche est ajouté à resultat si il n'est pas présent dans résultat
            }
            i++;
        }
        return resultat;

    }

    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        int i = 0;
        while (i < depeches.size()) { //pour chaque dépêche
            int j = 0;
            while (j < depeches.get(i).getMots().size()) { //pour chaque mot de la dépêche
                String mot = depeches.get(i).getMots().get(j);
                if (UtilitairePaireChaineEntier.indicePourChaineTri(dictionnaire, mot) != -1) { //vérification si mot est dans le dictionnaire
                    int indiceAModifier = UtilitairePaireChaineEntier.indicePourChaineTri(dictionnaire, mot);
                    if (depeches.get(i).getCategorie().compareTo(categorie) == 0) { //incrémentation si même cat
                        dictionnaire.set(indiceAModifier, new PaireChaineEntier(dictionnaire.get(indiceAModifier).getChaine(), dictionnaire.get(indiceAModifier).getEntier() + 1));
                    } else { //décrémentation si cat différente
                        dictionnaire.set(indiceAModifier, new PaireChaineEntier(dictionnaire.get(indiceAModifier).getChaine(), dictionnaire.get(indiceAModifier).getEntier() - 1));
                    }
                }
                j++;
            }
            i++;
        }
    }

    public static int calculScoresCompteur(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        int compteur = 0;
        int i = 0;
        while (i < depeches.size()) { //pour chaque dépêche
            int j = 0;
            while (j < depeches.get(i).getMots().size()) { //pour chaque mot de la dépêche
                String mot = depeches.get(i).getMots().get(j);
                if (UtilitairePaireChaineEntier.indicePourChaineTriCompteur(dictionnaire, mot).getResultat() != -1) { //vérification si mot est dans le dictionnaire
                    int indiceAModifier = UtilitairePaireChaineEntier.indicePourChaineTriCompteur(dictionnaire, mot).getResultat();
                    if (depeches.get(i).getCategorie().compareTo(categorie) == 0) { //incrémentation si même cat
                        dictionnaire.set(indiceAModifier, new PaireChaineEntier(dictionnaire.get(indiceAModifier).getChaine(), dictionnaire.get(indiceAModifier).getEntier() + 1));
                    } else { //décrémentation si cat différente
                        dictionnaire.set(indiceAModifier, new PaireChaineEntier(dictionnaire.get(indiceAModifier).getChaine(), dictionnaire.get(indiceAModifier).getEntier() - 1));
                    }
                    compteur++;
                }
                compteur = compteur + UtilitairePaireChaineEntier.indicePourChaineTriCompteur(dictionnaire, mot).getCompteur();
                j++;
            }
            i++;
        }
        return compteur;
    }

    public static int poidsPourScore(int score) {
        if (score <= 0) {
            return 0;
        } else if (score >= 12){
            return 3;
        } else if (score >= 8){
            return 2;
        } else {
            return 1;
        }
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {
        ArrayList<PaireChaineEntier> dico = initDico(depeches, categorie); //création du dictionnaire pour categorie
        calculScores(depeches, categorie, dico); //calcul des scores de dico
        int i = 0;
        try {
            FileWriter file = new FileWriter(nomFichier); //création du fichier txt
            while (i < dico.size()) { //pour chaque élément de dico
                int score = poidsPourScore(dico.get(i).getEntier());
                if (score > 0) {
                    file.write(dico.get(i).getChaine() + ":" + score + "\n");
                }
                i++;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generationLexiqueCompteur(ArrayList<Depeche> depeches, String categorie, String nomFichier) {
        int compteur = 0;
        ArrayList<PaireChaineEntier> dico = initDico(depeches, categorie); //création du dictionnaire pour categorie
        calculScoresCompteur(depeches, categorie, dico); //calcul des scores de dico
        compteur = compteur + calculScoresCompteur(depeches, categorie, dico);
        int i = 0;
        try {
            FileWriter file = new FileWriter(nomFichier); //création du fichier txt
            while (i < dico.size()) { //pour chaque élément de dico
                int score = poidsPourScore(dico.get(i).getEntier());
                if (score > 0) {
                    file.write(dico.get(i).getChaine() + ":" + score + "\n");
                }
                i++;
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("La génération du lexique de la catégorie " + categorie + " a fait " + compteur + " comparaisons !");
    }

    public static void triBulleAmeliore(ArrayList<PaireChaineEntier> listPaire) {
        int i = 0;
        boolean onAPermute = true;
        while (onAPermute) {
            onAPermute = false;
            int j = listPaire.size() - 1;
            while (j > i) {
                if (listPaire.get(j).getChaine().compareTo(listPaire.get(j-1).getChaine()) < 0) {
                    PaireChaineEntier sauvegarde = listPaire.get(j);
                    listPaire.set(j, listPaire.get(j-1));
                    listPaire.set(j-1, sauvegarde);
                    onAPermute = true;
                }
                j--;
            }
        }
    }

    public static void triInsertionDerniereValeur(ArrayList<PaireChaineEntier> listPaire) {
        int j = listPaire.size() - 1;
        PaireChaineEntier valAPlacer = listPaire.get(j);
        while (j > 0 && listPaire.get(j - 1).getChaine().compareTo(valAPlacer.getChaine()) > 0) {
            listPaire.set(j, listPaire.get(j - 1));
            j--;
        }
        listPaire.set(j, valAPlacer);
    }

    public static void main(String[] args) {
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

       // Test pour les lexiques automatiques
        generationLexique(depeches, "ENVIRONNEMENT-SCIENCES", "./Environnement-Sciences-Auto.txt");
        generationLexique(depeches, "CULTURE", "./Culture-Auto.txt");
        generationLexique(depeches, "ECONOMIE", "./Economie-Auto.txt");
        generationLexique(depeches, "POLITIQUE", "./Politique-Auto.txt");
        generationLexique(depeches, "SPORTS", "./Sport-Auto.txt");

        //initialisation des lexiques
        environnementScience.initLexique("./Environnement-Sciences-Auto.txt");
        culture.initLexique("./Culture-Auto.txt");
        economie.initLexique("./Economie-Auto.txt");
        politique.initLexique("./Politique-Auto.txt");
        sport.initLexique("./Sport-Auto.txt");

        depeches = lectureDepeches("./test.txt");
        classementDepeches(depeches, categories, "sortieAuto.txt");

        // Test pour les fichiers manuels
        environnementScience.initLexique("./Environnement-Sciences2.0.txt");
        triBulleAmeliore(environnementScience.getLexique());
        System.out.println(environnementScience.getLexique());
        culture.initLexique("./Culture2.0.txt");
        triBulleAmeliore(culture.getLexique());
        System.out.println(culture.getLexique());
        economie.initLexique("./Economie3.0.txt");
        triBulleAmeliore(economie.getLexique());
        System.out.println(economie.getLexique());
        politique.initLexique("./Politique3.0.txt");
        triBulleAmeliore(politique.getLexique());
        System.out.println(politique.getLexique());
        sport.initLexique("./Sport2.0.txt");
        triBulleAmeliore(sport.getLexique());
        System.out.println(sport.getLexique());
        classementDepeches(depeches, categories, "sortieManuelle.txt");
    }
}