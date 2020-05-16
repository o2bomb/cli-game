package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import model.Enemy;
import model.Player;
import model.Shop;
import view.Menu;

public class Game {
    private Scanner sc;
    private Player player;
    private Shop shop;
    private ArrayList<Enemy> enemies;
    private boolean isWon;

    public Game(Player player, Shop shop, List<Enemy> enemies) throws NullPointerException {
        // Throws NullPointerException if enemies is null or empty
        if(enemies.size() == 0) {
            throw new NullPointerException("enemies cannot be empty");
        }
        this.enemies = new ArrayList<>(Objects.requireNonNull(enemies));
        this.sc = new Scanner(System.in);
        this.player = player;
        this.shop = shop;
        this.isWon = false;
    }

    public void startGame() {
        shop.loadInStock("shop.txt");
        displayMenus();
    }

    public void endGame() {
        if(isWon) {
            System.out.println("You leave the game, having slain the dragon. The dragon no longer reigns over this world and its citizens, and hopefully they continue to live in a world of peace.");
        } else if(player.getCurrHealth() == 0) {
            System.out.println("Your consciousness is booted from the game. The citizens of this world continue to be terrorised by the dragon.");
        } else {
            System.out.println("You escape the game alive, leaving the dragon free will to terrorize the citizens of this world.");
        }
        sc.close();
        System.exit(0);
    }
    
    private void displayMenus() {
        Menu menu = new Menu("Base menu", this);
        menu.addEntry(new ExitGame(this));
        menu.addEntry(new GoToShop(shop, this, menu));
        menu.addEntry(new ChoosePlayerName(player));
        menu.addEntry(new ChooseWeapon(this, menu));
        menu.addEntry(new ChooseArmour(this, menu));
        menu.addEntry(new StartBattle(this));
        menu.doAction(sc);
    }

    public void winGame() {
        isWon = true;
    }

    public boolean isWon() {
        return isWon;
    } 

    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return The enemy that is most likely to appear next
     */
    public Enemy getEnemy() {
        int i = 0;
        if(enemies.size() > 1) {
            while(true) {
                i = (int)(Math.random() * (enemies.size()));
                Enemy e = enemies.get(i);
                if(Math.random() < (double)e.getProbability() / 100.0) {
                    int j = 0;
                    while(j < enemies.size()) {
                        if(j != i) {
                            enemies.get(j).updateProbability();
                        }
                        j++;
                    }
                    e.fullHeal();
                    return e;
                }
            }
        }
        enemies.get(0).fullHeal();
        return enemies.get(0);
    }
}