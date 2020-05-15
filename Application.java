import java.util.LinkedList;
import java.util.List;

import controller.Game;
import model.Dragon;
import model.Enemy;
import model.Goblin;
import model.Ogre;
import model.Player;
import model.Shop;
import model.Slime;

public class Application {
    public static void main(String[] args) {
        List<Enemy> enemies = new LinkedList<Enemy>();
        enemies.add(new Slime());
        enemies.add(new Goblin());
        enemies.add(new Ogre());
        enemies.add(new Dragon());
        Game game = new Game(new Player("Bob"), new Shop(), enemies);
        game.startGame();
    }
}