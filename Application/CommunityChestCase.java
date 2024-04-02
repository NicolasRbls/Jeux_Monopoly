public class CommunityChestCase extends Case {
    public CommunityChestCase(String nom) {
        super(nom);
    }

    @Override
    public String effectuerAction(Player joueur) {
        int action = (int) (Math.random() * 8); // Génère un nombre aléatoire entre 0 et 7
        switch (action) {
            case 0:
                joueur.ajouterArgent(100);
                return joueur.getName() + " gagne 100.";
            case 1:
                joueur.retirerArgent(20);
                return joueur.getName() + " perd 20.";
            case 2:
                joueur.ajouterArgent(200);
                return joueur.getName() + " reçoit 200.";
            case 3:
                // Logique pour payer 50 à chaque joueur. Doit être gérée dans MonopolyGame.
                return joueur.getName() + " doit payer 50 à chaque joueur.";
            case 4:
                int montantHeritage = 100;
                joueur.ajouterArgent(montantHeritage);
                return joueur.getName() + " gagne un petit héritage et reçoit " + montantHeritage + " en héritage.";
            case 5:
                joueur.move(-4);
                return joueur.getName() + " recule de 4 cases.";
            case 6:
                // Logique pour les réparations à payer. Doit être gérée dans MonopolyGame.
                return joueur.getName() + " a des réparations à payer pour ses maisons.";
            case 7:
                joueur.ajouterArgent(50);
                return joueur.getName() + " gagne un concours de beauté et reçoit 50.";
            default:
                return "Erreur: action non définie.";
        }
    }
}
