package controller;

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
        displayMenus();
        shop.loadInStock("shop.txt");
    }

    private void displayMenus() {
        Menu menu = new Menu("Base menu");
        menu.addEntry(new GoToShop(shop, player, menu));
        menu.addEntry(new ChoosePlayerName(player));
        menu.addEntry(new ChooseWeapon(player, menu));
        menu.doAction();
    }
}