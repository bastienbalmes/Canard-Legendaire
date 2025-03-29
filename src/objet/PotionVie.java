package objet;

import canard.Canard;

public class PotionVie extends Objet {
    private double restauration;

    public PotionVie(double restauration) {
        this.restauration = restauration;
    }

    @Override
    public void utiliser(Canard canard) {
        canard.setPtsVie(canard.getPtsVie() + restauration);
        System.out.println(canard.getNom() + " voila ton pansement petit bonhomme");
    }
}
