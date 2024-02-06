public abstract class Case {
    private String nom;
    private static int nextId = -1; // initialisation à -1 pour que la première propriété ait un id de 0
    protected int id;

    public Case(String nom) {
        this.nom = nom;
        this.id = ++nextId; // un identifiant unique à chaque nouvelle instance et l'incrémenter
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    // Méthode abstraite pour gérer les actions spécifiques à chaque type de case
    public abstract void effectuerAction(Player joueur);
}
