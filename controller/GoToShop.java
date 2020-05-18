package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Enchantment;
import model.Item;
import model.Player;
import model.Shop;
import model.ShopException;
import view.Menu;
import view.MenuEntry;

public class GoToShop extends Menu {
    private Shop shop;
    private Player player;

    public GoToShop(Shop shop, Game game, Menu prevMenu) {
        super("Go to shop", game, prevMenu);
        this.shop = shop;
        this.player = game.getPlayer();
        addEntry(new PurchaseAnItem(game, this));
        addEntry(new SellAnItem(game, this));
        addEntry(new AddEnchantment(game, this));
    }

    private class PurchaseAnItem extends Menu {
        public PurchaseAnItem(Game game, Menu prevMenu) {
            super("Purchase an item", game, prevMenu);
            for(Item i : shop.getStock()) {
                addEntry(new PurchaseItemEntry(i));
            }
        }

        private class PurchaseItemEntry implements MenuEntry {
            private Item item;

            public PurchaseItemEntry(Item item) {
                this.item = item;
            }

            @Override
            public String getDescription() {
                return String.format("%s || %dg", item.getDisplayName(), item.getCost());
            }

            @Override
            public boolean doAction(Scanner sc) {
                try {
                    shop.sellToPlayer(item, player);
                } catch(ShopException e) {
                    System.out.println("Failed to purchase item: " + e.getMessage());
                }
                return false;
            }
        }
    }

    private class SellAnItem extends Menu implements PlayerObserver {
        public SellAnItem(Game game, Menu prevMenu) {
            super("Sell an item", game, prevMenu);
            player.addObserver(this);
            for(Item i : player.getInventory()) {
                addEntry(new SellItemEntry(i));
            }
        }

        @Override
        public void playerUpdated() {
            List<MenuEntry> newEntries = new ArrayList<>();
            for(Item i : player.getInventory()) {
                newEntries.add(new SellItemEntry(i));
            }
            refreshEntries(newEntries);
        }

        private class SellItemEntry implements MenuEntry {
            private Item item;

            public SellItemEntry(Item item) {
                this.item = item;
            }

            @Override
            public String getDescription() {
                return String.format("%s || %dg", item.getDisplayName(), item.getCost() / 2);
            }

            @Override
            public boolean doAction(Scanner sc) {
                try {
                    shop.buyFromPlayer(item, player);
                } catch(ShopException e) {
                    System.out.println("Failed to sell item: " + e.getMessage());
                }
                return false;
            }
        }
    }

    private class AddEnchantment extends Menu {
        public AddEnchantment(Game game, Menu prevMenu) {
            super("Add enchantment to weapon", game, prevMenu);
            for(Enchantment e : shop.getEnchantments()) {
                addEntry(new AddEnchantmentEntry(e));
            }
        }

        private class AddEnchantmentEntry implements MenuEntry {
            private Enchantment enchantment;

            public AddEnchantmentEntry(Enchantment enchantment) {
                this.enchantment = enchantment;
            }

            @Override
            public String getDescription() {
                return String.format("%s || %dg", enchantment.getDisplayName(), enchantment.getCost());
            }

            @Override
            public boolean doAction(Scanner sc) {
                try {
                    shop.sellToPlayer(enchantment, player);
                } catch(ShopException e) {
                    System.out.println("Failed to sell enchantment: " + e.getMessage());
                }
                return false;
            }
        }
    }
}