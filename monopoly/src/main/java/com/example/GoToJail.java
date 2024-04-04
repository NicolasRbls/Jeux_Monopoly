package com.example;

public class GoToJail extends Case {
    public GoToJail() {
        super("Aller en Prison");
    }

    @Override
    public String effectuerAction(Player joueur) {
        joueur.setInJail(true); //Marque le joueur comme étant en prison
        joueur.setCurrentPosition(Board.JAIL_POSITION); //Déplace le joueur en prison
        return joueur.getName() + " va directement en prison et est placé sur la case prison!";
    }
}
