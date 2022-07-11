package comp3350.team10.application;

import comp3350.team10.persistence.SharedDB;

public class Main {
    public static final String dbName = "db";
    private static String dbPathName = "database/db";
    private static String imagesPathName = "images";


    public static void startUp() {
        SharedDB.start();
    }

    public static void shutDown() {
        SharedDB.close();
    }

    public static void saveDB() {
        SharedDB.saveDB();
    }


    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
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
