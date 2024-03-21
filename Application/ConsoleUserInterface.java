import java.util.Scanner;

public class ConsoleUserInterface implements IUserInterface {
    private Scanner scanner;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public void displayError(String message) {
        System.err.println(message);
    }
}
