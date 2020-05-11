package model;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.InsufficientResourcesException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Shop {
    private ArrayList<Item> stock;

    public Shop() {
        this.stock = new ArrayList<>();
    }

    public List<Item> getStock() {
        // The shop's stock should not be able to be modified by outside parties
        return Collections.unmodifiableList(stock);
    }

    public void loadInStock(String source) {
        ShopLoader loader = ShopFactory.makeLoader(source);

        try {
            stock = loader.readItems();
        } catch (IOException e) {
            System.out.println("Failed to load items into shop: " + e.getMessage());
        }
    }

    /**
     * Handles the selling of the specified item to the player. If the item is not in stock,
     * a NoSuchElementException is thrown. If the player does not have enough gold, the 
     * transaction fails
     * @param item
     * @param player
     * @throws NoSuchElementException
     */
    public void sellToPlayer(Item item, Player player) throws NoSuchElementException {
        if(!stock.contains(item)) {
            throw new NoSuchElementException("Item does not exist in stock");
        }

        try {
            player.payGold(item.getCost());
            if(item instanceof Enchantment) {
                // If the item is an enchantment, enchant the player's equipped weapon
                player.addEnchantmentToEquippedWeapon((Enchantment)item);
            } else {
                // Else, add it to the player's inventory
                player.addItem(item);
            }
        } catch(InsufficientResourcesException e) {
            System.out.println("Unable to purchase item from shop: " + e.getMessage());
        }
    }
    
    /**
     * Handles the buying of an item from the player. If the item does not exist in the 
     * player's inventory, a NoSuchElementException is thrown
     * @param item
     * @param player
     * @throws NoSuchElementException
     */
    public void buyFromPlayer(Item item, Player player) throws NoSuchElementException {
        if(!player.getInventory().contains(item)) {
            throw new NoSuchElementException("Item does not exist in player's inventory. Make sure it is not equipped!");
        }
        player.getInventory().remove(item);
        player.addGold(item.getCost() / 2);
    }
}