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
        Integer today = calendarToInt(calendar);
        DailyLog currLog = new DailyLog();

        ArrayList<Edible> logDay = new ArrayList<Edible>();
        logDay.add(this.dbRecipeFood.get(0));
        logDay.add(this.dbRecipeFood.get(1));
        logDay.add(this.dbRecipeFood.get(2));
        logDay.add(this.dbRecipeFood.get(3));
        logDay.addAll(emptyLog());

        this.dbFoodLog = new ArrayList<DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog

        currLog.init(today, 1500, 300, 0, logDay);
        this.dbFoodLog.add(currLog);

        currLog = new DailyLog();
        logDay = new ArrayList<Edible>();
        logDay.add(this.dbRecipeFood.get(4));
        logDay.add(this.dbRecipeFood.get(5));
        logDay.add(this.dbRecipeFood.get(6));
        logDay.add(this.dbRecipeFood.get(7));
        logDay.add(this.dbRecipeFood.get(8));
        logDay.addAll(emptyLog());
        currLog.init(today - 1, 1700, 300, 200, logDay);
        this.dbFoodLog.add(currLog);

        currLog = new DailyLog();
        logDay = new ArrayList<Edible>();
        logDay.add(this.dbRecipeFood.get(9));
        logDay.add(this.dbRecipeFood.get(10));
        logDay.add(this.dbRecipeFood.get(11));
        logDay.add(this.dbRecipeFood.get(12));
        logDay.add(this.dbRecipeFood.get(13));
        logDay.addAll(emptyLog());
        currLog.init(today - 2, 1300, 300, 30, logDay);
        this.dbFoodLog.add(currLog);

        currLog = new DailyLog();
        logDay = new ArrayList<Edible>();
        logDay.add(this.dbRecipeFood.get(4));
        logDay.add(this.dbRecipeFood.get(5));
        logDay.add(this.dbRecipeFood.get(6));
        logDay.addAll(emptyLog());
        currLog.init(today + 1, 1800, 300, 100, logDay);
        this.dbFoodLog.add(currLog);

        this.sortDBFoodLog();
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

    private ArrayList<Edible> emptyLog() {
        ArrayList<Edible> result = new ArrayList<Edible>();
        Food addLog = new Food();
        addLog.init("uielement", 0, 0, ListItem.FragmentType.diaryAdd, Edible.Unit.cups, 1, 0);

        result.add(addLog);

        return result;
    }

    private void loadRecipeDrinks() {
        Drink drinkItem = new Drink();
        int caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink = new ArrayList<Edible>();

        drinkItem.init("Mojito", R.drawable.food, caloriesRandom, new String("Mix them"),
                new String("White Rum 1.5oz\nSugar 2TBSP\nMint 8leaves\nLime 1.2 lime\nClub soda 0 fill\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("Mai-tai", R.drawable.food2, caloriesRandom, new String("Mix them"),
                new String("Coconut Malibu 1.5oz\nRum 1.5oz\nPineapple juice 3oz\nOrange juice 2oz\nGrenadine 1dash\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("Red Headed \"friend\"", R.drawable.food3, caloriesRandom, new String("Mix them"),
                new String("Jagermeister 1oz\nCranberry juice 1oz\nPeach Schnapps 1oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("Fun On The Beach", R.drawable.food4, caloriesRandom, new String("Mix them"),
                new String("Vodka 1.5oz\nPeach Schnapps 0.5oz\nChamboard 1 / 2oz\nOrange juice 1.5oz\nCranberry juice 1.5oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("Non Alcoholic Moscow Mule", R.drawable.food, caloriesRandom, new String("Mix them"),
                new String("Lime juice 1TBSP\nGinger beer 4oz\nClub soda 0fill\nMint 8leaves\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("Blue Hawaiian", R.drawable.food2, caloriesRandom, new String("Mix them"),
                new String("Pineapple juice 2oz\nLight rum 1oz\nBlue Curacao 1oz\nCoconut Malibu 1oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("French Martini", R.drawable.food3, caloriesRandom, new String("Mix them"),
                new String("Pineapple juice 3oz\nChamboard 1oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        drinkItem = new Drink();
        drinkItem.init("Non Alcoholic Mojito", R.drawable.food4, caloriesRandom, new String("Mix them"),
                new String("Sugar 2TBSP\nMint 8leaves\nLime 1 / 2lime\nClub soda 0fill\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeDrink.add(drinkItem);

    }

    private void loadRecipeFood() {
        Food foodItem = null;
        this.dbRecipeFood = new ArrayList<Edible>();

        foodItem = new Food();
        foodItem.init("apple", R.drawable.apple, 20, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("pear", R.drawable.pear, 50, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("cracker", R.drawable.cracker, 10, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("grain of rice", R.drawable.rice, 5, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("walnut", R.drawable.walnut, 25, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("molasse", R.drawable.food2, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("cereal", R.drawable.cereal, 260, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("nutella", R.drawable.nutella, 460, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("steak", R.drawable.steak, 600, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Banana", R.drawable.banana, 100, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 100, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Burger", R.drawable.burger, 800, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 500, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Bologna", R.drawable.bologna, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 150, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Berry", R.drawable.berry, 10, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 20, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Burrito", R.drawable.burrito, 300, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 400, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Bean", R.drawable.bean, 30, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 5, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Broccoli", R.drawable.broccoli, 20, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 120, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Biscotti", R.drawable.biscotti, 110, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 20, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Bun", R.drawable.bun, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 200, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Risotto", R.drawable.risotto, 100, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 100, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Ham", R.drawable.ham, 800, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 500, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Pizza", R.drawable.pizza, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 150, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Steak", R.drawable.steak, 10, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 20, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Potatoes", R.drawable.potatoes, 300, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 400, getNextKey());
        this.dbRecipeFood.add(foodItem);

        foodItem = new Food();
        foodItem.init("Carrot", R.drawable.carrot, 30, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 5, getNextKey());
        this.dbRecipeFood.add(foodItem);
    }

    private void loadRecipeMeals() {
        Meal mealItem = null;
        this.dbRecipeMeal = new ArrayList<Edible>();

        mealItem = new Meal();
        mealItem.init("soup", R.drawable.soup, 270,
                new String("broth 10 cups 100 cals\n onion 5 cups 50 cals\n brocoli 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("salad", R.drawable.salad, 150,
                new String("lettuce 10 cups 100 cals\n tomato 5 cups 50 cals\n onion 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("yogurt parfait", R.drawable.parfait, 175,
                new String("yogurt 10 cups 100 cals\n oats 5 cups 50 cals\n Stawberry 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("smoothie", R.drawable.food3, 500,
                new String("milk 10 cups 100 cals\n oats 5 cups 50 cals\n banana 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("rice pilaf", R.drawable.food, 420,
                new String("cucumber 10 cups 100 cals\n rice 5 cups 50 cals\n bread 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("sushi", R.drawable.food4, 320,
                new String("rice 10 cups 100 cals\n cream cheese5 cups 50 cals\n nori 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("wrap", R.drawable.food2, 200,
                new String("steak 10 cups 100 cals\n pesto 5 cups 50 cals\n lettuce 7 cups 80 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);

        mealItem = new Meal();
        mealItem.init("shrimp tacos", R.drawable.food, 160,
                new String("shrimp 10 cups 100 cals\n taco shell 10 cups 100 cals\n cheese 10 cups 100 cals\n lettuce 10 cups 100 cals\n"),
                new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey());
        this.dbRecipeMeal.add(mealItem);
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
        int currLog = this.dbFoodLog.indexOf(searchFoodLogByDate(date));
        DailyLog currEntry;
        int currLogIndex;

        sortDBFoodLog();
        currLogIndex = this.dbFoodLog.indexOf(currLog);
        this.currUser.setCalorieGoal(goal);

        for(int i = currLogIndex; i < this.dbFoodLog.size(); i++) {
            currEntry = this.dbFoodLog.get(i);
            this.dbFoodLog.remove(currEntry);
            currEntry.setCalGoal(goal);
            this.dbFoodLog.add(currEntry);
        }        
    }

    public void setExerciseGoal(int goal, Calendar date) {
        int currLog = this.dbFoodLog.indexOf(searchFoodLogByDate(date));
        DailyLog currEntry;
        int currLogIndex;

        sortDBFoodLog();
        currLogIndex = this.dbFoodLog.indexOf(currLog);
        this.currUser.setExerciseGoal(goal);

        for(int i = currLogIndex; i < this.dbFoodLog.size(); i++) {
            currEntry = this.dbFoodLog.get(i);
            this.dbFoodLog.remove(currEntry);
            currEntry.setCalGoal(goal);
            this.dbFoodLog.add(currEntry);
        }
    }

    public void setExerciseActual(int exerciseActual, Calendar date) {
        DailyLog currEntry = searchFoodLogByDate(date);
        
        this.dbFoodLog.remove(currEntry);
        currEntry.setCalGoal(exerciseActual);
        this.dbFoodLog.add(currEntry);
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
            if (intDate.intValue() == this.dbFoodLog.get(i).getDate().intValue()) {
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