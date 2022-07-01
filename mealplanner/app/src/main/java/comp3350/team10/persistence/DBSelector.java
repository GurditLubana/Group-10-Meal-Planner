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
        //this.hsql = new HSqlDB();
        this.stub = new DataAccessStub("stub");

        this.logDB = this.hsql;
        this.userDB = this.hsql;
        this.recipeDB = this.hsql;
        this.moveToStubDB();
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

    public void setHeight(int newHeight) {
        this.userDB.setHeight(newHeight);
    }

    public void setWeight(int newWeight) {
        this.userDB.setWeight(newWeight);
    }

    public void setCalorieGoal(int goal, Calendar date) {
        this.userDB.setCalorieGoal(goal, date);
    }

    public void setExerciseGoal(int goal, Calendar date) {
        this.userDB.setExerciseGoal(goal, date);
    }


    //Log interface
    public DailyLog searchFoodLogByDate(Calendar date) {
        return this.logDB.searchFoodLogByDate(date);
    }

    public void addLog(DailyLog newLog) {
        this.logDB.addLog(newLog);
    }

    public void deleteLog(DailyLog delLog) {
        this.logDB.deleteLog(delLog);
    }

    public EdibleLog findEdibleByKey(int dbkey) {
        //return this.recipeDB.findEdibleByKey(dbkey);
        EdibleLog result = null;
        try{
            result = new EdibleLog(this.recipeDB.findEdibleByKey(dbkey)).init(10, Edible.Unit.cups);
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        return result;
    }

    public void setExerciseActual(int newExercise, Calendar logDate) {
        this.logDB.setExerciseActual(newExercise, logDate);
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
