package canard;

public class CanardFeu extends Canard {
    public CanardFeu(String nom, int ptsVie, int dgtAttaque) {
        super(nom, TypeCanard.FEU, ptsVie, dgtAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }
}
