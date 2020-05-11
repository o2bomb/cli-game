import controller.Game;
import model.Player;
import model.Shop;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(new Player("Bob"), new Shop());
        game.startGame();
    }
}