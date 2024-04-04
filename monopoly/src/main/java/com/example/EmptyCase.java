package com.example;

public class EmptyCase extends Case {
    public EmptyCase(String nom) {
        super(nom);
    }

    @Override
    public String effectuerAction(Player joueur) {
        // Retourne simplement un message indiquant que le joueur est sur cette case, sans action supplémentaire.
        return joueur.getName() + " est sur " + getNom() + ". Aucune action requise.";
    }
}
