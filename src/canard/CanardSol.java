package canard;

import java.util.Random;

public class CanardSol extends Canard {

    /**
     * Constructeur de la classe Canard
     *
     * @param nom        nom du canard
     * @param ptsVie     points de vie du canard
     * @param dgtAttaque dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public CanardSol(String nom, int ptsVie, double dgtAttaque, int vitesse) {
        super(nom, TypeCanard.SOL, ptsVie, dgtAttaque, vitesse);
    }

    @Override
    public void activerCapaciteSpeciale() {

    }

    @Override
    public double getMultiplicateur(TypeCanard cible) {
        return (cible == TypeCanard.FEU || cible == TypeCanard.ELECTRIQUE) ? 2.0 : (cible == TypeCanard.EAU || cible == TypeCanard.GLACE ? 0.5 : 1.5);
    }

    /**
     * Active la capacité spéciale attaquante
     * @param cibleCanard canard qui subit l'effet
     */
    @Override
    public void activerCapaciteSpecialeAttaquante(Canard cibleCanard) {
        Random rand = new Random();
        // quitte la fonction si le canard qui attaque est gelé
        if (listeStatus.get(TypeStatus.GELEE.ordinal()) > 0) {
            System.out.println(this.nom + " Ta froid mon petit loulou, tu veux une veste le temps que tu restes gelé ?");
            return;
        }
        // quite la fonction si le canard qui attaque est paralyser et échoue l'attaque
        if (listeStatus.get(TypeStatus.PARALYSE.ordinal()) > 0) {
            if (rand.nextBoolean()) {
                System.out.println(this.nom + " Tu feras attention petit gars, tu peux plus bouger !! (ça doit être frustrant) ");
                return;
            }
        }
        System.out.println("ça doit faire mal ça");
        // vérifie les points d'énergie avant d'effectuer une attaque
        if(this.getPe() > 0){
            boolean coupCritique = rand.nextInt(100) < 10;
            // vérifie si le canard fait un coup critique
            if (coupCritique) {
                cibleCanard.subirDegats((this.dgtAttaque * this.getMultiplicateur(cibleCanard.getType()))*2);
                System.out.println("ça doit piquer ! COUP CRITIQUE");
            }
            cibleCanard.subirDegats((this.dgtAttaque * this.getMultiplicateur(cibleCanard.getType())));
        }else{
            System.out.println("Plus de PE");
        }
        pe -= 15;
    }


}
