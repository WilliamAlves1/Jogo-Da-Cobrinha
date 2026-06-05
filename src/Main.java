import ModeloCobra.Ranking;
import br.edu.view.MenuPrincipal;

public class Main {
    public static void main(String[] args) {

        // instanciando a classe Ranking
        Ranking ranking = new Ranking();

        MenuPrincipal menu = new MenuPrincipal(ranking);

        menu.setVisible(true);

    }
}