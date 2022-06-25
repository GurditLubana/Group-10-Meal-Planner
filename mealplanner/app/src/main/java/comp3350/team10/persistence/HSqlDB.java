package comp3350.team10.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class HSqlDB extends SQLiteOpenHelper implements DiaryDBInterface, RecipeDBInterface, UserDBInterface {
    private static final int CURR_VERSION = 1;
    private static final String DB_NAME = "HSqlDB";

    public HSqlDB(@Nullable Context context) {
        super(context, DB_NAME, null, CURR_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.createEdibleTable(db);
        this.createFoodTable(db);
        this.createMealTable(db);
        this.createDrinkTable(db);

        this.createMealIngredientTable(db);
        this.createDrinkIngredientTable(db);

        this.createUserTable(db);
        this.createEdibleHistoryTable(db);
        this.createWorkoutHistoryTable(db);
    }

    private void createEdibleTable(SQLiteDatabase db) {
        db.execSQL("create table Edible (" +
            "EdibleID   INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name       VARCHAR(9999) not null," +
            "Summary    VARCHAR(9999)," +
            "Photo          BLOB," +
            "Quantity   INTEGER       not null," +
            "Unit       VARCHAR(9999) not null," +
            "Calories   INTEGER," +
            "Protein    INTEGER," +
            "Carbs      INTEGER," +
            "Fat        INTEGER);"
        );
    }

    private void createFoodTable(SQLiteDatabase db) {
        db.execSQL("create table Food (" +
            "EdibleID       INTEGER," +
            "IsVegan        BOOLEAN," +
            "IsVegetarian   BOOLEAN," +
            "IsGlutanFree   BOOLEAN," +
            "IsSpicy        BOOLEAN," +
            "IsBreakfast    BOOLEAN," +
            "IsLunch        BOOLEAN," +
            "IsSupper       BOOLEAN," +

            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createMealTable(SQLiteDatabase db) {
        db.execSQL("create table Meal (" +
            "EdibleID       INTEGER," +
            "Instructions   VARCHAR(9999)," +
            "IsVegan        BOOLEAN," +
            "IsVegetarian   BOOLEAN," +
            "IsGlutanFree   BOOLEAN," +
            "IsSpicy        BOOLEAN," +
            "IsBreakfast    BOOLEAN," +
            "IsLunch        BOOLEAN," +
            "IsSupper       BOOLEAN," +

            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createDrinkTable(SQLiteDatabase db) {
        db.execSQL("create table Drink (" +
            "EdibleID       INTEGER," +
            "Instructions   VARCHAR(9999)," +
            "IsAlcoholic        BOOLEAN," +
            "IsSpicy   BOOLEAN," +

            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createDrinkIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table DrinkIngredient (" +
            "DrinkID            INTEGER       not null," +
            "FoodIngredientID   INTEGER," +
            "DrinkIngredientID  INTEGER," +
            "Alcoholic          BOOLEAN       not null," +
            "Substitute         BOOLEAN       not null," +
            "Unit               VARCHAR(9999) not null," +

            "FOREIGN KEY(DrinkID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(FoodIngredientID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(DrinkIngredientID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createMealIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table MealIngredient (" +
            "MealID             INTEGER       not null," +
            "FoodIngredientID   INTEGER," +
            "DrinkIngredientID  INTEGER," +
            "Quantity           INTEGER       not null," +
            "Unit               VARCHAR(9999) not null," +

            "FOREIGN KEY(MealID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(FoodIngredientID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(DrinkIngredientID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createUserTable(SQLiteDatabase db) {
        db.execSQL("create table User (" +
            "UserID         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999)," +
            "Height         INTEGER," +
            "Weight         INTEGER," +
            "CalorieGoal    INTEGER not null," +
            "ExerciseGoal   INTEGER);"
        );
    }

    private void createWorkoutHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table WorkoutHistory (" +
            "WorkoutID      INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID         INTEGER not null," +
            "Date           DATE    not null," +
            "ExerciseActual INTEGER not null," +

            "FOREIGN KEY(UserID) REFERENCES User(UserID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    private void createEdibleHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table EdibleHistory (" +
            "LogID      INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID     INTEGER not null," +
            "Date       DATE    not null," +
            "EdibleID   INTEGER not null," +

            "FOREIGN KEY(UserID) REFERENCES User(UserID) ON DELETE CASCADE ON UPDATE CASCADE," +
            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID) ON DELETE CASCADE ON UPDATE CASCADE);"
        );
    }

    @Override //used when the a new version is created
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public ArrayList<Edible> getFoodList(Calendar date) {
        return null;
    }

    public ArrayList<Edible> getMealList(Calendar date) {
        return null;
    }

    public ArrayList<Edible> getDrinkList(Calendar date) {
        return null;
    }

    @Override
    public LinkedList<Edible> getFoodRecipes() {
        return null;
    }

    @Override
    public LinkedList<Edible> getMealRecipes() {
        return null;
    }

    @Override
    public LinkedList<Edible> getDrinkRecipes() {
        return null;
    }

    public void addFoodToRecipeBook(Food newFood) {

    }

    public void addMealToRecipeBook(Meal newMeal) {

    }

    public void addDrinkToRecipeBook(Drink newDrink) {

    }

    public void addLog(Edible newEdible) {

    }

    public void deleteLog(Edible delEdible) {

    }

    public void updateLog(Edible modEdible) {

    }

    public void addEdible(Edible newEdible) {

    }

    public void updateEdible(Edible modEdible) {

    }

    public void addUser(String name, int height, int weight) {

    }

    public User getUserDetails() {
        return null;
    }

    public void setHeight(int newHeight) {

    }

    public void setWeight(int newWeight) {

    }

    public void setCalorieGoal(int goal) {

    }

    public void setExerciseGoal(int goal) {

    }
}
