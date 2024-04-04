package com.example;

public class Player {
    private String name;
    private int money;
    private int currentPosition;
    private boolean inJail = false;
    private int turnsInJail = 0;

    public Player(String name) {
        this.name = name;
        money = 1500; //argent initial 
        currentPosition = 0; //position de départ
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

    // Méthodes pour gérer le statut en prison
    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
        this.turnsInJail = inJail ? 1 : 0; // Réinitialiser le compte des tours en prison
    }

    public void incrementTurnsInJail() {
        if (inJail) {
            turnsInJail++;
        }
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }
    

}
