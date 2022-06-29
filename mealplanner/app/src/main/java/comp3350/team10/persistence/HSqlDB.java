package comp3350.team10.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.DailyLog;
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
        //Creates company stock recipe tables
        this.createEdibleTable(db);
        this.createFoodTable(db);
        this.createMealTable(db);
        this.createDrinkTable(db);

        //Creates individual user's custom recipe tables
        this.createCustomEdibleTable(db);
        this.createCustomFoodTable(db);
        this.createCustomMealTable(db);
        this.createCustomDrinkTable(db);

        //Creates ingredient tables
        this.createMealIngredientTable(db);
        this.createDrinkIngredientTable(db);

        //creates user data tables
        this.createUserTable(db);
        this.createHistoryTable(db);
        this.createEdibleHistoryTable(db);
        this.createWorkoutHistoryTable(db);
    }

    private void createCustomEdibleTable(SQLiteDatabase db) {
        db.execSQL("create table CustomEdible (" +
            "CustomEdibleID     INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID             INTEGER         not null," +
            "Name               VARCHAR(9999)   not null," +
            "Description        VARCHAR(9999)," +
            "Quantity           INTEGER         not null," +
            "Unit               VARCHAR(9999)   not null," +
            "Calories           INTEGER         not null," +
            "Protein            INTEGER         not null," +
            "Carbs              INTEGER         not null," +
            "Fat                INTEGER         not null," +
            "Photo              BLOB," +
            "IsAlcoholic        BOOLEAN         not null," +
            "IsSpicy            BOOLEAN         not null," +
            "IsVegan            BOOLEAN         not null," +
            "IsVegetarian       BOOLEAN         not null," +
            "IsGlutenFree       BOOLEAN         not null);"
        );
    }

    private void createCustomDrinkTable(SQLiteDatabase db) {
        db.execSQL("create table CustomDrink (" +
            "CustomEdibleID     INTEGER         not null," +
            "Instructions       VARCHAR(9999)," +

            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID));"
        );
    }

    private void createCustomMealTable(SQLiteDatabase db) {
        db.execSQL("create table CustomMeal (" +
            "CustomEdibleID     INTEGER         not null," +
            "Instructions       VARCHAR(9999)," +

            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID));"
        );
    }

    private void createCustomFoodTable(SQLiteDatabase db) {
        db.execSQL("create table    CustomFood (" +
            "CustomEdibleID         INTEGER     not null," +

            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID));"
        );
    }

    private void createEdibleTable(SQLiteDatabase db) {
        db.execSQL("create table Edible (" +
            "EdibleID       INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999)   not null," +
            "Description    VARCHAR(9999)," +
            "Quantity       INTEGER         not null," +
            "Unit           VARCHAR(9999)   not null," +
            "Calories       INTEGER         not null," +
            "Protein        INTEGER         not null," +
            "Carbs          INTEGER         not null," +
            "Fat            INTEGER         not null," +
            "Photo          BLOB," +
            "IsAlcoholic    BOOLEAN         not null," +
            "IsSpicy        BOOLEAN         not null," +
            "IsVegan        BOOLEAN         not null," +
            "IsVegetarian   BOOLEAN         not null," +
            "IsGlutenFree   BOOLEAN         not null);"
        );
    }

    private void createFoodTable(SQLiteDatabase db) {
        db.execSQL("create table Food (" +
            "EdibleID       INTEGER     not null," +

            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID));"
        );
    }

    private void createMealTable(SQLiteDatabase db) {
        db.execSQL("create table Meal (" +
            "EdibleID       INTEGER     not null," +
            "Instructions   VARCHAR(9999)," +

            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID));"
        );
    }

    private void createDrinkTable(SQLiteDatabase db) {
        db.execSQL("create table Drink (" +
            "EdibleID       INTEGER     not null," +
            "Instructions   VARCHAR(9999)," +

            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID));"
        );
    }

    private void createDrinkIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table DrinkIngredient (" +
            "EdibleID           INTEGER," +
            "CustomEdibleID     INTEGER," +
            "Quantity           INTEGER       not null," +
            "Unit               VARCHAR(9999) not null," +
            "Substitute         BOOLEAN," +

            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID)," +
            "FOREIGN KEY(FoodIngredientID) REFERENCES Edible(EdibleID));"
        );
    }

    private void createMealIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table MealIngredient (" +
            "EdibleID           INTEGER," +
            "CustomEdibleID     INTEGER," +
            "Quantity           INTEGER       not null," +
            "Unit               VARCHAR(9999) not null," +

            "FOREIGN KEY(DrinkID) REFERENCES Edible(EdibleID)," +
            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID));"
        );
    }

    private void createUserTable(SQLiteDatabase db) {
        db.execSQL("create table User (" +
            "UserID         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Name           VARCHAR(9999)," +
            "Height         INTEGER," +
            "Weight         INTEGER," +
            "CalorieGoal    INTEGER     not null," +
            "ExerciseGoal   INTEGER);"
        );
    }

    private void createHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table History (" +
            "HistoryID      INTEGER PRIMARY KEY AUTOINCREMENT," +
            "UserID         INTEGER not null," +
            "Date           DATE    not null," +
            "CalorieGoal    INTEGER not null," +
            "CalorieActual  INTEGER not null," +

            "FOREIGN KEY(UserID) REFERENCES User(UserID));"
        );
    }

    private void createWorkoutHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table WorkoutHistory (" +
            "HistoryID      INTEGER not null," +
            "ExerciseActual INTEGER not null," +

            "FOREIGN KEY(HistoryID) REFERENCES History(HistoryID));"
        );
    }

    private void createEdibleHistoryTable(SQLiteDatabase db) {
        db.execSQL("create table EdibleHistory (" +
            "HistoryID          INTEGER         not null," +
            "EdibleID           INTEGER," +
            "CustomEdibleID     INTEGER ," +
            "Quantity           INTEGER         not null," +
            "Unit               VARCHAR(9999)   not null," +

            "FOREIGN KEY(HistoryID) REFERENCES History(HistoryID)," +
            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID)," +
            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID));"
        );
    }

    @Override //used when the a new version is created
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<Edible> getFoodList(Calendar date) {
        return null;
    }

    public ArrayList<Edible> getMealList(Calendar date) {
        return null;
    }

    public ArrayList<Edible> getDrinkList(Calendar date) {
        return null;
    }

    public ArrayList<Edible> getFoodRecipes() {
        return null;
    }

    public ArrayList<Edible> getMealRecipes() {
        return null;
    }

    public ArrayList<Edible> getDrinkRecipes() {
        return null;
    }

    public void addFoodToRecipeBook(Food newFood) {

    }

    public void addMealToRecipeBook(Meal newMeal) {

    }

    public void addDrinkToRecipeBook(Drink newDrink) {

    }

    public DailyLog searchFoodLogByDate(Calendar date) {
        return null;
    }

    public void addLog(DailyLog newLog) {

    }

    public void deleteLog(DailyLog delLog) {

    }

    public void addLog(Edible newEdible) {

    }

    public void deleteLog(Edible delEdible) {

    }

    public void addEdible(Edible newEdible) {

    }

    public void updateEdible(Edible modEdible) {

    }

    public void addUser(String name, int height, int weight) {

    }

    public User getUser() {
        return null;
    }

    public void setHeight(int newHeight) {

    }

    public void setWeight(int newWeight) {

    }

    public void setCalorieGoal(int goal, Calendar date) {

    }

    public void setExerciseGoal(int goal, Calendar date) {

    }

    public void setCalorieGoal(int goal) {

    }

    public void setExerciseGoal(int goal) {

    }
}