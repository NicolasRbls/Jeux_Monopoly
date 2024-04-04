package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    @Test
    void testAjouterArgent() {
        Player player = new Player("TestPlayer");
        player.ajouterArgent(500);
        assertEquals(2000, player.getMoney()); // Vérifie si l'argent est correctement ajouté
    }

    @Test
    void testGetCurrentPosition() {
        Player player = new Player("TestPlayer");
        assertEquals(0, player.getCurrentPosition()); // La position initiale doit être 0
    }

    @Test
    void testGetMoney() {
        Player player = new Player("TestPlayer");
        assertEquals(1500, player.getMoney()); // Vérifie si le montant initial est correct
    }

    @Test
    void testGetName() {
        Player player = new Player("TestPlayer");
        assertEquals("TestPlayer", player.getName()); // Vérifie si le nom est correct
    }

    @Test
    void testGetTurnsInJail() {
        Player player = new Player("TestPlayer");
        assertEquals(0, player.getTurnsInJail()); // Le nombre de tours en prison doit être initialisé à 0
    }

    

    @Test
    void testIsInJail() {
        Player player = new Player("TestPlayer");
        assertFalse(player.isInJail()); // Au début, le joueur ne devrait pas être en prison
    }

    @Test
    void testMove() {
        Player player = new Player("TestPlayer");
        player.move(5); // Simule un mouvement de 5 cases
        assertEquals(5, player.getCurrentPosition()); // Vérifie si le mouvement a été effectué correctement
    }

    @Test
    void testRetirerArgent() {
        Player player = new Player("TestPlayer");
        player.retirerArgent(200);
        assertEquals(1300, player.getMoney()); // Vérifie si l'argent est correctement retiré
    }

    @Test
    void testSetCurrentPosition() {
        Player player = new Player("TestPlayer");
        player.setCurrentPosition(10);
        assertEquals(10, player.getCurrentPosition()); // Vérifie si la position actuelle est correctement définie
    }

    @Test
    void testSetInJail() {
        Player player = new Player("TestPlayer");
        player.setInJail(true);
        assertTrue(player.isInJail()); // Vérifie si le joueur est correctement mis en prison
    }
}
