public class EmptyCase extends Case {
    public EmptyCase(String nom) {
        super(nom);
    }

    @Override
    public void effectuerAction(Player joueur) {
        System.out.println(joueur.getName() + " est sur " + getNom() + ". Aucune action requise.");
    }
}
