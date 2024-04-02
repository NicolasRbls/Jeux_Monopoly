public class Property extends Case {
    private int price;
    private Player owner;
    private int baseRent;
    

    public Property(String name, int price, int baseRent) {
        super(name);
        this.price = price;
        this.owner = null; //la propriété n'a pas de propriétaire au debut
        this.baseRent = baseRent;

    }

    public int getBaseRent() {
        return baseRent;
    }

    public int getPrice() {
        return price;
    }

    public String getName(){
        return nom;
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
    public String effectuerAction(Player joueur) {
        String actionResult = "";
        if (owner == null) {
            // Si la propriété n'a pas de propriétaire, le joueur peut l'acheter.
            if (joueur.getMoney() >= price) {
                // Le joueur a suffisamment d'argent pour acheter la propriété.
                joueur.retirerArgent(price);
                setOwner(joueur);
                actionResult = joueur.getName() + " a acheté la propriété " + getName() + ".";
            } else {
                actionResult = joueur.getName() + " n'a pas assez d'argent pour acheter la propriété " + getName() + ".";
            }
        } else if (owner != joueur) {
            // La propriété a un propriétaire autre que le joueur actuel, collecter le loyer.
            int loyer = calculateRent();
            joueur.retirerArgent(loyer);
            owner.ajouterArgent(loyer);
            actionResult = joueur.getName() + " a payé " + loyer + " de loyer à " + owner.getName() + " pour la propriété " + getName() + ".";
        }
        return actionResult;
    }

}
