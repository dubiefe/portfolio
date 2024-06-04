public class PaireChaineEntier {
    //atributs
    private String chaine;
    private int entier;

    //constructeur
    public PaireChaineEntier(String chaine, int entier) {
        this.chaine = chaine;
        this.entier = entier;
    }

    //méthodes
    public String getChaine() {
        return chaine;
    }

    public int getEntier() {
        return entier;
    }

    @Override
    public String toString() {
        return chaine + " " + entier;
    }
}
