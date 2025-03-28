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
        this.appliquerEffets(TypeStatus.DGTSUPP,2);
    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }

    @Override
    public void attaquer(Canard canardAttauqer){
        if (verifierLeStatut(TypeStatus.GELEE) || verifierLeStatut(TypeStatus.PARALYSE)) {
            return ;
        } else if (verifierLeStatut(TypeStatus.DGTSUPP)) {
            canardAttauqer.subirDegats(this.getDgtAttaque()*TypeCanard.getMultiplicateur(this.type, canardAttauqer.getType())+100000000);
        }
        canardAttauqer.subirDegats(this.getDgtAttaque()*TypeCanard.getMultiplicateur(this.type, canardAttauqer.getType()));
    }
}
