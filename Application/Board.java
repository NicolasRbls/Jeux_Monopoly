import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Case> cases;
    public static final int NUM_CASES = 2; // le nombre total de cases

    public Board() {
        cases = new ArrayList<>();
    }

    public void initialize() {
        // ici les différentes cases du Monopoly au plateau
        addCase(new Property("Propriété 1", 250, 75));
        addCase(new Property("Propriété 2", 300, 90));
        // ajoutez d'autres types de cases 

        // Ne pas dépasser la limite maximale
        if (cases.size() > NUM_CASES) {
            throw new IllegalStateException("Le nombre maximum de cases a été atteint.");
        }
    }

    public List<Case> getCases() {
        return cases;
    }

    public Property getPropertyById(int id) {
        for (Case aCase : cases) {
            if (aCase instanceof Property) {
                Property property = (Property) aCase;
                if (property.getId() == id) {
                    return property;
                }
            }
        }
        return null; // Si la propriété n'est pas trouvée
    }

    public List<Property> getAvailableProperties() {
        List<Property> availableProperties = new ArrayList<>();
        for (Case aCase : cases) {
            if (aCase instanceof Property) {
                Property property = (Property) aCase;
                if (property.getOwner() == null) {
                    availableProperties.add(property);
                }
            }
        }
        return availableProperties;
    }
    // Méthodes pour gérer les propriétés, les gares, les compagnies, les loyers, les améliorations, etc.

    public void purchaseProperty(Player player, Property property) {
        if (player.getMoney() >= property.getPrice() && property.getOwner() == null) {
            player.retirerArgent(property.getPrice());
            property.setOwner(player);
        }
    }

    public void addCase(Case aCase) {
        cases.add(aCase);
    }

    // Méthodes pour gérer les loyers, les améliorations de propriétés, etc.
}
