package comp3350.team10.persistence;

public class SharedDB {
    private static DataAccessStub db = null;
    public static String dbName = "";

    public static DataAccessStub start(String dbname){
        if(db == null){
            db = new DataAccessStub();
            SharedDB.dbName = dbname;
            db.open(dbname);
        }
        return db;
    }

    public static DataAccessStub getSharedDB(){
        return db;
    }

    public static void close(){
        if(db != null)
        {
            db = null;
        }
    }


}
