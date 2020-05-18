package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Dragon;
import model.Enemy;
import model.Player;
import model.Potion;
import view.Menu;
import view.MenuEntry;

public class StartBattle extends Menu {
    private Game game;
    private Player player;
    private Enemy enemy;

    public StartBattle(Game game) {
        super("Start battle", game);
        this.game = game;
        this.player = game.getPlayer();
        this.enemy = null;
        addEntry(new AttackWith());
        addEntry(new UseAPotion(this));
    }

    public String displayEnemyInfo() {
        String display = new String();
        display += "========================= ENEMY STATS =========================\n";
        display += String.format("Name: %s\n", enemy.getName());
        display += String.format("Health: %d / %d\n", enemy.getCurrHealth(), enemy.getMaxHealth());
        display += String.format("Gold: %d\n", enemy.getGold());
        display += String.format("Special ability: %s\n", enemy.getSpecialDescription());
        display += String.format("Chance of encounter: %d\n", enemy.getProbability());
        display += "================================================================\n";

        return display;
    }

    @Override
    public boolean doAction(Scanner sc) {
        // Get a fresh new enemy
        this.enemy = game.getEnemy();
        System.out.println(String.format("An enemy appears! Its a %s", enemy.getName()));
        while(true) {
            System.out.println("Select your move:");
            System.out.println(displayMenu());
            System.out.println(displayPlayerInfo());
            System.out.println(displayEnemyInfo());
            try {
                int choice = Integer.parseInt(sc.nextLine());
                clearScreen();
                select(choice, sc);
                if(enemy.getCurrHealth() == 0) {
                    // The enemy dies
                    System.out.println(String.format("The %s has been slain!", enemy.getName()));
                    // Award the player gold
                    player.addGold(enemy.getGold());
                    // Increase the players health by 1.5x (3/2)
                    player.doHeal(Math.min(player.getMaxHealth(), (player.getCurrHealth() * 3) / 2));
                    if(enemy instanceof Dragon) {
                        // The player has defeated the dragon; they win the game
                        game.winGame();
                        game.endGame();
                    }
                    // Update enemy probability
                    enemy.updateProbability();
                    // End the battle
                    break;
                }
                if(enemy.usingSpecial()) {
                    System.out.println(String.format("The %s is using their special ability!", enemy.getName()));
                }
                System.out.println(String.format("The %s deals %d damage to you.", enemy.getName(), player.doDefend(enemy.getDamage())));
                if (player.getCurrHealth() == 0) {
                    // The player dies
                    System.out.println("You have been slain!");
                    // End the game
                    game.endGame();
                    // End the battle
                    break;
                }
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Your choice does not exist.");
            } catch(NumberFormatException e) {
                System.out.println("Your choice must be a number.");
            } catch(NoSuchElementException e) {
                System.out.println("Your choice cannot be empty.");
            } catch(IllegalStateException e) {
                System.out.println("Choice scanner failed. Please try again: " + e.getMessage());
            }
        }
        return false;
    }

    private class AttackWith implements MenuEntry {
        public AttackWith() {}

        @Override
        public String getDescription() {
            return "Attack with " + player.getEquippedWeapon().getDisplayName();
        }

        @Override
        public boolean doAction(Scanner sc) {
            int damage = enemy.doDefend(player.getDamage());
            System.out.println(String.format("You deal %d damage to the %s.", damage, enemy.getName()));
            return false;
        }
    }

    private class UseAPotion extends Menu implements PlayerObserver {
        public UseAPotion(MenuEntry prevMenu) {
            super("Use a potion", game, prevMenu);
            player.addObserver(this);
            for(Potion p : player.getPotions()) {
                addEntry(new UseAPotionEntry(p));
            }
        }

        @Override
        public void playerUpdated() {
            List<MenuEntry> newEntries = new ArrayList<>();
            for(Potion p : player.getPotions()) {
                newEntries.add(new UseAPotionEntry(p));
            }
            refreshEntries(newEntries);
        }

        private class UseAPotionEntry implements MenuEntry {
            private Potion potion;

            public UseAPotionEntry(Potion potion) {
                this.potion = potion;
            }

            @Override
            public String getDescription() {
                return potion.getDisplayName();
            }

            @Override
            public boolean doAction(Scanner sc) {
                System.out.println(String.format("You use a %s.", potion.getDisplayName()));
                int amount = potion.getEffect();
                if(potion.getType() == 'h') {
                    System.out.println(String.format("It heals you for %d health.", amount));
                    player.doHeal(amount);
                } else if(potion.getType() == 'd') {
                    System.out.println(String.format("It deals %d damage to the %s.", amount, enemy.getName()));
                    enemy.doDefend(amount);
                } else {
                    System.out.println("It does nothing.");
                }
                System.out.println(String.format("While you were rummaging through your bag for potions, the %s attacked you!", enemy.getName()));
                player.removeItem(potion);
                return true;
            }
        }
    }
}