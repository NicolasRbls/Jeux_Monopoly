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
        return null;
    }
        
}
