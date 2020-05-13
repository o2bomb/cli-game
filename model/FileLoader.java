package model;

import java.lang.Character;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLoader implements ShopLoader {
    private String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the item file, and returns all items within from that file as a list
     * @return A list of all items read from the file
     * @throws IOException When file reading fails
     */
    @Override
    public ArrayList<Item> readItems() throws IOException {
        ArrayList<Item> items = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                // Trim leading and trailing whitespace from each part
                for(int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                char identifier = Character.toLowerCase(parts[0].charAt(0));

                Item newItem = null;
                switch(identifier) {
                    case 'w':
                        String wName =  parts[1];
                        int minDamage = Integer.parseInt(parts[2]);
                        int maxDamage = Integer.parseInt(parts[3]);
                        int wCost = Integer.parseInt(parts[4]);
                        String damageType = parts[5];
                        String weaponType = parts[6];

                        newItem = new BaseWeapon(wName, wCost, minDamage, maxDamage, weaponType, damageType);
                        break;
                    case 'a':
                        String aName =  parts[1];
                        int minDefence = Integer.parseInt(parts[2]);
                        int maxDefence = Integer.parseInt(parts[3]);
                        int aCost = Integer.parseInt(parts[4]);
                        String material = parts[5];

                        newItem = new Armour(aName, aCost, material, minDefence, maxDefence);
                        break;
                    case 'p':
                        String pName =  parts[1];
                        int minEffect = Integer.parseInt(parts[2]);
                        int maxEffect = Integer.parseInt(parts[3]);
                        int pCost = Integer.parseInt(parts[4]);
                        char potionType = Character.toLowerCase(parts[5].charAt(0));

                        newItem = new Potion(pName, pCost, minEffect, maxEffect, potionType);
                        break;
                    default:
                        throw new IOException("Invalid file format/contents");
                }
                items.add(newItem);

                line = reader.readLine();
            }
            reader.close();
        } catch(NumberFormatException e) {
            throw new IOException("Invalid file format/contents");
        }

        return items;
    }
    
}