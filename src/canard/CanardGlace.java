package canard;

public class CanardGlace extends Canard {
    public CanardGlace(String nom, int ptsVie, int dgtAttaque) {
        super(nom, TypeCanard.GLACE, ptsVie, dgtAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }
}
