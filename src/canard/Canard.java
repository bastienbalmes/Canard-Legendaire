package canard;

public class Canard {
    private String nom;
    private TypeCanard type;
    private int ptsVie;
    private int dgtAttaque;

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

    }
    public void subirDegats(int degats){
        this.ptsVie-=degats;
    }
    public boolean estKo(){
        return this.ptsVie==0;
    }
}
