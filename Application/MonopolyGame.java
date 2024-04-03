import java.util.ArrayList;
import java.util.List;

public class MonopolyGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int rounds;
    private IUserInterface ui;
    private int lastDiceRoll;
    private static int freeParkingPot = 0;



    public MonopolyGame(IUserInterface ui) {
        board = new Board();
        players = new ArrayList<>();
        dice = new Dice();
        rounds = 0;
        this.ui = ui;
    }

    public void playGame() {
        initializeBoard();
        initializePlayers();
    
        while (!isGameOver()) {
            startRound();
            removeBankruptPlayers(); // Méthode pour retirer les joueurs sans argent et libérer leurs propriétés
            displayGameState();
            rounds++;
        }
        displayOwnedProperties(); // Affiche les propriétés possédées à la fin du jeu
        ui.displayMessage("Le jeu est terminé !");
        
    }


    public void initializePlayers() {
        ui.displayMessage("Combien de joueurs ?");
        int numPlayers = Integer.parseInt(ui.getInput());

        for (int i = 0; i < numPlayers; i++) {
            ui.displayMessage("Nom du joueur " + (i + 1) + ": ");
            String playerName = ui.getInput();
            Player player = new Player(playerName);
            player.setCurrentPosition(0);
            players.add(player);
        }
    }

    public void startRound() {
        ui.displayMessage("Tour " + (rounds + 1));

        for (Player player : players) {
            // Gère le tour si le joueur est en prison
            if (player.isInJail()) {
                handleJailTurn(player);
                //Continue au prochain joueur si le joueur est toujours en prison après handleJailTurn
                if (player.isInJail()) {
                    continue;
                }
            }
    
            int roll = dice.roll();
            int roll2 = dice.roll();
            lastDiceRoll = roll + roll2; // Stockez le total du lancer pour utilisation ultérieure
            ui.displayMessage(player.getName() + " lance les dés et obtient " + lastDiceRoll);
            player.move(lastDiceRoll);
    
            // Récupération de la case actuelle en fonction de la position du joueur
            Case currentCase = board.getCases().get(player.getCurrentPosition());
    
            // Affichage de la case sur laquelle le joueur est tombé
            ui.displayMessage(player.getName() + " atterrit sur la case " + currentCase.getNom());

            // joueur atterrit sur une de ses propriétés traditionnelles, proposer de construire.
            if (currentCase instanceof Property 
                && !((currentCase instanceof Station) || (currentCase instanceof Utility)) 
                && ((Property) currentCase).getOwner() == player) {
                manageConstructions(player, (Property) currentCase); 
            }

    
            // Décisions et actions spécifiques à la case
            if (currentCase instanceof Property) {
                Property property = (Property) currentCase;
                handlePropertyAction(player, property);
            } else {
                // autres types de cases, exécutez l'action associée.
                String actionResult = currentCase.effectuerAction(player);
                if (actionResult != null && !actionResult.isEmpty()) {
                    ui.displayMessage(actionResult);
                }
            }
        }
    }

    private void handlePropertyAction(Player player, Property property) {
        if (property.getOwner() == null) {
            ui.displayMessage("Case " + property.getNom() + " - Prix : " + property.getPrice());
            ui.displayMessage("Voulez-vous acheter cette propriété ? (Oui/Non) : ");
            String response = ui.getInput();
            if ("Oui".equalsIgnoreCase(response)) {
                if (player.getMoney() >= property.getPrice()) {
                    property.setOwner(player);
                    player.retirerArgent(property.getPrice());
                    ui.displayMessage("Vous avez acheté " + property.getNom() + ".");
                } else {
                    ui.displayMessage("Vous n'avez pas assez d'argent pour acheter cette propriété.");
                }
            } else {
                ui.displayMessage("Vous avez choisi de ne pas acheter la propriété.");
            }
        } else if (property.getOwner() != player) {
            // Le calcul du loyer peut varier en fonction du type de propriété
            int rent = 0;
            if (property instanceof Station) {
                rent = calculateStationRent(property.getOwner());
            } else if (property instanceof Utility) {
                rent = calculateUtilityRent(property.getOwner());
            } else {
                rent = property.calculateRent();
            }
            
            player.retirerArgent(rent);
            property.getOwner().ajouterArgent(rent);
            ui.displayMessage(player.getName() + " paie un loyer de " + rent + " à " + property.getOwner().getName() + " pour la propriété " + property.getNom() + ".");
        
        }
        // Pas d'action si le joueur est le propriétaire
    }
    
    

    public boolean isGameOver() {
        int playersWithMoney = 0; // Compteur pour les joueurs ayant encore de l'argent
    
        for (Player player : players) {
            if (player.getMoney() > 0) {
                playersWithMoney++; // Augmenter le compteur si le joueur a encore de l'argent
            }
        }
    
        //le jeu se termine si un seul joueur a de l'argent ou si le nombre de tours dépasse une certaine limite 
        return playersWithMoney <= 1 || rounds >= 10;
    }
    

    public void displayGameState() {
        ui.displayMessage("État du jeu :");

        for (Player player : players) {
            ui.displayMessage(player.getName() + " - Argent : " + player.getMoney() + " - Position : " + player.getCurrentPosition() + " - ");

            Case currentCase = null;
            for (Case aCase : board.getCases()) {
                if (aCase.getId() == player.getCurrentPosition()) {
                    currentCase = aCase;
                    break;
                }
            }
            if (currentCase != null) {
                if (currentCase instanceof Property) {
                    Property property = (Property) currentCase;
                    ui.displayMessage("Case " + property.getName() + " - Prix : " + property.getPrice());
                } else {
                    ui.displayMessage("Case " + currentCase.getNom());
                }
            } else {
                ui.displayMessage("Aucune case trouvée.");
            }
        }
    }

    public void displayOwnedProperties() {
        ui.displayMessage("Propriétés possédées par les joueurs :");

        for (Case aCase : board.getCases()) {
            if (aCase instanceof Property) {
                Property property = (Property) aCase;
                if (property.getOwner() != null) {
                    ui.displayMessage(property.getName() + " - Propriétaire : " + property.getOwner().getName());
                }
            }
        }
    }

    public void initializeBoard() {
        //initialisation du plateau de jeu
        board.initialize();
    }

    public void removeBankruptPlayers() {
        List<Player> bankruptPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getMoney() <= 0) {
                bankruptPlayers.add(player);
                releaseProperties(player); // Libérer les propriétés du joueur
                ui.displayMessage(player.getName() + " a été éliminé du jeu.");
            }
        }
        players.removeAll(bankruptPlayers); // Retirer tous les joueurs en faillite de la liste des joueurs actifs
    }
    
    public void releaseProperties(Player player) {
        for (Case aCase : board.getCases()) {
            if (aCase instanceof Property) {
                Property property = (Property) aCase;
                if (property.getOwner() == player) {
                    property.setOwner(null); // Libérer la propriété en réinitialisant son propriétaire
                    ui.displayMessage("La propriété " + property.getNom() + " a été libérée.");
                }
            }
        }
    }

    private int calculateStationRent(Player owner) {
        int ownedStations = 0;
        for (Case c : board.getCases()) {
            if (c instanceof Station && ((Station) c).getOwner() == owner) {
                ownedStations++;
            }
        }
        // Le loyer est calculé en fonction du nombre de gares possédées.
        return 25 * (int) Math.pow(2, ownedStations - 1);
    }
    
    private int calculateUtilityRent(Player owner) {
        int ownedUtilities = 0;
        for (Case c : board.getCases()) {
            if (c instanceof Utility && ((Utility) c).getOwner() == owner) {
                ownedUtilities++;
            }
        }
        // Le loyer pour les compagnies dépend du résultat des dés.
        int diceRoll = getLastDiceRoll(); 
        return diceRoll * (ownedUtilities == 1 ? 4 : 10);
    }
    
    public int getLastDiceRoll() {
        return lastDiceRoll;
    }
    

    private void handleJailTurn(Player player) {
        if (player.isInJail()) {
            ui.displayMessage(player.getName() + " est en prison. Tour en prison: " + player.getTurnsInJail());
    
            // Si le joueur est en prison pour le 3e tour, il doit payer pour sortir.
            if (player.getTurnsInJail() >= 3) {
                ui.displayMessage(player.getName() + " doit payer 50 pour sortir de prison car c'est le 3e tour en prison.");
                if (player.getMoney() >= 50) {
                    player.retirerArgent(50);
                    player.setInJail(false);
                    ui.displayMessage(player.getName() + " paie 50 et sort de prison.");
                    //ne se deplace pas après avoir payé.
                } else {
                    ui.displayMessage(player.getName() + " n'a pas assez d'argent et reste en prison.");
                    //si il n'a pas l'argent il reste en prison (peut-être faillite)
                }
                return; // sort de prison de cette manière.
            }
    
            // Option de payer pour sortir immédiatement, disponible avant le 3e tour
            ui.displayMessage("Payer 50 pour sortir ou essayer de lancer un double? (Payer/Double) : ");
            String response = ui.getInput();
            if ("Payer".equalsIgnoreCase(response)) {
                if (player.getMoney() >= 50) {
                    player.retirerArgent(50);
                    player.setInJail(false);
                    ui.displayMessage(player.getName() + " paie 50 et sort de prison.");
                    return; // Sortie anticipée de la méthode
                } else {
                    ui.displayMessage(player.getName() + " n'a pas assez d'argent.");
                }
            }
    
            // Tentez de lancer un double pour sortir
            int roll1 = dice.roll();
            int roll2 = dice.roll();
            ui.displayMessage(player.getName() + " lance les dés pour essayer de sortir de prison.");
            if (roll1 == roll2) {
                player.setInJail(false);
                ui.displayMessage(player.getName() + " lance un double " + roll1 + " et sort de prison.");
            } else {
                ui.displayMessage(player.getName() + " ne lance pas un double et reste en prison.");
                player.incrementTurnsInJail();
            }
        }
    }

    public static void addToFreeParkingPot(int amount) {
        freeParkingPot += amount;
    }
    
    public static int getFreeParkingPot() {
        return freeParkingPot;
    }
    
    public static void resetFreeParkingPot() {
        freeParkingPot = 0;
    }

    private void manageConstructions(Player player, Property property) {
        if (property.getOwner() == player) {
            ui.displayMessage(property.getName() + " : " + property.getHouses() + " maisons, Hôtel? " + property.hasHotel());
    
            // Demander au joueur s'il veut construire une maison ou un hôtel
            ui.displayMessage("Construire sur " + property.getName() + "? (1: Maison, 2: Hôtel, 0: Passer)");
            String response = ui.getInput();
            switch (response) {
                case "1": // Construire une maison
                    if (property.getHouses() < 4 && !property.hasHotel()) {
                        if (player.getMoney() >= property.getHouseCost()) {
                            player.retirerArgent(property.getHouseCost());
                            property.addHouse();
                            ui.displayMessage("Une maison a été ajoutée sur " + property.getName());
                        } else {
                            ui.displayMessage("Fonds insuffisants pour construire une maison.");
                        }
                    } else {
                        ui.displayMessage("Impossible de construire plus de maisons ou un hôtel est déjà présent.");
                    }
                    break;
                case "2": // Construire un hôtel
                    if (property.getHouses() == 4) {
                        if (player.getMoney() >= property.getHotelCost()) {
                            player.retirerArgent(property.getHotelCost());
                            property.buildHotel();
                            ui.displayMessage("Un hôtel a été construit sur " + property.getName());
                        } else {
                            ui.displayMessage("Fonds insuffisants pour construire un hôtel.");
                        }
                    } else {
                        ui.displayMessage("Vous devez avoir 4 maisons avant de pouvoir construire un hôtel.");
                    }
                    break;
                case "0": // Passer
                    break;
                default:
                    ui.displayMessage("Choix non valide.");
                    break;
            }
        } else {
            ui.displayMessage("Vous ne pouvez construire que sur vos propriétés.");
        }
    }
    
    
    
    

}
