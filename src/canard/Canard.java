package canard;

public abstract class Canard {
    private String nom;
    private TypeCanard type;
    private int ptsVie;
    private int dgtAttaque;

    public Canard(String nom, TypeCanard type, int ptsVie, int dgtAttaque) {
        this.nom = nom;
        this.type = type;
        this.ptsVie = ptsVie;
        this.dgtAttaque = dgtAttaque;
    }

    public String getNom() {
        return nom;
    }

    public TypeCanard getType() {
        return type;
    }

    public int getPtsVie() {
        return ptsVie;
    }

    public int getDgtAttaque() {
        return dgtAttaque;
    }

    public void attaquer(Canard canardAttauqer){
        int dgt = this.getDgtAttaque();
        if(this.fortContre(canardAttauqer.type)){
            dgt *= 2;
        }
        subirDegats(dgt);
    }

    public void subirDegats(int degats){
        this.ptsVie-=degats;
    }

    public boolean estKo(){
        return this.ptsVie==0;
    }

    public boolean fortContre(TypeCanard autre) {
        return (this.type == TypeCanard.EAU && autre == TypeCanard.FEU) ||
                (this.type == TypeCanard.FEU && autre == TypeCanard.GLACE) ||
                (this.type == TypeCanard.GLACE && autre == TypeCanard.VENT) ||
                (this.type == TypeCanard.VENT && autre == TypeCanard.EAU);
    }

    public abstract void activerCapaciteSpeciale();
}
