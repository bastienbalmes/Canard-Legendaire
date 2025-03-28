package canard;

import java.util.Random;

public class CanardEau extends Canard {
    public CanardEau(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        super(nom, TypeCanard.EAU, ptsVie, dgtAttaque, vitesse);
    }

    @Override
    public void activerCapaciteSpeciale() {
        if (listeStatus.get(TypeStatus.GELEE.ordinal()) > 0) {
            System.out.println(this.nom + " Ta froid mon petit loulou, tu veux une veste le temps que tu restes gelé ?");
            return;
        }

        if (listeStatus.get(TypeStatus.PARALYSE.ordinal()) > 0) {
            Random rand = new Random();
            if (rand.nextBoolean()) {
                System.out.println(this.nom + " Tu feras attention petit gars, tu peux plus bouger !! (ça doit être frustrant) ");
                return;
            }
        }
        this.ptsVie += 20;
    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }
}
