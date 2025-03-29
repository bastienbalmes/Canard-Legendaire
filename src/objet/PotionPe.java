package objet;
import canard.Canard;
public class PotionPe extends Objet {
    private int restauration;

    public PotionPe(int restauration) {
        this.restauration = restauration;
    }

    @Override
    public void utiliser(Canard canard) {
        canard.setPe(canard.getPe() + restauration);
        System.out.println(canard.getNom() + " un REDBULL FRAMBOISE BIEN FRAICHE pour te donner des ailes dans ton combat");
    }
}