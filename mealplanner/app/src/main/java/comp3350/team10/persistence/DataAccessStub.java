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

    private void loadFoodlog() {
        int today = calendarToInt(calendar);

        this.dbFoodLog = new ArrayList<DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog

        try {
            ArrayList<Edible> logDay = new ArrayList<Edible>();
            logDay.add(new EdibleLog(this.dbRecipeFood.get(0)).init(10, Edible.Unit.cups));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(1)).init(20, Edible.Unit.g));
            logDay.add(new EdibleLog(this.dbRecipeFood.get(2)).init(30, Edible.Unit.ml));
            this.dbFoodLog.add(new DailyLog().init(calendar, logDay, 700, 100, 0));

            this.sortDBFoodLog();
        } catch (Exception e) {
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

                if (calendarToInt(left.getDate()) < calendarToInt(right.getDate())) {
                    result = -1;
                } else if (calendarToInt(left.getDate()) > calendarToInt(right.getDate())) {
                    result = 1;
                }
                return result;
            }
        });
    }

    private void loadRecipeDrinks() {
        this.dbRecipeDrink = new ArrayList<Edible>();
        ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();

        try {
            DrinkIngredient ingredient = (DrinkIngredient) new DrinkIngredient().init(this.dbRecipeFood.get(1), 1.1, Edible.Unit.cups);
            ingredients.add(ingredient);
            ingredients.add(ingredient);

            this.dbRecipeDrink.add(new Drink()
                    .initDetails(1, "Mojito", "The best", 10, Edible.Unit.cups)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "mojito.jpg")
            );
            ((Drink) this.dbRecipeDrink.get(0)).setIngredients(ingredients);
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(2, "Ceasar", "People who siracha in this are gross", 20, Edible.Unit.tbsp)
                    .initNutrition(200, 25, 40, 35)
                    .initMetadata(false, "ceasar.jpg")
            );
            ((Drink) this.dbRecipeDrink.get(1)).setIngredients(ingredients);
            this.dbRecipeDrink.add(new Drink()
                    .initDetails(3, "Mai-Tai", "Also amazing", 30, Edible.Unit.g)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "maitai.jpg")
            );
            ((Drink) this.dbRecipeDrink.get(2)).setIngredients(ingredients);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    private void loadRecipeFood() {
        this.dbRecipeFood = new ArrayList<Edible>();

        try {
            this.dbRecipeFood.add(new Edible()
                    .initDetails(4, "Cheese", "desc", 10, Edible.Unit.cups)
                    .initNutrition(100, 30, 45, 25)
                    .initCategories(true, false, false, false, false)
                    .initMetadata(false, "cheese.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(5, "Chicken", "desc", 20, Edible.Unit.tsp)
                    .initNutrition(200, 25, 40, 35)
                    .initCategories(false, false, false, false, true)
                    .initMetadata(false, "chicken.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(6, "Carrots", "desc", 30, Edible.Unit.g)
                    .initNutrition(300, 40, 50, 10)
                    .initCategories(false, false, true, true, false)
                    .initMetadata(false, "carrots.jpg")
            );
            this.dbRecipeFood.add(new Edible()
                    .initDetails(7, "Rabbit", "desc", 40, Edible.Unit.tbsp)
                    .initNutrition(400, 30, 20, 50)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "rabbit.jpg")
            );
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
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
                    .initDetails(8, "meal1", "desc", 10, Edible.Unit.ml)
                    .initNutrition(100, 30, 45, 25)
                    .initMetadata(false, "photo.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(0)).setIngredients(ingredients);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(9, "meal2", "desc", 20, Edible.Unit.oz)
                    .initNutrition(200, 25, 40, 35)
                    .initMetadata(false, "photo.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(1)).setIngredients(ingredients);
            this.dbRecipeMeal.add(new Meal()
                    .initDetails(10, "Another meal!", "desc", 30, Edible.Unit.cups)
                    .initNutrition(300, 40, 50, 10)
                    .initMetadata(false, "photo.jpg")
            );
            ((Meal) this.dbRecipeMeal.get(2)).setIngredients(ingredients);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public EdibleLog findEdibleByKey(int key, boolean isCustom) throws NoSuchElementException{
        EdibleLog result = null;
        System.out.println("key: " + key);

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
                result =  new EdibleLog(this.dbRecipeDrink.get(i));
            }
        }

        if(result == null){
            throw new NoSuchElementException("Requested item for dbkey does not exist");
        }

        return result;
    }

//    public byte[] getImageAsBytes() {
//        byte[] imageBytes;
//        InputStream ims = getAssets().open("avatar.jpg");
//        BufferedImage img = ImageIO.read(getClass().getResource("/path/to/image"));
//
//        return imageBytes;
//    }

    public int getNextKey() {
        Integer result = this.currKey.intValue();

        this.currKey += 1;

        return result.intValue();
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
            currLogIndex = this.dbFoodLog.indexOf(currEntry);
            this.currUser.setCalorieGoal(goal);

            for (int i = currLogIndex; i < this.dbFoodLog.size(); i++) {
                currEntry = this.dbFoodLog.get(i);
                this.dbFoodLog.remove(currEntry);
                currEntry.setCalorieGoal(goal);
                this.dbFoodLog.add(i, currEntry);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void setExerciseGoal(int userID, double goal, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date, userID);
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

        if( foundLog == null ){
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
}