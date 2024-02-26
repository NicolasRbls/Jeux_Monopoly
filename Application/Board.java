import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Case> cases;
    public static final int NUM_CASES = 22 ; //le nombre total de cases

    public Board() {
        cases = new ArrayList<>();
    }

    public void initialize() {
        //ici les différentes cases du Monopoly au plateau
        addCase(new Property("Boulevard de Belleville", 60, 30));
        addCase(new Property("Rue Lecourbe", 60, 30));
        addCase(new Property("Avenue de la République", 100, 50));
        addCase(new Property("Boulevard de la Villette", 100, 50));
        addCase(new Property("Avenue de Neuilly", 120, 60));
        addCase(new Property("Rue de Paradis", 140, 70));
        addCase(new Property("Avenue Mozart", 140, 70));
        addCase(new Property("Boulevard Saint-Michel", 160, 80));
        addCase(new Property("Place Pigalle", 180, 90));
        addCase(new Property("Avenue Matignon", 180, 90));
        addCase(new Property("Boulevard Malesherbes", 200, 100));
        addCase(new Property("Avenue Henri-Martin", 220, 110));
        addCase(new Property("Cours de Vincennes", 220, 110));
        addCase(new Property("Avenue de la République", 240, 120));
        addCase(new Property("Rue de Courcelles", 260, 130));
        addCase(new Property("Avenue de Neuilly", 260, 130));
        addCase(new Property("Rue de la Fayette", 280, 140));
        addCase(new Property("Avenue Niel", 300, 150));
        addCase(new Property("Avenue Montaigne", 300, 150));
        addCase(new Property("Boulevard Saint-Michel", 320, 160));
        addCase(new Property("Avenue Foch", 350, 175));
        addCase(new Property("Boulevard des Capucines", 400, 200));
        //plusieur types de cases 

        //ne pas dépasser la limite maximale
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
        return null; //si la propriété n'est pas trouvée
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

    public void purchaseProperty(Player player, Property property) {
        if (player.getMoney() >= property.getPrice() && property.getOwner() == null) {
            player.retirerArgent(property.getPrice());
            property.setOwner(player);
        }
    }

    public void addCase(Case aCase) {
        cases.add(aCase);
    }

}
