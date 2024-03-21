public class ChanceCase extends Case {
    public ChanceCase(String nom) {
        super(nom);
    }

    @Override
    public void effectuerAction(Player joueur) {
        int action = (int) (Math.random() * 8); // Génère un nombre aléatoire entre 0 et 7
        switch (action) {
            case 0:
                System.out.println(joueur.getName() + " avance de 5 cases.");
                joueur.move(5);
                break;
            case 1:
                System.out.println(joueur.getName() + " recule de 3 cases.");
                joueur.move(-3);
                break;
            case 2:
                System.out.println(joueur.getName() + " reçoit 50.");
                joueur.ajouterArgent(50);
                break;
            case 3:
                System.out.println(joueur.getName() + " perd 75.");
                joueur.retirerArgent(75);
                break;
            case 4:
                System.out.println(joueur.getName() + " avance à la case départ.");
                joueur.setCurrentPosition(0);
                break;
            case 5:
                System.out.println(joueur.getName() + " échange de position avec le dernier joueur.");
                //a faire plus tard
                break;
            case 6:
                // Simule un nouveau lancer de dés
                int roll = (int) (Math.random() * 6) + 1; 
                System.out.println(joueur.getName() + " double son lancer de dés et avance de " + (roll) + " cases.");
                joueur.move(roll);
                break;
            case 7:
                System.out.println(joueur.getName() + " paie des frais de 50.");
                joueur.retirerArgent(50);
                break;
            default:
                System.out.println("Erreur: action non définie.");
                break;
        }
    }
}