package com.example;

public class Tax extends Case {
    private final int taxAmount;

    public Tax(String name, int taxAmount) {
        super(name);
        this.taxAmount = taxAmount;
    }

    @Override
    public String effectuerAction(Player joueur) {
        joueur.retirerArgent(taxAmount);
        MonopolyGame.addToFreeParkingPot(taxAmount);
        return joueur.getName() + " paie " + taxAmount + " en impôts.";
    }
}
