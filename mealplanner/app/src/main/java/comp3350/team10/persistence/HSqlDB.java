package comp3350.team10.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class HSqlDB extends SQLiteOpenHelper implements LogDBInterface, RecipeDBInterface, UserDBInterface {
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

        //seed the database on startup
        this.seedDB();
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
            "PreparedID         INTEGER," +
            "PreparedCustomID   INTEGER," +
            "EdibleID           INTEGER," +
            "CustomEdibleID     INTEGER," +
            "Quantity           INTEGER       not null," +
            "Unit               VARCHAR(9999) not null," +
            "Substitute         BOOLEAN       not null," +

            "FOREIGN KEY(PreparedID) REFERENCES Edible(EdibleID)," +
            "FOREIGN KEY(PreparedCustomID) REFERENCES CustomEdible(CustomEdibleID)," +
            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID)," +
            "FOREIGN KEY(CustomEdibleID) REFERENCES CustomEdible(CustomEdibleID));"
        );
    }

    private void createMealIngredientTable(SQLiteDatabase db) {
        db.execSQL("create table MealIngredient (" +
            "PreparedID         INTEGER," +
            "PreparedCustomID   INTEGER," +
            "EdibleID           INTEGER," +
            "CustomEdibleID     INTEGER," +
            "Quantity           INTEGER       not null," +
            "Unit               VARCHAR(9999) not null," +

            "FOREIGN KEY(PreparedID) REFERENCES Edible(EdibleID)," +
            "FOREIGN KEY(PreparedCustomID) REFERENCES CustomEdible(CustomEdibleID)," +
            "FOREIGN KEY(EdibleID) REFERENCES Edible(EdibleID)," +
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
        return new ArrayList<Edible>();
    }

    public ArrayList<Edible> getMealList(Calendar date) {
        return new ArrayList<Edible>();
    }

    public ArrayList<Edible> getDrinkList(Calendar date) {
        return new ArrayList<Edible>();
    }

    public ArrayList<Edible> getFoodRecipes() {
        return new ArrayList<Edible>();
    }

    public ArrayList<Edible> getMealRecipes() {
        return new ArrayList<Edible>();
    }

    public ArrayList<Edible> getDrinkRecipes() {
        return new ArrayList<Edible>();

    }

    public int getNextKey() {
        return 1;
    }

    public void addFoodToRecipeBook(Edible newFood) {

    }

    public void addMealToRecipeBook(Meal newMeal) {

    }

    public void addDrinkToRecipeBook(Drink newDrink) {

    }

    public DailyLog searchFoodLogByDate(Calendar date) {
        //int today = calendarToInt(Calendar.getInstance());
        DailyLog log = new DailyLog();
        try {
            log.init(date, new ArrayList<Edible>(), 700, 100, 0);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return log;
    }

    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }

    public Edible findEdibleByKey(int dbkey) {
        return null;
    }

    public void addLog(DailyLog newLog) {

    }

    public void deleteLog(DailyLog delLog) {

    }

    public void setExerciseActual(int newExercise, Calendar logDate) {

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
        return new User("USER", 1, 666, 666, 666, 666);
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

    private void seedDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        
        //Used to add data to each respective table
        ContentValues edible = new ContentValues();
        ContentValues customEdible = new ContentValues();
        ContentValues user = new ContentValues();
        ContentValues history = new ContentValues();
        ContentValues workoutHistory = new ContentValues();
        ContentValues edibleHistory = new ContentValues();
        ContentValues food = new ContentValues();
        ContentValues meal = new ContentValues();
        ContentValues drink = new ContentValues();
        ContentValues customFood = new ContentValues();
        ContentValues customMeal = new ContentValues();
        ContentValues ingredient = new ContentValues();
        ContentValues drinkIngredient = new ContentValues();

        //The saved ID's of seeded entries (for later use as foreign keys)
        long pineappleID, mushroomID, eggID, walnutID, milkID, onionID, wannabePinaColadaID, scrambledEggsID;
        long pickleID, scrambledEggsWithPickleID;
        long userID;
        long firstDayHistoryID, secondDayHistoryID;
        

        //creates new edible entries then add them to the DB
        edible.put("Name", "Pineapple");
        edible.put("Quantity", 79);
        edible.put("Unit", "oz");
        edible.put("Calories", 10);
        edible.put("Protein", 20);
        edible.put("Carbs", 30);
        edible.put("Fat", 30);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", true);
        edible.put("IsVegetarian", true);
        edible.put("IsGlutenFree", true);
        pineappleID = db.insert("Edible", null, edible);

        edible.put("Name", "Mushroom");
        edible.put("Quantity", 20);
        edible.put("Unit", "ml");
        edible.put("Calories", 5);
        edible.put("Protein", 200);
        edible.put("Carbs", 31);
        edible.put("Fat", 30);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", true);
        mushroomID = db.insert("Edible", null, edible);

        edible.put("Name", "Egg");
        edible.put("Quantity", 9);
        edible.put("Unit", "oz");
        edible.put("Calories", 15);
        edible.put("Protein", 10);
        edible.put("Carbs", 10);
        edible.put("Fat", 10);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        eggID = db.insert("Edible", null, edible);

        edible.put("Name", "Walnut");
        edible.put("Quantity", 93);
        edible.put("Unit", "serving");
        edible.put("Calories", 43);
        edible.put("Protein", 2);
        edible.put("Carbs", 2);
        edible.put("Fat", 3);
        edible.put("IsAlcoholic", true);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        walnutID = db.insert("Edible", null, edible);

        edible.put("Name", "Milk");
        edible.put("Quantity", 10);
        edible.put("Unit", "cups");
        edible.put("Calories", 40);
        edible.put("Protein", 100);
        edible.put("Carbs", 2);
        edible.put("Fat", 3);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        milkID = db.insert("Edible", null, edible);

        edible.put("Name", "Onion");
        edible.put("Quantity", 10);
        edible.put("Unit", "g");
        edible.put("Calories", 2);
        edible.put("Protein", 1);
        edible.put("Carbs", 1);
        edible.put("Fat", 1);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        onionID = db.insert("Edible", null, edible);

        edible.put("Name", "Wannabe Pina-Colada");
        edible.put("Quantity", 10);
        edible.put("Unit", "g");
        edible.put("Calories", 2);
        edible.put("Protein", 1);
        edible.put("Carbs", 1);
        edible.put("Fat", 1);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        wannabePinaColadaID = db.insert("Edible", null, edible);

        edible.put("Name", "Scrambled Eggs");
        edible.put("Quantity", 10);
        edible.put("Unit", "g");
        edible.put("Calories", 20);
        edible.put("Protein", 60);
        edible.put("Carbs", 1);
        edible.put("Fat", 1);
        edible.put("IsAlcoholic", false);
        edible.put("IsSpicy", false);
        edible.put("IsVegan", false);
        edible.put("IsVegetarian", false);
        edible.put("IsGlutenFree", false);
        scrambledEggsID = db.insert("Edible", null, edible);


        //creates customEdibles then adds them to the DB
        customEdible.put("Name", "Pickle");
        customEdible.put("Quantity", 11);
        customEdible.put("Unit", "g");
        customEdible.put("Calories", 5);
        customEdible.put("Protein", 5);
        customEdible.put("Carbs", 5);
        customEdible.put("Fat", 5);
        customEdible.put("IsAlcoholic", false);
        customEdible.put("IsSpicy", false);
        customEdible.put("IsVegan", false);
        customEdible.put("IsVegetarian", true);
        customEdible.put("IsGlutenFree", true);
        pickleID = db.insert("CustomEdible", null, customEdible);

        customEdible.put("Name", "Scrambled Eggs With A Pickle");
        customEdible.put("Quantity", 10);
        customEdible.put("Unit", "g");
        customEdible.put("Calories", 25);
        customEdible.put("Protein", 65);
        customEdible.put("Carbs", 1);
        customEdible.put("Fat", 1);
        customEdible.put("IsAlcoholic", false);
        customEdible.put("IsSpicy", false);
        customEdible.put("IsVegan", false);
        customEdible.put("IsVegetarian", false);
        customEdible.put("IsGlutenFree", false);
        scrambledEggsWithPickleID = db.insert("CustomEdible", null, customEdible);
        
        
        //creates new user then adds the user to the database
        user.put("Name", "BestUser");
        user.put("Height", 190);  //cm
        user.put("Weight", 160);  //pounds
        user.put("CalorieGoal", 4000);
        user.put("ExerciseGoal", 100);
        userID = db.insert("User", null, user);


        //creates history for the newly added user and adds it to the database
        history.put("UserID", userID);
        history.put("Date", Calendar.getInstance().toString());
        history.put("CalorieGoal", 5000);
        history.put("CalorieActual", 6000);
        firstDayHistoryID = db.insert("History", null, history);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1); //incrase the day by 1

        history.put("UserID", userID);
        history.put("Date", calendar.getTime().toString());
        history.put("CalorieGoal", 4000);
        history.put("CalorieActual", -1000);
        secondDayHistoryID = db.insert("History", null, history);


        //creates workout history for the newly added user and adds it to the database
        workoutHistory.put("HistoryID", firstDayHistoryID);
        workoutHistory.put("ExerciseActual", 100);
        db.insert("WorkoutHistory", null, workoutHistory);

        workoutHistory.put("HistoryID", secondDayHistoryID);
        workoutHistory.put("ExerciseActual", 1000);
        db.insert("WorkoutHistory", null, workoutHistory);


        //creates edible history for the newly added user and adds it to the database
        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", pineappleID);
        edibleHistory.put("Quantity", 1);
        edibleHistory.put("Unit", "cups");
        db.insert("EdibleHistory", null, edibleHistory);

        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", walnutID);
        edibleHistory.put("Quantity", 2);
        edibleHistory.put("Unit", "serving");
        db.insert("EdibleHistory", null, edibleHistory);
        
        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", wannabePinaColadaID);
        edibleHistory.put("Quantity", 3);
        edibleHistory.put("Unit", "serving");
        db.insert("EdibleHistory", null, edibleHistory);

        edibleHistory.put("HistoryID", firstDayHistoryID);
        edibleHistory.put("EdibleID", scrambledEggsWithPickleID);
        edibleHistory.put("Quantity", 10);
        edibleHistory.put("Unit", "g");
        db.insert("EdibleHistory", null, edibleHistory);


        //fills food table out with already created edibles
        food.put("EdibleID", pineappleID);
        db.insert("Food", null, food);

        food.put("EdibleID", mushroomID);
        db.insert("Food", null, food);

        food.put("EdibleID", eggID);
        db.insert("Food", null, food);

        food.put("EdibleID", walnutID);
        db.insert("Food", null, food);

        food.put("EdibleID", onionID);
        db.insert("Food", null, food);


        //fills meal table out with already created edibles
        meal.put("EdibleID", scrambledEggsID); 
        meal.put("Instructions", "git guud");
        db.insert("Meal", null, meal);


        //fills drink table out with already created edibles
        drink.put("EdibleID", wannabePinaColadaID); 
        drink.put("Instructions", "git guud");
        db.insert("Drink", null, drink);


        //fills custom food table out with already created edibles
        customFood.put("EdibleID", pickleID); 
        db.insert("CustomFood", null, customFood);


        //fills custom food table out with already created edibles
        customMeal.put("EdibleID", scrambledEggsWithPickleID); 
        drink.put("Instructions", "this one is different!");
        db.insert("CustomFood", null, customMeal);


        //fills ingredient table out with already created edibles   
        ingredient.put("PreparedID", scrambledEggsID);
        ingredient.put("EdibleID", eggID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient.put("PreparedID", scrambledEggsID);
        ingredient.put("EdibleID", onionID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient = new ContentValues();
        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
        ingredient.put("EdibleID", eggID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient = new ContentValues();
        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
        ingredient.put("EdibleID", onionID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        ingredient = new ContentValues();
        ingredient.put("CustomPreparedID", scrambledEggsWithPickleID);
        ingredient.put("CustomEdibleID", pickleID);
        ingredient.put("Quantity", 5);
        ingredient.put("Unit", "ml");
        db.insert("Ingredient", null, ingredient);

        
        //fills ingredient table out with already created edibles   
        drinkIngredient.put("PreparedID", wannabePinaColadaID);
        drinkIngredient.put("EdibleID", milkID);
        drinkIngredient.put("Quantity", 5);
        drinkIngredient.put("Unit", "ml");
        db.insert("DrinkIngredient", null, drinkIngredient);

        drinkIngredient.put("PreparedID", wannabePinaColadaID);
        drinkIngredient.put("EdibleID", pineappleID);
        drinkIngredient.put("Quantity", 10);
        drinkIngredient.put("Unit", "ml");
        db.insert("DrinkIngredient", null, drinkIngredient);
    }
}