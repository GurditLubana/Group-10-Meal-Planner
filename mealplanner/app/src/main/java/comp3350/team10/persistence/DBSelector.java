package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class DBSelector {
    private static DataAccess db = null;

    public static void start() {
        db = new HSqlDB();
    }

    public static void start(DataAccess alternateDB) {
        db = alternateDB;
    }

    public static void save() {
        db.save();
    }

    public static void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public static DataAccess getSharedDB() {
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

    public static void removeTestData() {
        db.removeTestData();
    }
}
