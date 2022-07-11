package comp3350.team10.persistence;


import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.PreparedItem;
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
    ArrayList<Integer[]> history;               //user history

    public DataAccessStub(String dbName) {
        this.calendar = Calendar.getInstance();
        this.calendar.set(Calendar.MONTH, 9);
        this.calendar.set(Calendar.DAY_OF_MONTH, 10);
        this.dbName = dbName;
        this.currKey = 100;
    }

    public void open(String dbName) {
        //Load edible data
        this.loadRecipeFood();
        this.loadRecipeMeals();
        this.loadRecipeDrinks();

        //Load user data
        this.loadUser();
        this.loadFoodlog();
        this.loadHistory();
    }

    private void loadUser() {
        currUser = new User().init(USER_ID, "USER", 666, 666, 666, 666);
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

    public EdibleLog findEdibleByKey(int key, boolean isCustom) {
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

    public User getUser() {
        return this.currUser;
    }

    public void setHeight(int userID, int value) throws IllegalArgumentException {
        try {
            if (value >= 1 && value <= Constant.ENTRY_MAX_VALUE) {
                findUserByID(userID);
                this.currUser.setHeight(value);
            } else {
                throw new IllegalArgumentException("DataAccessStub setHeight requires values " + 1 + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setHeight invalid value provided " + e);
        }
    }

    public void setWeight(int userID, int value) throws IllegalArgumentException {
        try {
            if (value >= 1 && value <= Constant.ENTRY_MAX_VALUE) {
                findUserByID(userID);
                this.currUser.setWeight(value);
            } else {
                throw new IllegalArgumentException("DataAccessStub setWeight requires values " + 1 + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setWeight invalid value provided " + e);
        }
    }

    public void setCalorieGoal(int userID, double value) throws IllegalArgumentException {
        try {
            if (value >= Constant.ENTRY_MIN_VALUE && value <= Constant.ENTRY_MAX_VALUE) {
                findUserByID(userID);
                this.currUser.setCalorieGoal(value);
            } else {
                throw new IllegalArgumentException("DataAccessStub setCalorieGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setCalorieGoal invalid value provided " + e);
        }
    }

    public void setExerciseGoal(int userID, double value) throws IllegalArgumentException {
        try {
            if (value >= Constant.ENTRY_MIN_VALUE && value <= Constant.ENTRY_MAX_VALUE) {
                findUserByID(userID);
                this.currUser.setExerciseGoal(value);
            } else {
                throw new IllegalArgumentException("DataAccessStub setExerciseGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setExerciseGoal invalid value provided " + e);
        }
    }

    public void setLogCalorieGoal(int userID, double goal, Calendar date) throws IllegalArgumentException {
        DailyLog currEntry = null;

        try {
            if (goal >= Constant.ENTRY_MIN_VALUE && goal <= Constant.ENTRY_MAX_VALUE) {
                currEntry = searchFoodLogByDate(userID, date);
                findUserByID(userID);
                sortDBFoodLog();
                currEntry.setCalorieGoal(goal);
            } else {
                throw new IllegalArgumentException("DataAccessStub setCalorieGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setCalorieGoal invalid value provided " + e);
        }
    }

    public void setLogExerciseGoal(int userID, double goal, Calendar date) throws IllegalArgumentException {
        DailyLog currEntry = null;

        try {
            if (goal >= Constant.ENTRY_MIN_VALUE && goal <= Constant.ENTRY_MAX_VALUE) {
                currEntry = searchFoodLogByDate(userID, date);
                findUserByID(userID);
                sortDBFoodLog();
                currEntry.setExerciseGoal(goal);
            } else {
                throw new IllegalArgumentException("DataAccessStub setLogExerciseGoal requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setLogExerciseGoal invalid value provided " + e);
        }
    }

    public void setExerciseActual(int userID, double exerciseActual, Calendar date) throws IllegalArgumentException {
        DailyLog currEntry = null;

        try {
            if (exerciseActual >= Constant.ENTRY_MIN_VALUE && exerciseActual <= Constant.ENTRY_MAX_VALUE) {
                findUserByID(userID);
                currEntry = searchFoodLogByDate(userID, date);
                this.dbFoodLog.remove(currEntry);
                currEntry.setExerciseActual(exerciseActual);
                this.dbFoodLog.add(currEntry);

            } else {
                throw new IllegalArgumentException("DataAccessStub setExerciseActual requires values " + Constant.ENTRY_MIN_VALUE + "<= value <=" + Constant.ENTRY_MAX_VALUE);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("DataAccessStub setExerciseActual invalid value provided " + e);
        }
    }

    private ArrayList<Edible> getDeepCopy(ArrayList<Edible> source) {
        ArrayList<Edible> arrayCopy = new ArrayList<Edible>();
        Edible copy = null;

        for (int i = 0; i < source.size(); i++) {
            copy = source.get(i).clone();
            arrayCopy.add(copy);
        }

        return arrayCopy;
    }

    //This section implements RecipeDBInterface
    public ArrayList<Edible> getFoodRecipes() {
        return getDeepCopy(this.dbRecipeFood);
    }

    public ArrayList<Edible> getMealRecipes() {
        return getDeepCopy(this.dbRecipeMeal);
    }

    public ArrayList<Edible> getDrinkRecipes() {
        return getDeepCopy(this.dbRecipeDrink);
    }

    public void addFoodToRecipeBook(Edible newFood) throws IllegalArgumentException {
        if (newFood != null) {
            if (!(newFood instanceof PreparedItem)) {
                if (findEdibleByKey(newFood.getDbkey(), false) == null) {
                    this.dbRecipeFood.add(newFood.clone());
                } else {
                    throw new IllegalArgumentException("DB addFoodToRecipeBook cannot add duplicate dbkey");
                }
            } else {
                throw new IllegalArgumentException("DB addFoodToRecipeBook cannot add prepared items");
            }
        } else {
            throw new IllegalArgumentException("DB addFoodToRecipeBook cannot be null");
        }
    }

    public void addMealToRecipeBook(Meal newMeal) throws IllegalArgumentException {
        if (newMeal != null) {
            if (findEdibleByKey(newMeal.getDbkey(), false) == null) {
                this.dbRecipeMeal.add(newMeal.clone());
            } else {
                throw new IllegalArgumentException("DB addFoodToRecipeBook cannot add duplicate dbkey");
            }
        } else {
            throw new IllegalArgumentException("DB addMealToRecipeBook cannot be null");
        }
    }

    public void addDrinkToRecipeBook(Drink newDrink) throws IllegalArgumentException {
        if (newDrink != null) {
            if (findEdibleByKey(newDrink.getDbkey(), false) == null) {
                this.dbRecipeDrink.add(newDrink.clone());
            } else {
                throw new IllegalArgumentException("DB addFoodToRecipeBook cannot add duplicate dbkey");
            }
        } else {
            throw new IllegalArgumentException("DB addDrinkToRecipeBook cannot be null");
        }
    }

    private User findUserByID(int userID) throws IllegalArgumentException {
        User result = null;
        if (userID == this.currUser.getUserID()) {
            result = this.currUser;
        } else {
            throw new IllegalArgumentException("DB findUserByID User does not exist");
        }
        return result;
    }

    //This section implements DiaryDBInterface
    public DailyLog searchFoodLogByDate(int userID, Calendar date) throws IllegalArgumentException {
        Integer intDate = null;
        DailyLog foundLog = null;

        try {
            findUserByID(userID);
            if (date != null) {
                intDate = calendarToInt(date);
                for (int i = 0; i < this.dbFoodLog.size() && foundLog == null; i++) {
                    if (intDate.intValue() == calendarToInt(this.dbFoodLog.get(i).getDate()).intValue()) {
                        foundLog = dbFoodLog.get(i);
                    }
                }

                if (foundLog == null) {
                    foundLog = new DailyLog().init(date, new ArrayList<Edible>(), this.getUserCalorieGoal(userID), this.getUserExerciseGoal(userID), 0);
                    this.dbFoodLog.add(foundLog);
                }
            } else {
                throw new IllegalArgumentException("DB searchFoodLogByDate Date cannot be null");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }

        return foundLog;
    }

    private double getUserCalorieGoal(int userID) {
        return this.currUser.getCalorieGoal();
    }

    private double getUserExerciseGoal(int userID) {
        return this.currUser.getExerciseGoal();
    }

    public void replaceLog(int userID, DailyLog newLog) throws IllegalArgumentException {
        try {
            findUserByID(userID);
            if (newLog != null) {
                if (this.dbFoodLog != null) {
                    this.deleteLog(userID, newLog.getDate());
                    this.dbFoodLog.add(newLog.clone());
                }
            } else {
                throw new IllegalArgumentException("DataAccessStub replaceLog cannot be null");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private void deleteLog(int userID, Calendar date) throws IllegalArgumentException {
        int foundLog = -1;
        Calendar currDate = null;
        try {
            findUserByID(userID);
            if (date != null) {
                for (int i = 0; i < this.dbFoodLog.size() && foundLog == -1; i++) {
                    currDate = this.dbFoodLog.get(i).getDate();
                    if (calendarToInt(date).intValue() == calendarToInt(currDate).intValue()) {
                        foundLog = i;
                    }
                }
                if (foundLog >= 0) {
                    this.dbFoodLog.remove(foundLog);
                }
            } else {
                throw new IllegalArgumentException("DB searchFoodLogByDate Date cannot be null");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public ArrayList<Double> getDataFrame(DataFrame.DataType dataType, int days) throws IllegalArgumentException {
        ArrayList<Double> result = new ArrayList<>();
        if (dataType != null) {
            if (days >= DataFrame.numDays[DataFrame.Span.Week.ordinal()]) {
                for (int i = 0; i < days; i++) {
                    result.add(this.history.get(i)[dataType.ordinal() + 1].doubleValue());
                }
            } else {
                throw new IllegalArgumentException("DB getDataFrame must be >= " + DataFrame.numDays[DataFrame.Span.Week.ordinal()]);
            }
        } else {
            throw new IllegalArgumentException("DB getDataFrame dataType cannot be null");
        }

        return result;
    }

    private void loadFoodlog() {
        Calendar today = (Calendar) this.calendar.clone();
        today.set(Calendar.MONTH, 9);
        today.set(Calendar.DAY_OF_MONTH, 10);

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
                    .initDetails(25, "Lime", "Lime desc", 1, Edible.Unit.serving)
                    .initNutrition(50, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "lime.jpg")
            );
        } catch (Exception e) {
            System.out.println("DataAccessStub loadFoodRecipes failed " + e);

        }
    }

    private void loadHistory() {
        Calendar today = (Calendar) this.calendar.clone();
        today.set(Calendar.MONTH, 9);
        today.set(Calendar.DAY_OF_MONTH, 10);
        this.history = new ArrayList<>();
        Integer[] data = null;
        int calorieConsumed = 1200;
        int exerciseCalories = 600;
        int calorieGoal = 2000;
        int weight = 200;
        int offsetExercise = 0;
        int offsetActual = 0;
        int offsetWeight = 0;
        int offsetGoal = 0;

        for (int i = 0; i < DataFrame.numDays[DataFrame.Span.All.ordinal()]; i++) {

            offsetActual = ThreadLocalRandom.current().nextInt(-200 + i, 400 + i);
            offsetGoal = ThreadLocalRandom.current().nextInt(-200, 200);
            offsetExercise = ThreadLocalRandom.current().nextInt(-200, 200);
            offsetWeight = ThreadLocalRandom.current().nextInt(-1 - (i % 29), 5);
            today.add(Calendar.DAY_OF_YEAR, -i);
            data = new Integer[5];

            data[0] = new Integer(calendarToInt(today));
            data[1] = new Integer(calorieGoal + offsetGoal);
            data[2] = new Integer(calorieConsumed + offsetActual);
            data[3] = new Integer(exerciseCalories + offsetExercise);
            data[4] = new Integer(weight + offsetWeight);
            history.add(data);
        }
    }

}
