package controller;

import java.util.Scanner;

import model.Player;
import model.Shop;
import view.Menu;

public class Game {
    private Player player;
    private Shop shop;

    public Game(Player player, Shop shop) {
        this.player = player;
        this.shop = shop;
    }

    public void startGame() {
        shop.loadInStock("shop.txt");
        displayMenus();
    }

    public void endGame() {
        System.exit(0);
    }

    private void displayMenus() {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu("Base menu");
        menu.addEntry(new ExitGame(this));
        menu.addEntry(new GoToShop(shop, player, menu));
        menu.addEntry(new ChoosePlayerName(player));
        menu.addEntry(new ChooseWeapon(player, menu));
        menu.addEntry(new ChooseArmour(player, menu));
        menu.doAction(sc);
        sc.close();
    }
}