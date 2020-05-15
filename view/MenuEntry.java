package view;

import java.util.Scanner;

public interface MenuEntry {
    public String getDescription();
    /**
     * The action performed by the menu entry. Returns an boolean indicating
     * whether the parent menu entry object should exit as well
     * @param sc
     * @return If true, then the parent should exit
     */
    public boolean doAction(Scanner sc);
}