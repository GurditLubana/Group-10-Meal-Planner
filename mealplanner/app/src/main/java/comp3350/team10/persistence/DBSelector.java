package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class DBSelector {
    private HSqlDB hsql;                //An active instance of the HSQL database
    private DataAccessStub stub;        //An active instance of the DataAccessStub database (switch to shared)

    private LogDBInterface logDB;       //The database we would like to process log operations on
    private UserDBInterface userDB;     //The database we would like to process user operations on
    private RecipeDBInterface recipeDB; //The database we would like to process recipe operations on
    

    DBSelector() { //Creates both databases then points all interfaces towards hsql
        this.hsql = new HSqlDB();
        this.stub = new DataAccessStub("stub");

        this.logDB = this.hsql;
        this.userDB = this.hsql;
        this.recipeDB = this.hsql;
    }


    void moveToStubDB() { //Point all interfaces towards the Stub
        this.recipeDB = this.stub;
        this.userDB = this.stub;
        this.logDB = this.stub;
        
        this.stub.open("");
    }
    

    //User interface
    public void addUser(String name, int height, int weight) {
        this.userDB.addUser(name, height, weight);
    }

    public User getUser() {
        return this.userDB.getUser();
    }

    public void setHeight(int userID, int newHeight) {
        this.userDB.setHeight(userID, newHeight);
    }

    public void setWeight(int userID, int newWeight) {
        this.userDB.setWeight(userID, newWeight);
    }

    public void setCalorieGoal(int userID, double goal, Calendar date) {
        this.userDB.setCalorieGoal(userID, goal, date);
    }

    public void setExerciseGoal(int userID, int goal, Calendar date) {
        this.userDB.setExerciseGoal(userID, goal, date);
    }


    //Log interface
    public DailyLog searchFoodLogByDate(Calendar date, int userID) {
        return this.logDB.searchFoodLogByDate(date, userID);
    }

    public void addLog(DailyLog newLog, int userID) {
        this.logDB.addLog(newLog, userID);
    }

    public void deleteLog(DailyLog delLog, int userID) {
        this.logDB.deleteLog(delLog, userID);
    }

    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom) {
        return this.recipeDB.findEdibleByKey(dbkey, isCustom);
    }

    public void setExerciseActual(double newExercise, DailyLog logDate, int userID) {
        this.logDB.setExerciseActual(newExercise, logDate, userID);
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
}
