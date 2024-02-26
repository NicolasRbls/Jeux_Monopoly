public class Player {
    private String name;
    private int money;
    private int currentPosition;

    public Player(String name) {
        this.name = name;
        money = 1500; // Argent initial du joueur
        currentPosition = 0; // Position de départ
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
        //deplacez le joueur en fonction du nombre obtenu lors du lancer de dés
        currentPosition += steps;
        if (currentPosition >= Board.NUM_CASES) {
            currentPosition -= Board.NUM_CASES; //revient au début du plateau si nécessaire
        }
    }    

    public void ajouterArgent(int amount) {
        money += amount;
    }

    public void retirerArgent(int amount) {
        if (money >= amount) {
            money -= amount;
        } else {
            //gerer la situation où le joueur n'a pas assez d'argent
            System.out.println("Attention, " + name + " n'a pas assez d'argent pour effectuer cette opération.");
        }
    }

    public void setCurrentPosition(int position) {
        this.currentPosition = position;
    }
    

}
