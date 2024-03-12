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
                player.setCurrentPosition(0);
                players.add(player);
            }
        }
    }

    public void initializeBoard() {
        board.initialize();
    }

    public void startRound() {
        System.out.println("Tour " + (rounds + 1));

        for (Player player : players) {
            int roll = dice.roll();
            System.out.println(player.getName() + " lance les dés et obtient " + roll);
            player.move(roll);

            // Trouve la case correspondant à la position actuelle du joueur
            Case currentCase = board.getCases().get(player.getCurrentPosition());
            currentCase.effectuerAction(player);
        }
    }

    public boolean isGameOver() {
        return rounds >= 10;
    }

    public void displayGameState() {
        System.out.println("État du jeu :");

        for (Player player : players) {
            System.out.println(player.getName() + " - Argent : " + player.getMoney() + " - Position : " + player.getCurrentPosition() + " - Case : " + board.getCases().get(player.getCurrentPosition()).getNom());
        }
    }

    public static void main(String[] args) {
        MonopolyGame game = new MonopolyGame();
        game.playGame();
    }
}