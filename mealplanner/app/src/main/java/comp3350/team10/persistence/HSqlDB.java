package comp3350.team10.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import comp3350.team10.objects.Meal;

public class HSqlDB extends SQLiteOpenHelper implements DataInterface {
    private static final int CURR_VERSION = 1;

    public HSqlDB(@Nullable Context context, @Nullable String name) {
        super(context, name, null, CURR_VERSION);
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
            "Fat        INTEGER);"
        );
    }

    private void createDrinkIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table DrinkIngredient (" +
            "DrinkID      INTEGER       not null," +
            "IngredientID INTEGER       not null," +
            "Alcoholic    BOOLEAN       not null," +
            "Substitute   BOOLEAN       not null," +
            "Unit         VARCHAR(9999) not null);"
        );
    }

    private void createMealIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table MealIngredient (" +
            "MealID        INTEGER       not null," +
            "IngredientID  INTEGER       not null," +
            "Quantity      INTEGER       not null," +
            "Unit          VARCHAR(9999) not null);"
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
            "ExerciseActual INTEGER not null);"
        );
    }

    private void createEdibleHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table EdibleHistory (" +
            "ID          INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID     INTEGER not null," +
            "Date       DATE    not null," +
            "EdibleID   INTEGER not null);"
        );
    }

    @Override //used when the a new version is created
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
