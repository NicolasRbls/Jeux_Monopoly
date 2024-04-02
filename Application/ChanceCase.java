public class ChanceCase extends Case {
    public ChanceCase(String nom) {
        super(nom);
    }

    @Override
    public String effectuerAction(Player joueur) {
        int action = (int) (Math.random() * 8); // Génère un nombre aléatoire entre 0 et 7
        switch (action) {
            case 0:
                joueur.move(5);
                return joueur.getName() + " avance de 5 cases.";
            case 1:
                joueur.move(-3);
                return joueur.getName() + " recule de 3 cases.";
            case 2:
                joueur.ajouterArgent(50);
                return joueur.getName() + " reçoit 50.";
            case 3:
                joueur.retirerArgent(75);
                return joueur.getName() + " perd 75.";
            case 4:
                joueur.setCurrentPosition(0);
                return joueur.getName() + " avance à la case départ.";
            case 5:
                // Implementation future
                return joueur.getName() + " échange de position avec le dernier joueur.";
            case 6:
                int roll = (int) (Math.random() * 6) + 1;
                joueur.move(roll);
                return joueur.getName() + " double son lancer de dés et avance de " + roll + " cases.";
            case 7:
                joueur.retirerArgent(50);
                return joueur.getName() + " paie des frais de 50.";
            default:
                return "Erreur: action non définie.";
        }
    }
}
