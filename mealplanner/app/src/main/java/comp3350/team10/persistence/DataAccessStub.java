package comp3350.team10.persistence;


import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.User;

public class DataAccessStub implements LogDBInterface, RecipeDBInterface, UserDBInterface {
    //Database info and data variables
    private Calendar calendar = Calendar.getInstance();
    private Integer currKey;                    //The current database key
    private String dbType = "stub";             //Type of database
    private String dbName;                      //Name of database

    //Recipe Database
    private ArrayList<Edible> dbRecipeDrink;    //Drink recipes
    private ArrayList<Edible> dbRecipeFood;     //Food
    private ArrayList<Edible> dbRecipeMeal;     //Meal recipes
    private final static int OFFSET_FOOD = 1;
    private final static int OFFSET_SOLO_DRINKS = 31;
    private final static int OFFSET_MEAL = 41;

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
        //Load edible data
        this.loadRecipeFood();
        this.loadRecipeMeals();
        this.loadRecipeDrinks();

        //Load user data
        this.loadUser();
        this.loadFoodlog();
    }

    private void loadUser() {
        currUser = new User(USER_ID, "USER", 666, 666, 666, 666);
    }

    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }

    private void sortDBFoodLog() {
        Collections.sort(dbFoodLog, new Comparator<DailyLog>() {

            public int compare(DailyLog left, DailyLog right) {
                int result = 0;

                if (calendarToInt(left.getDate()) < calendarToInt(right.getDate())) {
                    result = -1;
                } else if (calendarToInt(left.getDate()) > calendarToInt(right.getDate())) {
                    result = 1;
                }
                return result;
            }
        });
    }

    public EdibleLog findEdibleByKey(int key, boolean isCustom) throws NoSuchElementException {
        EdibleLog result = null;
        System.out.println("key: " + key);

        try {
            for (int i = 0; i < this.dbRecipeFood.size() && result == null; i++) {
                if (this.dbRecipeFood.get(i).getDbkey() == key) {
                    result = new EdibleLog(this.dbRecipeFood.get(i));
                }
            }
            for (int i = 0; i < this.dbRecipeMeal.size() && result == null; i++) {
                if (this.dbRecipeMeal.get(i).getDbkey() == key) {
                    result = new EdibleLog(this.dbRecipeMeal.get(i));
                }
            }
            for (int i = 0; i < this.dbRecipeDrink.size() && result == null; i++) {
                if (this.dbRecipeDrink.get(i).getDbkey() == key) {
                    result = new EdibleLog(this.dbRecipeDrink.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("findEdibleByKey error creating a new EdibleLog " + e);
            result = null;
        }
        if (result == null) {
            throw new NoSuchElementException("Requested item for dbkey does not exist");
        }

        return result;
    }

    public int getNextKey() {
        Integer result = this.currKey.intValue();

        this.currKey += 1;

        return result.intValue();
    }

    public void close() {
        System.out.println("Closed " + this.dbType + " database " + this.dbName);
    }

    public void save() {
        System.out.println("saved datbase");
    }

    //This section implements UserDBInterface'
    public void addUser(String name, int height, int weight) {
        System.out.println("Service not implemented yet");
    }

    public User getUser() {
        return this.currUser;
    }

    public void setHeight(int userID, int newHeight) {
        this.currUser.setHeight(newHeight);
    }

    public void setWeight(int userID, int newWeight) {
        this.currUser.setHeight(newWeight);
    }

    public void setCalorieGoal(int userID, double goal, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date, userID);
        int currLogIndex;

        try {
            sortDBFoodLog();
            currEntry.setCalorieGoal(goal);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void setExerciseGoal(int userID, double goal, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date, userID);

        try {
            sortDBFoodLog();
            currEntry.setExerciseGoal(goal);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void setExerciseActual(double exerciseActual, DailyLog currLog, int userID) {
        DailyLog currEntry = searchFoodLogByDate(currLog.getDate(), userID);

        try {
            this.dbFoodLog.remove(currEntry);
            currEntry.setExerciseActual(exerciseActual);
            this.dbFoodLog.add(currEntry);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
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

    public void addFoodToRecipeBook(Edible newFood) {
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
    public DailyLog searchFoodLogByDate(Calendar date, int userID) {
        Integer intDate = calendarToInt(date);
        DailyLog foundLog = null;

        for (int i = 0; i < this.dbFoodLog.size() && foundLog == null; i++) {
            if (intDate.intValue() == calendarToInt(this.dbFoodLog.get(i).getDate()).intValue()) {
                foundLog = dbFoodLog.get(i);
            }
        }

        if (foundLog == null) {
            foundLog = new DailyLog().init(date, new ArrayList<Edible>(), this.getUserCalorieGoal(userID), this.getUserExerciseGoal(userID), 0);
            this.dbFoodLog.add(foundLog);
        }

        return foundLog;
    }

    private double getUserCalorieGoal(int userID) {
        return this.currUser.getCalorieGoal();
    }

    private double getUserExerciseGoal(int userID) {
        return this.currUser.getExerciseGoal();
    }

    public void addLog(DailyLog newLog, int userID) {
        if (this.dbFoodLog != null) {
            this.dbFoodLog.add(newLog);
        }
    }

    public void deleteLog(DailyLog delLog, int userID) {
        if (this.dbFoodLog != null) {
            this.dbFoodLog.remove(delLog);
        }
    }

    public ArrayList<Double> getDataFrame(String dataType, String span){
        ArrayList<Double> result = new ArrayList<>();

        return result;
    }

    private void loadFoodlog() {
        Calendar today = (Calendar) this.calendar.clone();

        this.dbFoodLog = new ArrayList<DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog
        ArrayList<Edible> logDay = null;
        try {
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(0)).init(1, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(2)).init(30, Edible.Unit.ml));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -1);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(0)).init(10, Edible.Unit.ml));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(1)).init(20, Edible.Unit.oz));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(2)).init(30, Edible.Unit.cups));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -2);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(4)).init(20, Edible.Unit.ml));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(5)).init(20, Edible.Unit.oz));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(6)).init(30, Edible.Unit.cups));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -3);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(5)).init(20, Edible.Unit.oz));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(2)).init(30, Edible.Unit.cups));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -4);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(4)).init(20, Edible.Unit.ml));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(8)).init(1, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(0)).init(30, Edible.Unit.ml));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -6);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(5)).init(20, Edible.Unit.oz));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(11)).init(1.5, Edible.Unit.tsp));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(2)).init(30, Edible.Unit.cups));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -7);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(6)).init(20, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(8)).init(1, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(4)).init(20, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(21)).init(1, Edible.Unit.liter));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            today = (Calendar) this.calendar.clone();
            today.add(Calendar.DAY_OF_YEAR, -8);
            logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(5)).init(20, Edible.Unit.oz));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(8)).init(1, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeMeal.get(1)).init(30, Edible.Unit.oz));
            logDay.add(new EdibleLog(this.dbRecipeDrink.get(6)).init(30, Edible.Unit.cups));
            this.dbFoodLog.add(new DailyLog().init(today, logDay, 2000, 600, 0));

            this.sortDBFoodLog();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void loadRecipeDrinks() {
        this.dbRecipeDrink = new ArrayList<Edible>();
        ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
        loadRecipeDrinksSolo();
        DrinkIngredient ingredient = null;

        try {

            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeDrink.get(33 - OFFSET_SOLO_DRINKS), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeDrink.get(34 - OFFSET_SOLO_DRINKS), 2, Edible.Unit.oz);
            ingredients.add(ingredient);

            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeFood.get(25 - OFFSET_FOOD), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeFood.get(24 - OFFSET_FOOD), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            this.dbRecipeDrink.add(new Drink()
                    .initDetails(35, "Mojito", "The best", 10, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "mojito.jpg")
            );
            ((Drink) this.dbRecipeDrink.get(35 - OFFSET_SOLO_DRINKS)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeDrink.get(31 - OFFSET_SOLO_DRINKS), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeDrink.get(32 - OFFSET_SOLO_DRINKS), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            this.dbRecipeDrink.add(new Drink()
                    .initDetails(36, "Vodka OJ", "basic drink", 20, Edible.Unit.oz)
                    .initNutrition(200, 25, 40, 35)
                    .initMetadata(false, "vodkaoj.jpg")
            );
            ((Drink) this.dbRecipeDrink.get(36 - OFFSET_SOLO_DRINKS)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeDrink.get(33 - OFFSET_SOLO_DRINKS), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeDrink.get(32 - OFFSET_SOLO_DRINKS), 1, Edible.Unit.oz);
            ingredients.add(ingredient);

            this.dbRecipeDrink.add(new Drink()
                    .initDetails(37, "Vodka Tonic", "basic drink", 20, Edible.Unit.cups)
                    .initNutrition(200, 25, 40, 35)
                    .initMetadata(false, "vodkatonic.jpg")
            );
            ((Drink) this.dbRecipeDrink.get(37 - OFFSET_SOLO_DRINKS)).setIngredients(ingredients);

        } catch (Exception e) {
            System.out.println("DataAccessStub loadRecipeDrinks failed " + e);
        }
    }

    private void loadRecipeDrinksSolo() {

        try {
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(31, "Orange Juice", "OJ", 100, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "orangejuice.jpg")
            );
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(32, "Vodka", "Water", 10, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "vodka.jpg")
            );
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(33, "Tonic", "Tonic Water", 10, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "tonic.jpg")
            );
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(34, "White Rum", "Skyrim City", 1, Edible.Unit.oz)
                    .initNutrition(200, 30, 45, 25)
                    .initMetadata(false, "whiterum.jpg")
            );
        } catch (Exception e) {
            System.out.println("DataAccessStub loadRecipeDrinksSolo failed " + e);
        }
    }

    private void loadRecipeMeals() {
        this.dbRecipeMeal = new ArrayList<Edible>();
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient ingredient = null;

        try {

            ingredient = new Ingredient().init(this.dbRecipeFood.get(1 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(2 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            this.dbRecipeMeal.add(new Meal()
                    .initDetails(41, "Meal 2items", "desc", 10, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "spaghetti.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(41 - OFFSET_MEAL)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = new Ingredient().init(this.dbRecipeFood.get(3 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(4 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(5 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(42, "Meal 3items", "desc", 20, Edible.Unit.oz)
                    .initNutrition(200, 25, 40, 35)
                    .initMetadata(false, "lasagna.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(42 - OFFSET_MEAL)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = new Ingredient().init(this.dbRecipeFood.get(6 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(7 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(43, "Meal 67", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "salmon.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(43 - OFFSET_MEAL)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = new Ingredient().init(this.dbRecipeFood.get(8 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(9 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(44, "Meal 89", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "chickenfingers.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(44 - OFFSET_MEAL)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = new Ingredient().init(this.dbRecipeFood.get(10 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(11 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(45, "Meal 1011", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "ribsmash.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(45 - OFFSET_MEAL)).setIngredients(ingredients);

            ingredients.clear();
            ingredient = new Ingredient().init(this.dbRecipeFood.get(12 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);

            ingredient = new Ingredient().init(this.dbRecipeFood.get(13 - OFFSET_FOOD), 1, Edible.Unit.cups);
            ingredients.add(ingredient);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(46, "Meal 1213", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "hotdog.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(46 - OFFSET_MEAL)).setIngredients(ingredients);
        } catch (Exception e) {
            System.out.println("DataAccessStub loadRecipeMeals failed " + e);
        }
    }

    private void loadRecipeFood() {
        this.dbRecipeFood = new ArrayList<Edible>();

        try {
            this.dbRecipeFood.add(new Edible()
                    .initDetails(1, "Apple", "Newton's Bane", 100, Edible.Unit.g)
                    .initNutrition(100, 30, 45, 25)
                    .initCategories(true, false, false, false, false)
                    .initMetadata(false, "apple.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(2, "Pear", "This shape bad", 200, Edible.Unit.g)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "pear.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(3, "Cracker", "crack desc", 300, Edible.Unit.g)
                    .initNutrition(300, 40, 50, 10)
                    .initCategories(false, false, true, true, false)
                    .initMetadata(false, "cracker.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(4, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                    .initNutrition(400, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "rice.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(5, "Walnut", "not a floor nut", 1, Edible.Unit.tbsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "walnut.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(6, "Molasse", "Molasse desc", 1, Edible.Unit.oz)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "molasse.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(7, "Cereal", "Cereal desc", 1, Edible.Unit.ml)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "cereal.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(8, "Nutella", "Nutella desc", 1, Edible.Unit.serving)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "nutella.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(9, "Steak", "Steak desc", 1, Edible.Unit.cups)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "steak.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(10, "Banana", "Banana desc", 1, Edible.Unit.liter)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "banana.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(11, "Burger", "Burger desc", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "burger.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(12, "Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "bologna.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(13, "Berry", "Berry desc", 100, Edible.Unit.g)
                    .initNutrition(100, 30, 45, 25)
                    .initCategories(true, false, false, false, false)
                    .initMetadata(false, "berry.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(14, "Burrito", "Burrito desc", 200, Edible.Unit.g)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "burrito.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(15, "Bean", "Bean desc", 300, Edible.Unit.g)
                    .initNutrition(300, 40, 50, 10)
                    .initCategories(false, false, true, true, false)
                    .initMetadata(false, "bean.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(16, "Broccoli", "Broccoli desc", 400, Edible.Unit.g)
                    .initNutrition(400, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "broccoli.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(17, "Biscotti", "Biscotti desc", 1, Edible.Unit.tbsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "biscotti.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(18, "Bun", "Bun desc", 1, Edible.Unit.oz)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "bun.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(19, "Risotto", "Risotto desc", 1, Edible.Unit.ml)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "risotto.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(20, "Ham", "Ham desc", 1, Edible.Unit.serving)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "ham.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(21, "Pizza", "Pizza desc", 1, Edible.Unit.cups)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "pizza.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(22, "Potatoes", "Potatoes desc", 1, Edible.Unit.liter)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "potatoes.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(23, "Carrot", "Carrot desc", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "carrot.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(24, "Mint", "Mint desc", 1, Edible.Unit.tsp)
                    .initNutrition(100, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "mint.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(25, "Lime", "Mint desc", 1, Edible.Unit.serving)
                    .initNutrition(50, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "lime.jpg")
            );
        } catch (Exception e) {
            System.out.println("DataAccessStub loadFoodRecipes failed " + e);

        }
    }

}