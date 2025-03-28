package canard;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;

public class CanardTest {
    private CanardFeu canardFeu;
    private CanardEau canardEau;
    private CanardGlace canardGlace;
    private CanardVent canardVent;

    @Before
    public void setUp() {
        canardFeu = new CanardFeu("Canarchaud", 100, 20, 10);
        canardEau = new CanardEau("Canareau", 100, 18, 12);
        canardGlace = new CanardGlace("Canarfroid", 100, 15, 14);
        canardVent = new CanardVent("Canadair", 100, 17, 16);
    }

    @Test
    public void testAttaqueInfligeDegats() {
        double vieAvant = canardEau.getPtsVie();
        System.out.println(vieAvant);
        canardFeu.attaquer(canardEau);
        double vieApres = canardEau.getPtsVie();
        System.out.println(vieApres);
        assertTrue("L'attaque a infliger des dégats", vieApres < vieAvant);
    }

    @Test
    public void testBrulureAppliqueeParCanardFeu() {
        canardFeu.attaquer(canardEau);
        assertTrue("Canard Eau a tu brulé ?",
                canardEau.verifierLeStatut(TypeStatus.BRULE));
    }

    @Test
    public void testMajStatutDiminueDuree() {
        canardEau.appliquerEffets(TypeStatus.BRULE, 3);
        canardEau.majStatut();
        assertEquals("La durée de brûlure doit diminuer", 2, canardEau.listeStatus.get(TypeStatus.BRULE.ordinal()).intValue());
    }

    @Test
    public void testBrulureFaitPerdreVie() {
        canardEau.appliquerEffets(TypeStatus.BRULE, 3);
        double vieAvant = canardEau.getPtsVie();
        canardEau.majStatut();
        assertTrue("Canard Eau perd des PV à cause de la brûlure", canardEau.getPtsVie() < vieAvant);
    }

    @Test
    public void testCanardParalysePeutNePasAttaquer() {
        canardEau.appliquerEffets(TypeStatus.PARALYSE, 2);
        Random rand = new Random();
        boolean peutAttaquer = !rand.nextBoolean();

        double vieAvant = canardFeu.getPtsVie();
        if (peutAttaquer) {
            canardEau.attaquer(canardFeu);
            assertTrue("Canard Eau attaque s'il n'est pas paralyser", canardFeu.getPtsVie() < vieAvant);
        } else {
            assertEquals("Canard Eau n'attaque pas s'il est paralyser", vieAvant, canardFeu.getPtsVie(), 0.01);
        }
    }

    @Test
    public void testCapaciteSpecialeCanardGlaceGeleAdversaire() {
        canardGlace.activerCapaciteSpecialeAttaquante(canardFeu);
        assertTrue("Canard Feu devrait être gelé", canardFeu.verifierLeStatut(TypeStatus.GELEE));
    }

    @Test
    public void testCanardVentAugmenteVitesse() {
        int vitesseAvant = canardVent.getVitesse();
        canardVent.activerCapaciteSpeciale();
        int vitesseApres = canardVent.getVitesse();
        assertTrue("La vitesse doit augmenter", vitesseApres > vitesseAvant);
    }
    @Test
    public void testConsommationPE() {
        int peAvant = canardFeu.getPe();
        canardFeu.attaquer(canardEau);
        assertEquals("L'attaque normale doit coûter 5 PE", peAvant - 5, canardFeu.getPe());
        peAvant = canardFeu.getPe();
        canardFeu.activerCapaciteSpeciale();
        assertEquals("L'attaque spéciale doit coûter 15 PE", peAvant - 15, canardFeu.getPe());
    }

}
