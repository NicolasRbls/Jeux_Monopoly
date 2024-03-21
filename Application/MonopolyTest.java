public class MonopolyTest {

    public static void main(String[] args) {
        IUserInterface ui = new ConsoleUserInterface();
        MonopolyGame game = new MonopolyGame(ui); 
        game.playGame();
    }
    
}
