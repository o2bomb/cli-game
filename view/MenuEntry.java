package view;

import java.util.Scanner;

public interface MenuEntry {
    public String getDescription();
    public void doAction(Scanner sc);
}