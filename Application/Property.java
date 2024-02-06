public class Property extends Case {
    private String name;
    private int price;
    private Player owner;
    private int baseRent;
    private static int nextId = -1; // initialisation à -1 pour que la première propriété ait un id de 0
    private int id;

    public Property(String name, int price, int baseRent) {
        super(name);
        this.price = price;
        this.owner = null; // La propriété n'a pas de propriétaire au départ
        this.baseRent = baseRent;
        this.id = ++nextId; // un identifiant unique à chaque nouvelle instance et l'incrémenter

    }

    public int getBaseRent() {
        return baseRent;
    }

    public int getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public int calculateRent() {
        return getBaseRent();
    }

    @Override
    public void effectuerAction(Player joueur) {
        if (owner == null) {
            // La propriété n'a pas de propriétaire, le joueur peut choisir de l'acheter
            if (joueur.getMoney() >= price) {
                // Le joueur a suffisamment d'argent pour acheter la propriété
                joueur.retirerArgent(price);
                setOwner(joueur);
                System.out.println(joueur.getName() + " a acheté la propriété " + getName());
            } else {
                System.out.println(joueur.getName() + " n'a pas assez d'argent pour acheter la propriété " + getName());
            }
        } else if (owner != joueur) {
            // La propriété a un propriétaire autre que le joueur actuel, collecter le loyer
            int loyer = calculateRent();
            joueur.retirerArgent(loyer);
            owner.ajouterArgent(loyer);
            System.out.println(joueur.getName() + " a payé " + loyer + " de loyer à " + owner.getName() + " pour la propriété " + getName());
        }
    }

}
