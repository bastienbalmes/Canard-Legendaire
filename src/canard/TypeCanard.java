package canard;

/**
 * Enum  pour les types de canard
 * Les types sont : EAU, FEU, GLACE, et VENT
 */
public enum TypeCanard {
    EAU, FEU, GLACE, VENT,ELECTRIQUE,TOXIQUE,SOL;

    /**
     * Retourne le multiplicateur de dégats en fonction des types de canard
     *
     * @param attaquant type du canard attaquant
     * @param cible type du canard cible
     * @return multiplicateur de dégâts
     *         - 2.0 si avantage sur la cible
     *         - 0.5 si désavantage sur la cible
     *         - 1.0 si neutre sur la cible
     */
    public static double getMultiplicateur(TypeCanard attaquant, TypeCanard cible) {
        if (attaquant == cible) {
            return 1.0;
        }
        switch (attaquant) {
            case EAU:
                return (cible == FEU || cible == SOL) ? 2.0 : (cible == GLACE || cible == VENT ? 0.5 : 1.0);
            case FEU:
                return (cible == GLACE || cible == TOXIQUE) ? 2.0 : (cible == EAU || cible == SOL ? 0.5 : 1.0);
            case GLACE:
                return (cible == VENT || cible == SOL) ? 2.0 : (cible == FEU || cible == TOXIQUE ? 0.5 : 1.0);
            case VENT:
                return (cible == EAU || cible == TOXIQUE) ? 2.0 : (cible == GLACE || cible == ELECTRIQUE ? 0.5 : 1.0);
            case ELECTRIQUE:
                return (cible == VENT || cible == EAU) ? 2.0 : (cible == SOL ? 0.5 : 1.0);
            case TOXIQUE:
                return (cible == GLACE || cible == EAU) ? 2.0 : (cible == FEU || cible == SOL ? 0.5 : 1.0);
            case SOL:
                return (cible == FEU || cible == ELECTRIQUE) ? 2.0 : (cible == EAU || cible == GLACE ? 0.5 : 1.0);
            default:
                return 1.0;
        }
    }
}
