import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Case> cases;
    public static final int NUM_CASES = 40 ; //le nombre total de cases
    public static final int JAIL_POSITION = 10;

    public Board() {
        cases = new ArrayList<>();
    }

    public void initialize() {
        addCase(new EmptyCase("Départ"));
        addCase(new Property("Boulevard de Belleville", 60, 30));
        addCase(new CommunityChestCase("Caisse de Communauté 1"));
        addCase(new Property("Rue Lecourbe", 60, 30));
        addCase(new Tax("Impôt sur le revenu" ,100));
        addCase(new Station("Gare Montparnasse" , 200)); 
        addCase(new Property("Rue de Vaugirard", 100, 50));
        addCase(new ChanceCase("Chance 1"));
        addCase(new Property("Rue de Courcelles", 100, 50));
        addCase(new Property("Avenue de la République", 120, 60));
        addCase(new EmptyCase("Prison"));
        addCase(new Property("Boulevard de la Villette", 140, 70));
        addCase(new Utility("Compagnie d'électricité",150)); 
        addCase(new Property("Avenue de Neuilly", 160, 80));
        addCase(new Property("Rue de Paradis", 140, 70));
        addCase(new Station("Gare de Lyon",200)); 
        addCase(new Property("Avenue Mozart", 180, 90));
        addCase(new CommunityChestCase("Caisse de Communauté 2"));
        addCase(new Property("Boulevard Saint-Michel", 180, 90));
        addCase(new Property("Place Pigalle", 200, 100));
        addCase(new FreeParking()); // parck gratuit pour recup argent 
        addCase(new Property("Avenue Matignon", 220, 110));
        addCase(new ChanceCase("Chance 2"));
        addCase(new Property("Boulevard Malesherbes", 220, 110));
        addCase(new Property("Avenue Henri-Martin", 240, 120));
        addCase(new Station("Gare du Nord",200)); 
        addCase(new Property("Faubourg Saint-Honoré", 260, 130));
        addCase(new Property("Place de la Bourse", 260, 130));
        addCase(new Utility("Compagnie des Eaux",150)); 
        addCase(new Property("Rue La Fayette", 280, 140));
        addCase(new GoToJail());//aller en prison
        addCase(new Property("Avenue de Breteuil", 300, 150));
        addCase(new Property("Avenue Foch", 300, 150));
        addCase(new CommunityChestCase("Caisse de Communauté 3"));
        addCase(new Property("Boulevard des Capucines", 320, 160));
        addCase(new Station("Gare Saint-Lazare",200)); 
        addCase(new ChanceCase("Chance 3"));
        addCase(new Property("Avenue des Champs-Élysées", 350, 175));
        addCase(new Tax("Taxe de luxe" , 150));
        addCase(new Property("Rue de la Paix", 400, 200));
    
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
