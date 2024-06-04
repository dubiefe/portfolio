import java.util.ArrayList;

public class UtilitairePaireChaineEntier {


    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        while (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) != 0) {
            i++;
        }
        //i == listePaires.size() || listePaires.get(i).getChaine().compareTo(chaine) == 0
        if (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) == 0) {
            return i;
        } else {
            return -1;
        }
    }

    public static PaireResultatCompteur<Integer> indicePourChaineCompteur(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        int indice;
        int compteur = 0;
        while (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) != 0) {
            compteur++;
            i++;
        }
        //i == listePaires.size() || listePaires.get(i).getChaine().compareTo(chaine) == 0
        if (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) == 0) {
            indice = i;
            compteur++;
        } else {
            indice = -1;
        }
        return new PaireResultatCompteur<>(indice, compteur);
    }

    public static int indicePourChaineTri(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        // {listePaires est trié} --> {recherche dichotomique pour trouver l'indice}
        if (listePaires.size() == 0) {
            return -1;
        } else {
            int inf = 0;
            int sup = listePaires.size() - 1;
            int m;
            while (inf < sup) {
                m = (inf + sup) / 2;
                if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
            } //inf = sup
            if (listePaires.get(inf).getChaine().compareTo(chaine) == 0) {
                return inf;
            } else { return -1; }
        }
    }

    public static PaireResultatCompteur<Integer> indicePourChaineTriCompteur(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        // {listePaires est trié} --> {recherche dichotomique pour trouver l'indice}
        int resultat, compteur = 0;
        if (listePaires.size() == 0) {
            resultat = -1;
        } else {
            int inf = 0;
            int sup = listePaires.size() - 1;
            int m;
            while (inf < sup) {
                m = (inf + sup) / 2;
                if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
                compteur++;
            } //inf = sup
            if (listePaires.get(inf).getChaine().compareTo(chaine) == 0) {
                resultat = inf;
            } else { resultat = -1; }
            compteur++;
        }
        compteur++;
        return new PaireResultatCompteur<>(resultat, compteur);
    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        while (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) != 0) {
            i++;
        }
        //i == listePaires.size() || listePaires.get(i).getChaine().compareTo(chaine) == 0
        if (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) == 0) {
            return listePaires.get(i).getEntier();
        } else {
            return 0;
        }
    }

    public static PaireResultatCompteur<Integer> entierPourChaineCompteur(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        int i = 0;
        int entier;
        int compteur = 0;
        while (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) != 0) {
            compteur++;
            i++;
        }
        //i == listePaires.size() || listePaires.get(i).getChaine().compareTo(chaine) == 0
        if (i < listePaires.size() && listePaires.get(i).getChaine().compareTo(chaine) == 0) {
            entier = listePaires.get(i).getEntier();
            compteur++;
        } else {
            entier = 0;
        }
        return new PaireResultatCompteur<>(entier, compteur);
    }

    public static int entierPourChaineTri(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        // {listePaires est trié} --> {recherche dichotomique pour trouver l'entier}
        if (listePaires.size() == 0) {
            return 0;
        } else {
            int inf = 0;
            int sup = listePaires.size() - 1;
            int m;
            while (inf < sup) {
                m = (inf + sup) / 2;
                if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
            } //inf = sup
            if (listePaires.get(inf).getChaine().compareTo(chaine) == 0) {
                return listePaires.get(inf).getEntier();
            } else { return 0; }
        }
    }

    public static PaireResultatCompteur<Integer> entierPourChaineTriCompteur(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        // {listePaires est trié} --> {recherche dichotomique pour trouver l'entier et le nb de comparaisons}
        int resultat, compteur = 0;
        if (listePaires.size() == 0) {
            resultat = 0;
        } else {
            int inf = 0;
            int sup = listePaires.size() - 1;
            int m;
            while (inf < sup) {
                m = (inf + sup) / 2;
                if (listePaires.get(m).getChaine().compareTo(chaine) >= 0) {
                    sup = m;
                } else {
                    inf = m + 1;
                }
                compteur++;
            } //inf = sup
            if (listePaires.get(inf).getChaine().compareTo(chaine) == 0) {
                resultat = listePaires.get(inf).getEntier();
            } else { resultat = 0; }
            compteur++;
        }
        compteur++;
        return new PaireResultatCompteur<>(resultat, compteur);
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        int max = 0;
        String maxChaine = new String();
        int i = 0;
        while (i < listePaires.size()) {
            if (listePaires.get(i).getEntier() > max) {
                max = listePaires.get(i).getEntier();
                maxChaine = listePaires.get(i).getChaine();
            }
            i++;
        }
        return maxChaine;
    }


    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        float moy = 0;
        int i = 0;
        while (i < listePaires.size()) {
            moy = moy + listePaires.get(i).getEntier(); //somme des entier de la liste
            i++;
        }
        return ((float) moy / listePaires.size()); //division par le nombre d'entier de la liste
    }

}
