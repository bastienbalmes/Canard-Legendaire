package canard;

import java.util.Random;

public class CanardGlace extends Canard {
    public CanardGlace(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        super(nom, TypeCanard.GLACE, ptsVie, dgtAttaque, vitesse);
    }


    @Override
    public void activerCapaciteSpeciale() {

    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {
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
        if(this.getPe() > 0){
            cibleCanard.appliquerEffets(TypeStatus.GELEE,2);
            System.out.println(cibleCanard.nom + " est geler c'est du propre ça ");
            pe -= 15;
        }else{
            System.out.println("Plus de PE");
        }

    }
}
