public class GoToJail extends Case {
    public GoToJail() {
        super("Aller en Prison");
    }

    @Override
    public String effectuerAction(Player joueur) {
        joueur.setInJail(true); // Marquez le joueur comme étant en prison
        joueur.setCurrentPosition(Board.JAIL_POSITION); // Déplacez le joueur en prison
        return joueur.getName() + " va directement en prison et est placé sur la case prison!";
    }
}
