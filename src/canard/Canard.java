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

    public Canard(String nom, TypeCanard type, int ptsVie, double dgtAttaque, int vitesse) {
        this.nom = nom;
        this.type = type;
        this.ptsVie = ptsVie;
        this.dgtAttaque = dgtAttaque;
        this.vitesse = vitesse;
        listeStatus = new ArrayList<>(Collections.nCopies(TypeStatus.values().length, 0));
    }

    public void appliquerEffets(TypeStatus statut, int duree) {
        listeStatus.set(statut.ordinal(), duree);
    }

    public void retirerStatut(TypeStatus statut) {
        listeStatus.set(statut.ordinal(), 0);
    }

    public boolean verifierLeStatut(TypeStatus statut) {
        return listeStatus.get(statut.ordinal()) > 0;
    }

    public void majStatut() {
        for (int i = 0; i < listeStatus.size(); i++) {
            if (listeStatus.get(i) > 0) {
                listeStatus.set(i, listeStatus.get(i) - 1);
                if (i == TypeStatus.BRULE.ordinal()) {
                    double degatsBrulure = this.ptsVie * 0.10;
                    this.ptsVie -= degatsBrulure;
                    System.out.println(this.nom + "Tu perd de la vie mon grand il ne fallais pas jouer avec me feu");
                }
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public TypeCanard getType() {
        return type;
    }

    public double getPtsVie() {
        return ptsVie;
    }

    public double getDgtAttaque() {
        return dgtAttaque;
    }

    public void attaquer(Canard canardAttaque) {
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
        if (listeStatus.get(TypeStatus.DGTSUPP.ordinal()) > 0) {
            System.out.println(this.nom + " ça va péter péter !!!!!!");
            canardAttaque.subirDegats(10000000);
            return;
        }
        System.out.println("ça doit faire mal ça");
        canardAttaque.subirDegats(this.dgtAttaque * TypeCanard.getMultiplicateur(this.type, canardAttaque.getType()));
    }
    public void subirDegats(double degats){
        this.ptsVie-=degats;
    }

    public boolean estKo(){
        return this.ptsVie <=0;
    }



    public abstract void activerCapaciteSpeciale();
    public abstract void activerCapaciteSpecialeAttaquante(Canard cibleCanard);
}
