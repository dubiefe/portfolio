public class PaireResultatCompteur<R> {

    //atributs
    private final R resultat;
    private final int compteur;

    //constructeur
    public PaireResultatCompteur(R resultat, int compteur) {
        this.resultat = resultat;
        this.compteur = compteur;
    }

    //méthodes
    public R getResultat() {
        return resultat;
    }

    public int getCompteur() {
        return compteur;
    }
}
