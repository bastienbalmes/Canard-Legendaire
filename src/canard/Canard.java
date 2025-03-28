package canard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Canard {
    protected String nom;
    protected TypeCanard type;
    protected double ptsVie;
    protected double dgtAttaque;
    protected ArrayList<Integer> listeStatus;
    protected int vitesse;
    protected int pe;

    /**
     * Constructeur de la classe Canard
     *
     * @param nom       nom du canard
     * @param type      type du canard (FEU, EAU, GLACE, VENT)
     * @param ptsVie    points de vie du canard
     * @param dgtAttaque  dégats d'attaque du canard
     * @param vitesse    vitesse du canard
     */
    public Canard(String nom, TypeCanard type, int ptsVie, double dgtAttaque, int vitesse) {
        this.nom = nom;
        this.type = type;
        this.ptsVie = ptsVie;
        this.dgtAttaque = dgtAttaque;
        this.vitesse = vitesse;
        this.pe = 100;
        listeStatus = new ArrayList<>(Collections.nCopies(TypeStatus.values().length, 0));
    }

    /**
     * Applique un statut temporaire au canard
     *
     * @param statut statut à appliquer ( BRULE, GELE, PARALYSE)
     * @param duree  durée du statut
     */
    public void appliquerEffets(TypeStatus statut, int duree) {
        listeStatus.set(statut.ordinal(), duree);
    }

    /**
     * Vérifie la durée en nombre de tour d'un statut
     *
     * @param statut statut a verifier
     * @return retourne vrai si il reste des tours au status
     */
    public boolean verifierLeStatut(TypeStatus statut) {
        return listeStatus.get(statut.ordinal()) > 0;
    }

    /**
     * Met a jour les statut, permet de décrementer les effets et d'appliquer l'effet
     *
     */
    public void majStatut() {
        for (int i = 0; i < listeStatus.size(); i++) {
            if (listeStatus.get(i) > 0) {
                // décremente la durée du statut
                listeStatus.set(i, listeStatus.get(i) - 1);
                if (i == TypeStatus.BRULE.ordinal()) {
                    // perd 10% de la vie par tour
                    double degatsBrulure = this.ptsVie * 0.10;
                    this.ptsVie -= degatsBrulure;
                    System.out.println(this.nom + "Tu perd de la vie mon grand il ne fallais pas jouer avec le feu");
                }
            }
        }
    }

    /**
     * Retourne le nom du canard
     * @return retourne le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le type du canard
     * @return retourne le type
     */
    public TypeCanard getType() {
        return type;
    }

    /**
     * Retourne les points de vie du canard
     * @return retourne les pv
     */
    public double getPtsVie() {
        return ptsVie;
    }

    /**
     * Retourne les points d'énergie du canard
     * @return retourne les PE
     */
    public int getPe() {
        return pe;
    }

    /**
     * Retourne la vitesse du canard
     * @return retourne la vitesse
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Permet d'attaquer un canard
     * @param canardAttaque cible de l'attaque
     */
    public void attaquer(Canard canardAttaque) {
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
        // infligue des dégats RIDICULE si le statut dégat supplémentaire est actif
        if (listeStatus.get(TypeStatus.DGTSUPP.ordinal()) > 0) {
            System.out.println(this.nom + " ça va péter péter !!!!!!");
            // vérifie les points d'énergie avant d'effectuer une attaque
            if(this.getPe() > 0){
                canardAttaque.subirDegats(10000000);
                pe -= 5;
            }else{
                System.out.println("Plus de PE");
            }

            return;
        }
        System.out.println("ça doit faire mal ça");
        // vérifie les points d'énergie avant d'effectuer une attaque
        if(this.getPe() > 0){
            boolean coupCritique = rand.nextInt(100) < 10;
            // vérifie si le canard fait un coup critique
            if (coupCritique) {
                canardAttaque.subirDegats((this.dgtAttaque * TypeCanard.getMultiplicateur(this.type, canardAttaque.getType()))*2);
                System.out.println("ça doit piquer ! COUP CRITIQUE");
            }
            canardAttaque.subirDegats((this.dgtAttaque * TypeCanard.getMultiplicateur(this.type, canardAttaque.getType())));
        }else{
            System.out.println("Plus de PE");
        }
        pe -= 5;
    }


    /**
     * Permet au canard de subir les dégats
     * @param degats dégats subit
     */
    public void subirDegats(double degats){
        this.ptsVie-=degats;
    }

    /**
     * Permet de savoir si le pokemon est ko
     * @return true si pokemon < à 0
     */
    public boolean estKo(){
        return this.ptsVie <=0;
    }


    /**
     * Capaciter spéciale, abstraite car chaque type a une capacite spéaciale différente.
     */
    public abstract void activerCapaciteSpeciale();

    /**
     * Capaciter spéciale, abstraite car chaque type a une capacite spéaciale différente.
     * Celle-ci prend un paramètre supplémentaire si on souhaite faire subir des effet ou dégat directement avec notre attaque spéciale
     * @param cibleCanard cible de l'attaque spéciale
     */
    public abstract void activerCapaciteSpecialeAttaquante(Canard cibleCanard);
}
