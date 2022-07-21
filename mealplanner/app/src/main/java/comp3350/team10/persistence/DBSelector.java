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

public class DBSelector implements LogDBInterface, UserDBInterface, RecipeDBInterface {
    private HSqlDB hsql;                //An active instance of the HSQL database
    private DataAccessStub stub;        //An active instance of the DataAccessStub database (switch to shared)

    private LogDBInterface logDB;       //The database we would like to process log operations on
    private UserDBInterface userDB;     //The database we would like to process user operations on
    private RecipeDBInterface recipeDB; //The database we would like to process recipe operations on


    DBSelector() { //Creates both databases then points all interfaces towards hsql
        //startHsqlDB();
        startStubDB();
    }


    public void startHsqlDB() {
        this.hsql = new HSqlDB();

        this.logDB = this.hsql;
        this.userDB = this.hsql;
        this.recipeDB = this.hsql;
    }

    public void startStubDB() {
        this.stub = new DataAccessStub("stub");

        this.stub.open("");
        this.recipeDB = this.stub;
        this.userDB = this.stub;
        this.logDB = this.stub;
    }

    public void save() {
        if (this.hsql != null) {
            this.hsql.save();
        } else if (this.stub != null) {
            this.stub.save();
        }
    }

    public void close() {
        if (this.hsql != null) {
            this.hsql.close();
        } else if (this.stub != null) {
            this.stub.close();
        }
    }


    //User interface

    public User getUser() {
        return this.userDB.getUser();
    }

    public void setHeight(int userID, int newHeight) {
        this.userDB.setHeight(userID, newHeight);
    }

    public void setWeight(int userID, int newWeight) {
        this.userDB.setWeight(userID, newWeight);
    }

    public void setCalorieGoal(int userID, double goal) {
        this.userDB.setCalorieGoal(userID, goal);
    }

    public void setExerciseGoal(int userID, double goal) {
        this.userDB.setExerciseGoal(userID, goal);
    }


    //Log interface
    public DailyLog searchFoodLogByDate(int userID, Calendar date) {
        return this.logDB.searchFoodLogByDate(userID, date);
    }

    public void replaceLog(int userID, DailyLog newLog) {
        this.logDB.replaceLog(userID, newLog);
    }


    public void setLogCalorieGoal(int userID, double goal, Calendar date) {
        this.logDB.setLogCalorieGoal(userID, goal, date);
    }

    public void setLogExerciseGoal(int userID, double goal, Calendar date) {
        this.logDB.setLogExerciseGoal(userID, goal, date);
    }

    public ArrayList<Double> getDataFrame(DataFrame.DataType dataType, int days) {
        return this.logDB.getDataFrame(dataType, days);
    }

    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom) {
        return this.logDB.findEdibleByKey(dbkey, isCustom);
    }

    public void setExerciseActual(int userID, double newValue, Calendar date) {
        this.logDB.setExerciseActual(userID, newValue, date);
    }


    //Recipe interface
    public int getNextKey() {
        return this.recipeDB.getNextKey();
    }

    public ArrayList<Edible> getFoodRecipes() {
        return this.recipeDB.getFoodRecipes();
    }

    public ArrayList<Edible> getMealRecipes() {
        return this.recipeDB.getMealRecipes();
    }

    public ArrayList<Edible> getDrinkRecipes() {
        return this.recipeDB.getDrinkRecipes();
    }

    public void addFoodToRecipeBook(Edible newFood) {
        this.recipeDB.addFoodToRecipeBook(newFood);
    }

    public void addMealToRecipeBook(Meal newMeal) {
        this.recipeDB.addMealToRecipeBook(newMeal);
    }

    public void addDrinkToRecipeBook(Drink newDrink) {
        this.recipeDB.addDrinkToRecipeBook(newDrink);
    }

    public Edible findIngredientByKey(int key, boolean isCustom) {
        return this.recipeDB.findIngredientByKey(key, isCustom);
    }
}
