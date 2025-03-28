import java.util.Scanner;
import canard.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans Canard Fighter Simulator !");

        Canard canard1 = creerCanard(scanner, 1);
        Canard canard2 = creerCanard(scanner, 2);

        System.out.println(canard1.getNom() + " (Type: " + canard1.getType() + ") VS " + canard2.getNom() + " (Type: " + canard2.getType() + ")");

        // Comparer les vitesses des canards pour déterminer qui commence
        Canard premierAttaquant = canard1.getVitesse() >= canard2.getVitesse() ? canard1 : canard2;
        Canard secondAttaquant = premierAttaquant == canard1 ? canard2 : canard1;

        while (!canard1.estKo() && !canard2.estKo()) {
            // Tour de combat du premier attaquant
            if (tourDeCombat(scanner, premierAttaquant, secondAttaquant)) {
                break;
            }
            if (secondAttaquant.estKo()) {
                System.out.println(secondAttaquant.getNom() + " est KO. " + premierAttaquant.getNom() + " gagne !");
                break;
            }

            // Tour de combat du second attaquant
            if (tourDeCombat(scanner, secondAttaquant, premierAttaquant)) {
                break;
            }

            if (premierAttaquant.estKo()) {
                System.out.println(premierAttaquant.getNom() + " est KO. " + secondAttaquant.getNom() + " gagne !");
                break;
            }
            if (canard1.getPe() <= 0 && canard2.getPe() <= 0) {
                if (canard1.getPtsVie() > canard2.getPtsVie()) {
                    System.out.println(canard1.getNom() + " gagne ! (plus de PV)");
                    break;
                } else if (canard2.getPtsVie() > canard1.getPtsVie()) {
                    System.out.println(canard2.getNom() + " gagne ! (plus de PV)");
                    break;
                } else {
                    System.out.println("Les deux canards sont à égalité.");
                    break;
                }
            }
        }


        scanner.close();
    }


    public static Canard creerCanard(Scanner scanner, int numero) {
        System.out.println("Création du Canard " + numero);

        System.out.print("Nom du Canard: ");
        String nom = scanner.nextLine();

        System.out.print("Points de Vie (PV): ");
        double ptsVie = scanner.nextDouble();

        System.out.print("Points d'Attaque (PA): ");
        double dgtAttaque = scanner.nextDouble();

        System.out.print("Vitesse: ");
        int vitesse = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne restante après nextInt()

        System.out.println("Choisissez le type de Canard: ");
        System.out.println("1. Eau");
        System.out.println("2. Feu");
        System.out.println("3. Glace");
        System.out.println("4. Vent");
        System.out.print("Votre choix: ");
        int choixType = scanner.nextInt();
        scanner.nextLine();  // Consomme la nouvelle ligne restante après nextInt()

        TypeCanard type = TypeCanard.values()[choixType - 1];

        switch (type) {
            case FEU:
                return new CanardFeu(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            case EAU:
                return new CanardEau(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            case GLACE:
                return new CanardGlace(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            case VENT:
                return new CanardVent(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            default:
                return null;
        }
    }


    public static boolean tourDeCombat(Scanner scanner, Canard attaquant, Canard defendeur) {
        System.out.println("\n--- Tour de " + attaquant.getNom() + " ---");
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser Capacité Spéciale");
        System.out.println("3. Quitter");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            if (attaquant.getPe() >= 5) {
                attaquant.attaquer(defendeur);
                System.out.println(attaquant.getNom() + " attaque " + defendeur.getNom() + " !");
            } else {
                System.out.println(attaquant.getNom() + " n'a pas assez de PE pour attaquer.");
            }
        } else if (choix == 2) {
            if (attaquant.getPe() >= 15) {
                if (attaquant.getType() == TypeCanard.GLACE) {
                    attaquant.activerCapaciteSpecialeAttaquante(defendeur);
                } else {
                    attaquant.activerCapaciteSpeciale();
                }
            } else if (attaquant.getPe() >= 5 && attaquant.getPe() < 15) {
                attaquant.attaquer(defendeur);
                System.out.println(attaquant.getNom() + " Attaque simple lancer, pas assez de PE pour une spéciale ");
            } else {
                System.out.println(attaquant.getNom() + " n'a pas assez de PE pour l'attaque spéciale.");
            }
        } else if (choix == 3) {
            System.out.println("Fin du combat.");
            return true;
        }
        attaquant.majStatut();

        System.out.println(attaquant.getNom() + " PV: " + attaquant.getPtsVie());
        System.out.println(attaquant.getNom() + " PE: " + attaquant.getPe());
        System.out.println(defendeur.getNom() + " PV: " + defendeur.getPtsVie());
        System.out.println(defendeur.getNom() + " PE: " + defendeur.getPe());
        return false;
    }
}
