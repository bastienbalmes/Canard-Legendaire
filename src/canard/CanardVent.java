package canard;

public class CanardVent extends Canard {
    public CanardVent(String nom, int ptsVie, int dgtAttaque) {
        super(nom, TypeCanard.VENT, ptsVie, dgtAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }
}
