package canard;

/**
 * Enum  pour les types de canard
 * Les types sont : EAU, FEU, GLACE, et VENT
 */
public enum TypeCanard {
    EAU, FEU, GLACE, VENT;

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
                return (cible == FEU) ? 2.0 : (cible == VENT ? 0.5 : 1.0);
            case FEU:
                return (cible == GLACE) ? 2.0 : (cible == EAU ? 0.5 : 1.0);
            case GLACE:
                return (cible == VENT) ? 2.0 : (cible == FEU ? 0.5 : 1.0);
            case VENT:
                return (cible == EAU) ? 2.0 : (cible == GLACE ? 0.5 : 1.0);
            default:
                return 1.0;
        }
    }
}
