package model;

public class ShopFactory {
    private static ShopLoader testLoader = null;
    static void setTestLoader(ShopLoader tLoader) {
        testLoader = tLoader;
    }

    public static ShopLoader makeLoader(String location) {
        if(testLoader == null) {
            ShopLoader loader = null;
            if(location.endsWith(".txt")) {
                loader = new FileLoader(location);
            }
            return loader;
        } else {
            return testLoader;
        }
    }
}