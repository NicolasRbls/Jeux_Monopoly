public class Case {
    protected String nom;
    protected static int nextId = -1;
    protected int id;

    public Case(String nom) {
        this.nom = nom;
        this.id = ++nextId;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void effectuerAction(Player joueur) {
        // Logique spécifique à la case peut être ajoutée ici
        System.out.println(joueur.getName() + " est sur " + nom);
    }
}