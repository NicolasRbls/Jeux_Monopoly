public class CommunityChestCase extends Case {
    public CommunityChestCase(String nom) {
        super(nom);
    }

    @Override
    public void effectuerAction(Player joueur) {
        int action = (int) (Math.random() * 8); // Génère un nombre aléatoire entre 0 et 7
        switch (action) {
            case 0:
                System.out.println(joueur.getName() + " gagne 100.");
                joueur.ajouterArgent(100);
                break;
            case 1:
                System.out.println(joueur.getName() + " perd 20.");
                joueur.retirerArgent(20);
                break;
            case 2:
                System.out.println(joueur.getName() + " reçoit 200.");
                joueur.ajouterArgent(200);
                break;
            case 3:
                System.out.println(joueur.getName() + " doit payer 50 à chaque joueur.");
                //je fait plus tard
                break;
            case 4:
                System.out.println(joueur.getName() + " gagne un petit héritage.");
                int montantHeritage = 100; 
                joueur.ajouterArgent(montantHeritage);
                System.out.println(joueur.getName() + " reçoit " + montantHeritage + " en héritage.");
                break;
            case 5:
                System.out.println(joueur.getName() + " recule de 4 cases.");
                joueur.move(-4);
                break;
            case 6:
                System.out.println(joueur.getName() + " a des réparations à payer pour ses maisons.");
                //plus tard
                break;
            case 7:
                System.out.println(joueur.getName() + " gagne un concours de beauté. Reçoit 50.");
                joueur.ajouterArgent(50);
                break;
            default:
                System.out.println("Erreur: action non définie.");
                break;
        }
    }
}