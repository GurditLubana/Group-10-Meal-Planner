package comp3350.team10.persistence;

public class SharedDB {
    private static DataAccessStub db = null;    //Represents whether or not the database has already been accessed or not
    public static String dbName;               //The name of the given database

    public static void start(String dbname) {
        if (db == null) {
            db = new DataAccessStub(dbname);
            SharedDB.dbName = dbname;
            db.open(dbname);
        }
    }

    public static DataAccessStub getSharedDB() {
        return db;
    }

    public static void close() {
        if (db != null) {
            db = null;
        }
    }
}
