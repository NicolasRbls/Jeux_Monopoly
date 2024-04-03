public class Property extends Case {
    private int price;
    private Player owner;
    private int baseRent;
    private int houses = 0;
    private boolean hotel = false;
    

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

    public void addHouse() {
        if (!hotel && houses < 4) {
            houses++;
        }
        // Sinon, ignorer l'action
    }

    public void buildHotel() {
        if (houses == 4) {
            hotel = true;
            houses = 0;
        }
        // Sinon, ignorer l'action
    }

    public int calculateRent() {
        if (hotel) {
            return baseRent * 5;
        }
        return baseRent + houses * (baseRent / 2); // calcul du loyer
    }

    public int getHouses(){
        return this.houses;
    }

    public boolean hasHotel(){
        return this.hotel;
    }

    public int getHouseCost() {
        return (int) (baseRent * 1.5); // prix maison en fonction du loyer
    }

    public int getHotelCost() {
        return getHouseCost() * 3; // prix hotel en fonction du cout maison
    }

    @Override
    public String effectuerAction(Player joueur) {
        return null;
    }
        
}
