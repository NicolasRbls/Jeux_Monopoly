package com.example;

public class FreeParking extends Case {
    public FreeParking() {
        super("Parc Gratuit");
    }

    @Override
    public String effectuerAction(Player joueur) {
        int amount = MonopolyGame.getFreeParkingPot();
        joueur.ajouterArgent(amount);
        MonopolyGame.resetFreeParkingPot();
        return joueur.getName() + " récupère " + amount + " du Parc Gratuit.";
    }
}
