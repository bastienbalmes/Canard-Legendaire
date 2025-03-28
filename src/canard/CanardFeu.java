package canard;

import java.util.Random;

/**
 * Canard de type Feu, sous-classe de {@link Canard}.
 */
public class CanardFeu extends Canard {
    /**
     * Crée un canard de type Feu
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardFeu(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        //appel du constructeur de la classe parente
        super(nom, TypeCanard.FEU, ptsVie, dgtAttaque, vitesse);
    }

    /**
     * Active la capacité spéciale
     */
    @Override
    public void activerCapaciteSpeciale() {
        // quitte la fonction si le canard qui attaque est gelé
        if (listeStatus.get(TypeStatus.GELEE.ordinal()) > 0) {
            System.out.println(this.nom + " Ta froid mon petit loulou, tu veux une veste le temps que tu restes gelé ?");
            return;
        }
        // quite la fonction si le canard qui attaque est paralyser et échoue l'attaque
        if (listeStatus.get(TypeStatus.PARALYSE.ordinal()) > 0) {
            Random rand = new Random();
            // 1/2 d'être paralyser
            if (rand.nextBoolean()) {
                System.out.println(this.nom + " Tu feras attention petit gars, tu peux plus bouger !! (ça doit être frustrant) ");
                return;
            }
        }
        // vérifie les points d'énergie avant d'effectuer une attaque
        if(this.getPe() > 0){
            // il y a anguille sous roche avec cette capaciter spéciale je pense
            this.appliquerEffets(TypeStatus.DGTSUPP,2);
            pe -= 15;
        }else{
            System.out.println("Plus de PE");
        }

    }

    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }

    /**
     * Attaque un autre canard et applique un effet de brulure
     *
     * @param canardAttaque canard qui subit l'attaque
     */
    @Override
    public void attaquer(Canard canardAttaque) {
        // appel d'attaquer de notre classe parente
        super.attaquer(canardAttaque);
        Random rand = new Random();
        if (rand.nextInt(100) < 30) {
            // applique la brulure
            canardAttaque.appliquerEffets(TypeStatus.BRULE, 3);
            System.out.println(canardAttaque.getNom() + " ça sent le canard rôti par ici ! ");
        }
    }


}
