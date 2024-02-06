public abstract class Case {
    private String nom;

    public Case(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    // Méthode abstraite pour gérer les actions spécifiques à chaque type de case
    public abstract void effectuerAction(Player joueur);
}
