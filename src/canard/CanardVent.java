package canard;

import java.util.Random;

/**
 * Canard de type Glace, sous-classe de {@link Canard}.
 */
public class CanardVent extends Canard {
    // variable du multiplicteur de la vitesse
    private int multiplicateurVitesse = 2;

    /**
     * Crée un canard de type Glace
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardVent(String nom, int ptsVie, int dgtAttaque, int vitesse) {
        super(nom, TypeCanard.VENT, ptsVie, dgtAttaque, vitesse);
    }

    /**
     * Active la capacité spéciale du CanardVent qui augmente la vitesse
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
            this.vitesse *= multiplicateurVitesse;
            multiplicateurVitesse++;
            pe -= 15;
        } else {
            System.out.println("Plus de PE");
        }
    }

    /**
     * Méthode vide pour la capacité spéciale attaquante du CanardVent.
     * Cette méthode n'a pas d'effet dans cette classe.
     *
     * @param cibleCanard Le canard cible.
     */
    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {

    }

    /**
     * Attaque un autre canard et applique un effet de paralysie
     *
     * @param canardAttaque canard qui subit l'attaque
     */
    @Override
    public void attaquer(Canard canardAttaque) {
        super.attaquer(canardAttaque);
        Random rand = new Random();
        // 30% de chance de paralyser
        if (rand.nextInt(100) < 30) {
            canardAttaque.appliquerEffets(TypeStatus.PARALYSE, 3);
            System.out.println(canardAttaque.getNom() + "Bah alors tu peux plus bouger ?");
        }
    }
}
