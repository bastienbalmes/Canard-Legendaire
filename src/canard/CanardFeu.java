package canard;

import java.util.Random;

public class CanardFeu extends Canard {
    public CanardFeu(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        super(nom, TypeCanard.FEU, ptsVie, dgtAttaque, vitesse);
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
        if(this.getPe() > 0){
            this.appliquerEffets(TypeStatus.DGTSUPP,2);
            pe -= 15;
        }else{
            System.out.println("Plus de PE");
        }

    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }
    @Override
    public void attaquer(Canard canardAttaque) {
        super.attaquer(canardAttaque);
        Random rand = new Random();
        if (rand.nextInt(100) < 30) {
            canardAttaque.appliquerEffets(TypeStatus.BRULE, 3);
            System.out.println(canardAttaque.getNom() + " ça sent le canard roti par ici ! ");
        }
    }

}
