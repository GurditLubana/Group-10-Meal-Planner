package comp3350.team10.persistence;

public class SharedDB {
    private static DBSelector db = null;       //Represents whether or not the database has already been accessed or not
    public static String dbName;               //The name of the given database

    public static void start() {
        if (db == null) {
            db = new DBSelector();
        }
    }

    public static DBSelector getSharedDB() {
        return db;
    }

    public static LogDBInterface getLogDB() {
        return db;
    }

    public static RecipeDBInterface getRecipeDB() {
        return db;
    }

    public static UserDBInterface getUserDB() {
        return db;
    }

    public static void saveDB() {
        db.save();
    }

    public static void close() {
        db.close();
        db = null;
    }
}
