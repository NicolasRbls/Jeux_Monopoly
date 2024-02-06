import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Property> properties;
    public static final int NUM_PROPERTIES = 2; //le nombre total de propriétés
    

    public Board() {
        properties = new ArrayList<>();
    }

    public void initialize() {
        // ici les propriétés/cases du Monopoly au plateau
        Property property1 = new Property("Propriété 1", 250, 75);
        Property property2 = new Property("Propriété 2", 300, 90);
        // ajoutez d'autres propriétés...

        // ne pas dépasser la limite maximale
        if (properties.size() > NUM_PROPERTIES) {
            throw new IllegalStateException("Le nombre maximum de propriétés a été atteint.");
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public Property getPropertyById(int id) {
        for (Property property : properties) {
            if (property.getId() == id) {
                return property;
            }
        }
        return null; // Si la propriété n'est pas trouvée
    }

    public List<Property> getAvailableProperties() {
        List<Property> availableProperties = new ArrayList<>();
        for (Property property : properties) {
            if (property.getOwner() == null) {
                availableProperties.add(property);
            }
        }
        return availableProperties;
    }

    public void purchaseProperty(Player player, Property property) {
        if (player.getMoney() >= property.getPrice() && property.getOwner() == null) {
            player.retirerArgent(property.getPrice());
            property.setOwner(player);
        }
    }

    // Méthodes pour gérer les loyers, les améliorations de propriétés, etc.
}
