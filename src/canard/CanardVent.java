package canard;

import java.util.Random;

public class CanardVent extends Canard {
    private int multiplicateurVitesse =1;
    public CanardVent(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        super(nom, TypeCanard.VENT, ptsVie, dgtAttaque, vitesse);
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
        this.vitesse *= multiplicateurVitesse++;
    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }
}
