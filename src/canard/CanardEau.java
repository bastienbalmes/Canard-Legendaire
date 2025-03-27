package canard;

public class CanardEau extends Canard {


    public CanardEau(String nom, int ptsVie, int dgtAttaque) {
        super(nom, TypeCanard.EAU, ptsVie, dgtAttaque);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }
}
