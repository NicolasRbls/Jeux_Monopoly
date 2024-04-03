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
                int lostMoney1 = 20;
                joueur.retirerArgent(lostMoney1);
                MonopolyGame.addToFreeParkingPot(lostMoney1); //ajout de l'argent perdu au pot du Parc Gratuit
                return joueur.getName() + " perd 20. L'argent va dans le pot du Parc Gratuit.";
            case 2:
                joueur.ajouterArgent(200);
                return joueur.getName() + " reçoit 200.";
            case 3:
                int anniv = 150;
                joueur.ajouterArgent(anniv);
                return joueur.getName() + " fête son anniversaire et reçoit " + anniv + ".";
            case 4:
                int montantHeritage = 100;
                joueur.ajouterArgent(montantHeritage);
                return joueur.getName() + " gagne un petit héritage et reçoit " + montantHeritage + ".";
            case 5:
                joueur.move(-4);
                return joueur.getName() + " recule de 4 cases.";
            case 6:
                int repairCosts = 100; //coût
                joueur.retirerArgent(repairCosts);
                MonopolyGame.addToFreeParkingPot(repairCosts); //ajout les coûts de réparation au pot du Parc Gratuit
                return joueur.getName() + " a des réparations à payer pour ses maisons. L'argent va dans le pot du Parc Gratuit.";
            case 7:
                joueur.ajouterArgent(50);
                return joueur.getName() + " gagne un concours de beauté et reçoit 50.";
            default:
                return "Erreur: action non définie.";
        }
    }
}
