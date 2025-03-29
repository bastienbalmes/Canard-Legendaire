package canard;

public class CanardSol extends Canard {

    /**
     * Constructeur de la classe Canard
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardSol(String nom, int ptsVie, double dgtAttaque, int vitesse) {
        super(nom, TypeCanard.SOL, ptsVie, dgtAttaque, vitesse);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }
}
