package comp3350.team10.persistence;

import android.content.Context;

public class SharedDB {
    //DBSelector(Context context)
    private static DBSelector db = null;   //Represents whether or not the database has already been accessed or not
    public static String dbName;               //The name of the given database

    public static void start(Context context) {
        if (db == null) {
            db = new DBSelector(context);
        }
    }

    public static DBSelector getSharedDB() {
        return db;
    }

    public static void close() {
        if (db != null) {
            db = null;
        }
    }
}
