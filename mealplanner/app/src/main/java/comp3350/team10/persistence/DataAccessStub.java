package comp3350.team10.persistence;

import comp3350.team10.objects.*;
import comp3350.team10.R;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;

public class DataAccessStub implements DiaryDBInterface, RecipeDBInterface, UserDBInterface {
    //Database info and data variables
    private Calendar calendar = Calendar.getInstance();
    private Integer currKey;                    //The current database key
    private String dbType = "stub";             //Type of database
    private String dbName;                      //Name of database

    //Recipe Database
    private ArrayList<Edible> dbRecipeDrink;    //Drink recipes
    private ArrayList<Edible> dbRecipeFood;     //Food
    private ArrayList<Edible> dbRecipeMeal;     //Meal recipes

    //User and History Database
    private final static int USER_ID = 0;       //Default user id
    private ArrayList<DailyLog> dbFoodLog;      //Logs
    private User currUser;                      //The current user


    public DataAccessStub(String dbName) {
        this.calendar = Calendar.getInstance();
        this.dbName = dbName;
        this.currKey = 1;
    }

    public void open(String dbName) {
        //Load data
        this.loadRecipeFood();
        this.loadRecipeMeals();
        this.loadRecipeDrinks();

        this.loadUser();
        this.loadFoodlog();
    }

    private void loadUser() {
        currUser = new User("USER", USER_ID, 666, 666, 666, 666);
    }

    private void loadFoodlog() {
        int today = calendarToInt(calendar);
        DailyLog currLog = new DailyLog();

        this.dbFoodLog = new ArrayList<DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog

        try {
            ArrayList<EdibleLog> logDay = new ArrayList<EdibleLog>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(0)).init(1, Edible.Unit.cups));
            logDay.addAll(emptyLog());

            this.dbFoodLog.add(new DailyLog().init(today, logDay, 700, 100, 0));

            this.sortDBFoodLog();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }
    
    private void sortDBFoodLog() {
        Collections.sort(dbFoodLog, new Comparator<DailyLog>() {
    
            public int compare(DailyLog left, DailyLog right) {
                int result = 0;

                if (left.getDate() < right.getDate()) {
                    result = -1;
                } else if (left.getDate() < right.getDate()) {
                    result = 1;
                }
                return result;
            }
        });
    }

    private ArrayList<EdibleLog> emptyLog() {
        ArrayList<EdibleLog> edibleLog = new ArrayList<EdibleLog>();
        Food addLog = new Food();
        EdibleLog emptyLog = new EdibleLog(addLog);


        try {
            addLog.setFragmentType(ListItem.FragmentType.diaryAdd);
            //emptyLog.setEdibleEntry(addLog);
            edibleLog.add(emptyLog);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return edibleLog;
    }

    private void loadRecipeDrinks() {
        this.dbRecipeDrink = new ArrayList<Edible>();
        ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();

        try {
            DrinkIngredient ingredient = (DrinkIngredient)new DrinkIngredient().init(this.dbRecipeFood.get(1), 1.1, Edible.Unit.cups);
            ingredients.add(ingredient);
            ingredients.add(ingredient);

            this.dbRecipeDrink.add(new Drink()
                    .initDetails(1, "drink", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            ((Drink)this.dbRecipeDrink.get(0)).setIngredients(ingredients);
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(2, "drink", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            ((Drink)this.dbRecipeDrink.get(1)).setIngredients(ingredients);
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(3, "drink", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            ((Drink)this.dbRecipeDrink.get(2)).setIngredients(ingredients);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private void loadRecipeFood() {
        this.dbRecipeFood = new ArrayList<Edible>();

        try {
            this.dbRecipeFood.add(new Food()
                    .initDetails(4, "food", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            this.dbRecipeFood.add(new Food()
                    .initDetails(5, "food", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            this.dbRecipeFood.add(new Food()
                    .initDetails(6, "food", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            this.dbRecipeFood.add(new Food()
                    .initDetails(7, "food", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private void loadRecipeMeals() {
        this.dbRecipeMeal = new ArrayList<Edible>();
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        try {
            Ingredient ingredient = new Ingredient().init(this.dbRecipeFood.get(1), 1.1, Edible.Unit.cups);
            ingredients.add(ingredient);
            ingredients.add(ingredient);

            this.dbRecipeMeal.add(new Meal()
                    .initDetails(8, "meal", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            ((Meal)this.dbRecipeMeal.get(0)).setIngredients(ingredients);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(9, "meal", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            ((Meal)this.dbRecipeMeal.get(1)).setIngredients(ingredients);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(10, "meal", "desc", 1, Edible.Unit.cups)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, new byte[] {10, 10}, ListItem.FragmentType.recipe)
            );
            ((Meal)this.dbRecipeMeal.get(2)).setIngredients(ingredients);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public Edible findEdibleByKey(int key) {
        Edible result = null;
        System.out.println("key: " + key);

        for (int i = 0; i < this.dbRecipeFood.size() && result == null; i++) {
            if (this.dbRecipeFood.get(i).getDbkey() == key) {
                result = this.dbRecipeFood.get(i);
            }
        }
        for (int i = 0; i < this.dbRecipeMeal.size() && result == null; i++) {
            if (this.dbRecipeMeal.get(i).getDbkey() == key) {
                result = this.dbRecipeMeal.get(i);
            }
        }
        for (int i = 0; i < this.dbRecipeDrink.size() && result == null; i++) {
            if (this.dbRecipeDrink.get(i).getDbkey() == key) {
                result = this.dbRecipeDrink.get(i);
            }
        }

        return result;
    }

    public Integer getNextKey() {
        Integer result = this.currKey.intValue();

        this.currKey += 1;

        return result;
    }

    public void close() {
        System.out.println("Closed " + this.dbType + " database " + this.dbName);
    }

   
    //This section implements UserDBInterface'
    public void addUser(String name, int height, int weight) {
        System.out.println("Service not implemented yet");
    }

    public User getUser() {
        return this.currUser;
    }

    public void setHeight(int newHeight) {
        this.currUser.setHeight(newHeight);
    }

    public void setWeight(int newWeight) {
        this.currUser.setHeight(newWeight);
    }

    public void setCalorieGoal(int goal, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date);
        int currLogIndex;

        try {
            sortDBFoodLog();
            currLogIndex = this.dbFoodLog.indexOf(currEntry);
            this.currUser.setCalorieGoal(goal);

            for (int i = currLogIndex; i < this.dbFoodLog.size(); i++) {
                currEntry = this.dbFoodLog.get(i);
                this.dbFoodLog.remove(currEntry);
                currEntry.setCalorieGoal(goal);
                this.dbFoodLog.add(i, currEntry);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void setExerciseGoal(int goal, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date);
        int currLogIndex;

        try {
            sortDBFoodLog();
            currLogIndex = this.dbFoodLog.indexOf(currEntry);
            this.currUser.setExerciseGoal(goal);

            for (int i = currLogIndex; i < this.dbFoodLog.size(); i++) {
                currEntry = this.dbFoodLog.get(i);
                this.dbFoodLog.remove(currEntry);
                currEntry.setCalorieGoal(goal);
                this.dbFoodLog.add(i, currEntry);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void setExerciseActual(int exerciseActual, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date);
        try {
            this.dbFoodLog.remove(currEntry);
            currEntry.setCalorieGoal(exerciseActual);
            this.dbFoodLog.add(currEntry);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }


    //This section implements RecipeDBInterface
    public ArrayList<Edible> getFoodRecipes() {
        ArrayList<Edible> currFood = new ArrayList<Edible>();
        
        currFood.addAll(this.dbRecipeFood);

        return currFood;
    }

    public ArrayList<Edible> getMealRecipes() {
        ArrayList<Edible> currMeals = new ArrayList<Edible>();
        
        currMeals.addAll(this.dbRecipeMeal);

        return currMeals;
    }

    public ArrayList<Edible> getDrinkRecipes() {
        ArrayList<Edible> currDrinks = new ArrayList<Edible>();
        
        currDrinks.addAll(this.dbRecipeDrink);

        return currDrinks;
    }

    public void addFoodToRecipeBook(Food newFood) {
        if (newFood != null) {
            this.dbRecipeFood.add(newFood);
        }
    }

    public void addMealToRecipeBook(Meal newMeal) {
        if (newMeal != null) {
            this.dbRecipeMeal.add(newMeal);
        }
    }

    public void addDrinkToRecipeBook(Drink newDrink) {
        if (newDrink != null) {
            this.dbRecipeDrink.add(newDrink);
        }
    }


    //This section implements DiaryDBInterface
    public DailyLog searchFoodLogByDate(Calendar date) {
        Integer intDate = calendarToInt(date);
        DailyLog foundLog = null;

        for (int i = 0; i < this.dbFoodLog.size() && foundLog == null; i++) {
            if (intDate.intValue() == this.dbFoodLog.get(i).getDate()) {
                foundLog = dbFoodLog.get(i);
            }
        }

        return foundLog;
    }

    public void addLog(DailyLog newLog) {
        if(this.dbFoodLog != null) {
            this.dbFoodLog.add(newLog);
        }
    }

    public void deleteLog(DailyLog delLog) {
        if(this.dbFoodLog != null) {
            this.dbFoodLog.remove(delLog);
        }
    }
}