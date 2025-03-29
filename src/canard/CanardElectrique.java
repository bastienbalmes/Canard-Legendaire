package canard;

public class CanardElectrique extends Canard {

    /**
     * Constructeur de la classe Canard
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardElectrique(String nom, int ptsVie, double dgtAttaque, int vitesse) {
        super(nom,TypeCanard.ELECTRIQUE, ptsVie, dgtAttaque, vitesse);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }
}
