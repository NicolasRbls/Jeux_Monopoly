import java.util.ArrayList;
import java.util.List;

public class MonopolyGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int rounds;
    private IUserInterface ui;


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
            int roll = dice.roll();
            ui.displayMessage(player.getName() + " lance les dés et obtient " + roll);
            player.move(roll);
    
            // Récupération de la case actuelle en fonction de la position du joueur
            Case currentCase = board.getCases().get(player.getCurrentPosition());
    
            // Affichage de la case sur laquelle le joueur est tombé
            ui.displayMessage(player.getName() + " atterrit sur la case " + currentCase.getNom());

            // Action spécifique à la case
            if (currentCase instanceof Property) {
                Property property = (Property) currentCase;
                if (property.getOwner() == null) {
                    ui.displayMessage("Case " + property.getNom() + " - Prix : " + property.getPrice());

                    ui.displayMessage("Voulez-vous acheter cette propriété ? (Oui/Non) : ");
                    String response = ui.getInput();

                    if (response.equalsIgnoreCase("Oui")) {
                        property.effectuerAction(player); // Le joueur décide d'acheter la propriété
                    } else {
                        ui.displayMessage("Vous avez choisi de ne pas acheter la propriété.");
                    }
                } else {
                    //si la propriété a déjà un propriétaire payer le loyer
                    property.effectuerAction(player);
                }
            } else {
                //exécuter simplement l'action de la case
                currentCase.effectuerAction(player);
            }
        }
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

}
