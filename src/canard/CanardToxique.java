package canard;

public class CanardToxique extends Canard {

    /**
     * Constructeur de la classe Canard
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardToxique(String nom, int ptsVie, double dgtAttaque, int vitesse) {
        super(nom,TypeCanard.TOXIQUE, ptsVie, dgtAttaque, vitesse);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }
}
