package comp3350.team10.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import comp3350.team10.objects.Meal;

public class HSqlDB extends SQLiteOpenHelper {//implements DataInterface {
    private static final int CURR_VERSION = 1;
    private static final String DB_NAME = "HSqlDB";

    public HSqlDB(@Nullable Context context, @Nullable String name) {
        super(context, DB_NAME, null, CURR_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.createFoodTable(db);
        this.createMealTable(db);
        this.createDrinkTable(db);
        this.createEdibleTable(db);

        this.createMealIngredientTable(db);
        this.createDrinkIngredientTable(db);

        this.createUserTable(db);
        this.createEdibleHistoryTable(db);
        this.createWorkoutHistoryTable(db);
    }

    private void createFoodTable(SQLiteDatabase db) {
         db.execSQL("create table Food (" +
            "ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999) not null," +
            "Photo          BLOB);"
         );
    }

    private void createMealTable(SQLiteDatabase db) {
        db.execSQL("create table Meal (" +
            "ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999) not null," +
            "Photo          BLOB," +
            "Instructions   VARCHAR(9999));"
        );
    }

    private void createDrinkTable(SQLiteDatabase db) {
        db.execSQL("create table Drink (" +
            "ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999) not null," +
            "Photo          BLOB," +
            "Instructions   VARCHAR(9999));"
        );
    }

    private void createEdibleTable(SQLiteDatabase db) {
        db.execSQL("create table Edible (" +
            "ID         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Type       VARCHAR(9999) not null," +
            "EdibleID   INTEGER       not null," +
            "Quantity   INTEGER       not null," +
            "Unit       VARCHAR(9999) not null," +
            "Calories   INTEGER," +
            "Protein    INTEGER," +
            "Carbs      INTEGER," +
            "Fat        INTEGER," +

            "FOREIGN KEY(EdibleID) REFERENCES Food(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(EdibleID) REFERENCES Meal(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(EdibleID) REFERENCES Drink(ID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createDrinkIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table DrinkIngredient (" +
            "DrinkID      INTEGER       not null," +
            "IngredientID INTEGER       not null," +
            "Alcoholic    BOOLEAN       not null," +
            "Substitute   BOOLEAN       not null," +
            "Unit         VARCHAR(9999) not null," +

            "FOREIGN KEY(DrinkID) REFERENCES Drink(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(IngredientID) REFERENCES Food(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(IngredientID) REFERENCES Drink(ID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createMealIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table MealIngredient (" +
            "MealID         INTEGER       not null," +
            "IngredientID   INTEGER       not null," +
            "Quantity       INTEGER       not null," +
            "Unit           VARCHAR(9999) not null," +

            "FOREIGN KEY(MealID) REFERENCES Meal(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(IngredientID) REFERENCES Food(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(IngredientID) REFERENCES Drink(ID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createUserTable(SQLiteDatabase db) {
        db.execSQL("create table User (" +
            "ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999)," +
            "Height         INTEGER," +
            "Weight         INTEGER," +
            "CalorieGoal    INTEGER not null," +
            "ExerciseGoal   INTEGER);"
        );
    }

    private void createWorkoutHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table WorkoutHistory (" +
            "ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID         INTEGER not null," +
            "Date           DATE    not null," +
            "ExerciseActual INTEGER not null," +

            "FOREIGN KEY(UserID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createEdibleHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table EdibleHistory (" +
            "ID          INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID     INTEGER not null," +
            "Date       DATE    not null," +
            "EdibleID   INTEGER not null," +

            "FOREIGN KEY(UserID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(EdibleID) REFERENCES Edible(ID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    @Override //used when the a new version is created
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
