import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MonopolyGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private int rounds;

    public MonopolyGame() {
        board = new Board();
        players = new ArrayList<>();
        dice = new Dice();
        rounds = 0;
    }

    public void playGame() {
        initializeBoard();
        initializePlayers();

        while (!isGameOver()) {
            startRound();
            displayGameState();
            rounds++;
        }

        System.out.println("Le jeu est terminé !");
    }

    public void initializePlayers() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Combien de joueurs ? ");
            int numPlayers = scanner.nextInt();
    
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Nom du joueur " + (i + 1) + ": ");
                String playerName = scanner.next();
                Player player = new Player(playerName);
                players.add(player);
            }
        } // le scanner sera fermé automatiquement à la fin du bloc try
    }
    

    public void initializeBoard() {
        // Initialisation du plateau de jeu (ajoutez des cases, des propriétés, etc.)
        board.initialize();
    }

    public void startRound() {
        System.out.println("Tour " + (rounds + 1));
        
        for (Player player : players) {
            int roll = dice.roll();
            System.out.println(player.getName() + " lance les dés et obtient " + roll);
            player.move(roll);
            // Gérer l'achat de propriétés, les paiements de loyer, etc.
        }
    }

    public boolean isGameOver() {
        return rounds >= 10; // Exemple : jeu terminé après 10 tours
    }

    public void displayGameState() {
        System.out.println("État du jeu :");
    
        // Afficher les informations des cases
        for (Case aCase : board.getCases()) {
            if (aCase instanceof Property) {
                Property property = (Property) aCase;
                System.out.println("Propriété " + property.getName() + " - Prix : " + property.getPrice());
            } else {
                System.out.println("Case " + aCase.getNom());
            }
        }
    
        // Afficher les informations des joueurs
        for (Player player : players) {
            System.out.println(player.getName() + " - Argent : " + player.getMoney() + " - Position : " + player.getCurrentPosition());
        }
    }
    
    

    public static void main(String[] args) {
        MonopolyGame game = new MonopolyGame();
        game.playGame();
    }
}
