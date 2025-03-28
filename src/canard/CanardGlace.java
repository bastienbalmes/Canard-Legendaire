package canard;

import java.util.Random;

/**
 * Canard de type Glace, sous-classe de {@link Canard}.
 */
public class CanardGlace extends Canard {
    /**
     * Crée un canard de type Glace
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardGlace(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        //appel du constructeur de la classe parente
        super(nom, TypeCanard.GLACE, ptsVie, dgtAttaque, vitesse);
    }


    @Override
    public void activerCapaciteSpeciale() {

    }


    /**
     * Active la capacité spéciale attaquante
     * @param cibleCanard canard qui subit l'effet
     */
    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {
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
            cibleCanard.appliquerEffets(TypeStatus.GELEE, 2);
            System.out.println(cibleCanard.nom + " est gelé, c'est du propre ça !");
            pe -= 15;
        } else {
            System.out.println("Plus de PE");
        }
    }
}
