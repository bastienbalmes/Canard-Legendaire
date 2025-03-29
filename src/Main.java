import java.util.Scanner;
import canard.*;
import objet.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans Canard Fighter Simulator !");

        // Créer les canards une seule fois
        Canard canard1 = creerCanard(scanner, 1);
        Canard canard2 = creerCanard(scanner, 2);

        boolean continuer = true;

        while (continuer) {
            System.out.println(canard1.getNom() + " (Type: " + canard1.getType() + ") VS " + canard2.getNom() + " (Type: " + canard2.getType() + ")");

            // Comparer les vitesses des canards pour déterminer qui commence
            Canard premierAttaquant = canard1.getVitesse() >= canard2.getVitesse() ? canard1 : canard2;
            Canard secondAttaquant = premierAttaquant == canard1 ? canard2 : canard1;

            // Boucle de combat
            while (!canard1.estKo() && !canard2.estKo()) {
                // Tour du premier attaquant
                if (tourDeCombat(scanner, premierAttaquant, secondAttaquant)) {
                    break;
                }

                if (secondAttaquant.estKo()) {
                    System.out.println(secondAttaquant.getNom() + " est KO. " + premierAttaquant.getNom() + " gagne !");
                    canard1.resetPv();
                    canard1.resetPe();
                    canard2.resetPv();
                    canard2.resetPe();
                    premierAttaquant.evoluer();
                    break;
                }

                // Tour du second attaquant
                if (tourDeCombat(scanner, secondAttaquant, premierAttaquant)) {
                    break;
                }

                if (premierAttaquant.estKo()) {
                    System.out.println(premierAttaquant.getNom() + " est KO. " + secondAttaquant.getNom() + " gagne !");
                    canard1.resetPv();
                    canard1.resetPe();
                    canard2.resetPv();
                    canard2.resetPe();
                    secondAttaquant.evoluer();
                    break;
                }

                // Cas où les deux canards sont à égalité de PV
                if (canard1.getPtsVie() <= 0 && canard2.getPtsVie() <= 0) {
                    if (canard1.getPtsVie() > canard2.getPtsVie()) {
                        System.out.println(canard1.getNom() + " gagne ! (plus de PV)");
                        canard1.resetPv();
                        canard1.resetPe();
                        canard2.resetPv();
                        canard2.resetPe();
                        canard1.evoluer();
                    } else if (canard2.getPtsVie() > canard1.getPtsVie()) {
                        System.out.println(canard2.getNom() + " gagne ! (plus de PV)");
                        canard1.resetPv();
                        canard1.resetPe();
                        canard2.resetPv();
                        canard2.resetPe();
                        canard2.evoluer();
                    } else {
                        System.out.println("Les deux canards sont à égalité.");
                    }
                    break;
                }
            }

            // Demander au joueur s'il veut continuer à combattre
            System.out.println("\nVoulez-vous continuer avec le même combat ? (oui/non)");
            String choix = scanner.nextLine();
            if (choix.equalsIgnoreCase("oui")) {
                // L'utilisateur veut continuer avec les mêmes canards

                continuer = true;
            } else {
                System.out.println("Fin du jeu. À bientôt !");
                continuer = false;
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
        scanner.nextLine();

        System.out.println("Choisissez le type de Canard: ");
        System.out.println("1. Eau");
        System.out.println("2. Feu");
        System.out.println("3. Glace");
        System.out.println("4. Vent");
        System.out.println("5. Électrique");
        System.out.println("6. Toxique");
        System.out.println("7. Sol");
        System.out.print("Votre choix: ");
        int choixType = scanner.nextInt();
        scanner.nextLine();

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
            case ELECTRIQUE:
                return new CanardElectrique(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            case TOXIQUE:
                return new CanardToxique(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            case SOL:
                return new CanardSol(nom, (int) ptsVie, (int) dgtAttaque, vitesse);
            default:
                return null;
        }
    }



    public static boolean tourDeCombat(Scanner scanner, Canard attaquant, Canard defendeur) {
        System.out.println("\n--- Tour de " + attaquant.getNom() + " ---");
        System.out.println("1. Attaquer");
        System.out.println("2. Utiliser Capacité Spéciale");
        System.out.println("3. Utiliser un objet");
        System.out.println("4. Quitter");
        System.out.print("Choix : ");
        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            if (attaquant.getPe() >= 5) {
                attaquant.attaquer(defendeur);
                System.out.println(attaquant.getNom() + " attaque " + defendeur.getNom() + " !");
            } else {
                System.out.println(attaquant.getNom() + " a besoin d'une redbull");
            }
        } else if (choix == 2) {
            if (attaquant.getPe() >= 15) {
                if ((attaquant.getType() == TypeCanard.GLACE) || (attaquant.getType() == TypeCanard.ELECTRIQUE)  || (attaquant.getType() == TypeCanard.SOL)
                        || (attaquant.getType() == TypeCanard.TOXIQUE)) {
                    attaquant.activerCapaciteSpecialeAttaquante(defendeur);
                } else {
                    attaquant.activerCapaciteSpeciale();
                }
            } else if (attaquant.getPe() >= 5 && attaquant.getPe() < 15) {
                attaquant.attaquer(defendeur);
                System.out.println(attaquant.getNom() + " attaque nulle, pas assez de PE pour une spéciale");
            } else {
                System.out.println(attaquant.getNom() + " a besoin d'une redbull");
            }
        } else if (choix == 3) {
            System.out.println("Choisissez un objet : ");
            System.out.println("1. Pansement (vie) ");
            System.out.println("2. Redbull (énergie)");
            int objetChoix = scanner.nextInt();
            scanner.nextLine();
            Objet objet = null;
            if (objetChoix == 1) {
                objet = new PotionVie(50);
            } else if (objetChoix == 2) {
                objet = new PotionPe(30);
            }
            if (objet != null) {
                objet.utiliser(attaquant);
            }
        } else if (choix == 4) {
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
