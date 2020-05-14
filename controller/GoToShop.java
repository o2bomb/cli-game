package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Enchantment;
import model.Item;
import model.Player;
import model.Shop;
import view.Menu;
import view.MenuEntry;

public class GoToShop extends Menu {
    private Shop shop;
    private Player player;

    public GoToShop(Shop shop, Player player, Menu prevMenu) {
        super("Go to shop", prevMenu);
        this.shop = shop;
        this.player = player;
        addEntry(new PurchaseAnItem(this));
        addEntry(new SellAnItem(this));
        addEntry(new AddEnchantment(this));
    }

    private class PurchaseAnItem extends Menu {
        public PurchaseAnItem(Menu prevMenu) {
            super("Purchase an item", prevMenu);
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
                return item.getDisplayName();
            }

            @Override
            public void doAction(Scanner sc) {
                try {
                    shop.sellToPlayer(item, player);
                } catch(NoSuchElementException e) {
                    System.out.println("Failed to purchase item: " + e.getMessage());
                }
            }
        }
    }

    private class SellAnItem extends Menu implements PlayerObserver {
        public SellAnItem(Menu prevMenu) {
            super("Sell an item", prevMenu);
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
                return item.getDisplayName();
            }

            @Override
            public void doAction(Scanner sc) {
                try {
                    shop.buyFromPlayer(item, player);
                } catch(NoSuchElementException e) {
                    System.out.println("Failed to sell item: " + e.getMessage());
                }
            }
        }
    }

    private class AddEnchantment extends Menu {
        public AddEnchantment(Menu prevMenu) {
            super("Add enchantment to weapon", prevMenu);
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
                return enchantment.getDisplayName();
            }

            @Override
            public void doAction(Scanner sc) {
                try {
                    shop.sellToPlayer(enchantment, player);
                } catch(NoSuchElementException e) {
                    System.out.println("Failed to sell enchantment: " + e.getMessage());
                }
            }
        }
    }
}