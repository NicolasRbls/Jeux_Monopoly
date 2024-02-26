public abstract class Case {
    protected String nom;
    protected static int nextId = -1; //initialisation à -1 pour que la première propriété ait un id de 0
    protected int id;

    public Case(String nom) {
        this.nom = nom;
        this.id = ++nextId; //un identifiant unique à chaque nouvelle instance et l'incrémenter
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    //méthode abstraite
    public abstract void effectuerAction(Player joueur);
}
