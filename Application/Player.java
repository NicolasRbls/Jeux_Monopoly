public class Player {
    private String name;
    private int money;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        money = 1500;
        currentPosition = 0;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void move(int steps) {
        currentPosition += steps;
        if (currentPosition >= Board.NUM_CASES) {
            currentPosition -= Board.NUM_CASES;
        }
    }

    public void ajouterArgent(int amount) {
        money += amount;
    }

    public void retirerArgent(int amount) {
        if (money >= amount) {
            money -= amount;
        } else {
            System.out.println("Attention, " + name + " n'a pas assez d'argent pour effectuer cette opération.");
        }
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }
}