package model;

import java.io.IOException;
import java.util.ArrayList;

public interface ShopLoader {
    public ArrayList<Item> readItems() throws IOException;
}