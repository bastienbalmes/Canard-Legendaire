package canard;

import java.util.Random;

/**
 * Canard de type Eau, sous-classe de {@link Canard}.
 */
public class CanardEau extends Canard {

    /**
     * Crée un canard de type Eau
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardEau(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        //appel du constructeur de la classe parente
        super(nom, TypeCanard.EAU, ptsVie, dgtAttaque, vitesse);
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
        if (this.getPe() > 0) {
            // soigne de 20 pv
            this.ptsVie += 20;
            pe -= 15;
        } else {
            System.out.println("Plus de PE");
        }
    }

    /**
     * Méthode non implémentée
     *
     * @param cibleCanard Le canard cible
     */
    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {
        // pas utile pour les canards eau
    }
}
