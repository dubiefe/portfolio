import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorie {

    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique; //le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public  ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte
    public void initLexique(String nomFichier) {
        //déclaration d'une arraylist de paireChaineEntier
        try {
            lexique = new ArrayList<PaireChaineEntier>();
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);
            String ligne = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] chaineSplit = ligne.split(":");
                String mot = chaineSplit[0];
                String noteTemp = chaineSplit[1];
                int note = Integer.parseInt(noteTemp);
                lexique.add(new PaireChaineEntier(mot, note));
                ligne = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        int score = 0;
        int i = 0;
        ArrayList<String> mots = d.getMots();
        while (i < mots.size()) {
            score = score + UtilitairePaireChaineEntier.entierPourChaineTri(lexique, mots.get(i));
            i++;
        }
        return score;
    }

    //calcul du score et du nb de comparaisons d'une dépêche pour la catégorie dans une liste triee
    public PaireResultatCompteur<Integer> scoreCompteur(Depeche d) {
        int score = 0;
        int i = 0;
        int compteur = 0;
        ArrayList<String> mots = d.getMots();
        while (i < mots.size()) {
            score = score + UtilitairePaireChaineEntier.entierPourChaineTriCompteur(lexique, mots.get(i)).getResultat();
            compteur = UtilitairePaireChaineEntier.entierPourChaineTriCompteur(lexique, mots.get(i)).getCompteur();
            i++;
        }
        return new PaireResultatCompteur<>(score, compteur);
    }


}
