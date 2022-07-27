package comp3350.team10.application;

import comp3350.team10.persistence.DBSelector;

public class Main {
    private static final String dbName = "db";
    private static String dbPathName = "database/db";
    private static String imagesPathName = "images";


    public static void startUp() {
        DBSelector.start();
    }

    public static void shutDown() {
        DBSelector.close();
    }

    public static void saveDB() {
        DBSelector.save();
    }
    
    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static String getDBName() {
        return dbName;
    }

    public static void setDBPathName(String pathName) {
        dbPathName = pathName;
    }

    public static void setImagesPathName(String pathName) {
        imagesPathName = pathName;
    }

    public static String getImagesPathName() {
        return imagesPathName;
    }
}
