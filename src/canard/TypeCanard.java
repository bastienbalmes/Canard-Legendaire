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
}
