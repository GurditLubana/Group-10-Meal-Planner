package comp3350.team10.persistence;

import comp3350.team10.objects.*;
import comp3350.team10.R;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;

public class DataAccessStub {
    private final static int GOAL_LIMIT = 9999;

    private static enum DATA_TYPES {FOOD, MEAL, DRINK}

    //Database and app management variables
    private DailyLog selectedFoodLog;       //Currently selected food log
    private Integer selectedDate;           //Currently selected date
    private Calendar calendar;              //A calandar instance
    private Integer currKey;                //The current database key

    //Database info and data variables
    private String dbType = "stub";         //Type of database
    private String dbName;                  //Name of database

    private ArrayList<DailyLog> dbFoodLog;      //Food log
    private ArrayList<Edible> dbRecipeDrink;    //Drink recipes
    private ArrayList<Edible> dbRecipeFood;     //Food
    private ArrayList<Edible> dbRecipeMeal;     //Meal recipes


    public DataAccessStub(String dbName) {
        this.dbName = dbName;

        this.calendar = Calendar.getInstance();
        this.selectedDate = null;
        this.selectedFoodLog = null;
        this.currKey = 1;
    }

    public void open(String dbName) {
        Calendar calendar = Calendar.getInstance();

        //Load data
        this.loadRecipeDrinks();
        this.loadRecipeFood();
        this.loadRecipeMeals();
        this.loadFoodlog();

        //Get the current day's logs
        this.selectedDate = this.calendarToInt(calendar);
        this.selectedFoodLog = this.selectFoodLogByDate(selectedDate);
    }

    public void close() {
        System.out.println("Closed " + this.dbType + " database " + this.dbName);
    }

    public int getCalorieGoal() {
        return selectedFoodLog.getCalGoal();
    }

    public void setCalorieGoal(int goal) {
        if (goal >= 0 && goal <= GOAL_LIMIT) {
            this.selectedFoodLog.setCalGoal(goal);
            this.pushUpdatedLogToDB();
        }
    }

    public int getExerciseGoal() {
        return selectedFoodLog.getExcGoal();
    }

    public void setExerciseGoal(int goal) {
        if (goal >= 0 && goal <= GOAL_LIMIT) {
            this.selectedFoodLog.setExcGoal(goal);
            this.pushUpdatedLogToDB();
        }
    }

    public void setExerciseActual(int exerciseActual) {
        this.selectedFoodLog.setExcActual(exerciseActual);
    }

    public int getExerciseActual() {
        return this.selectedFoodLog.getExcActual();
    }

    public ArrayList<Edible> getFoodList(Calendar date) {
        if (this.selectedDate.intValue() != calendarToInt(date).intValue()) {
            this.selectedFoodLog = selectFoodLogByDate(calendarToInt(date));
            this.selectedDate = calendarToInt(date);
        }

        return this.selectedFoodLog.getFoodList();
    }

    public void updateSelectedFoodLogFoodList(ArrayList<Edible> newList) {
        if (newList != null) {
            this.selectedFoodLog.setFoodList(newList);
            this.pushUpdatedLogToDB();
        }
    }

    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }

    private DailyLog selectFoodLogByDate(Integer date) {
        DailyLog result = new DailyLog();
        int position = this.searchFoodLogByDate(date);

        result.init(date, 1500, 0, 0, emptyLog());

        if (position == -1) {
            this.dbFoodLog.add(result);
        } else {
            result = this.dbFoodLog.get(position);
        }

        this.sortDBFoodLog();

        return result;
    }

    private void pushUpdatedLogToDB() {
        int position = searchFoodLogByDate(this.selectedDate);

        this.dbFoodLog.remove(position);
        this.dbFoodLog.add(this.selectedFoodLog);
    }

    private int searchFoodLogByDate(Integer date) {
        boolean found = false;
        int result = -1;

        if (this.dbFoodLog.size() > 0) {
            for (int i = 0; i < this.dbFoodLog.size() && !found; i++) {
                if (date.intValue() == this.dbFoodLog.get(i).getDate().intValue()) {
                    found = true;
                    result = i;
                }
            }
        }

        return result;
    }

    public LinkedList<Edible> getRecipes(String edibleType) {
        LinkedList<Edible> currEdibles = new LinkedList<Edible>();

        if (edibleType == "FOOD") {
            currEdibles.addAll(this.dbRecipeFood);
        } else if (edibleType == "DRINKS") {
            currEdibles.addAll(this.dbRecipeDrink);
        } else if (edibleType == "MEALS") {
            currEdibles.addAll(this.dbRecipeMeal);
        }

        Collections.shuffle(currEdibles);

        return currEdibles;
    }

    public void addFoodToRecipeBook(Edible newFood) {
        if (newFood != null) {
            this.dbRecipeFood.add(newFood);
        }
    }

    public void addMealToRecipeBook(Edible newMeal) {
        if (newMeal != null) {
            this.dbRecipeMeal.add(newMeal);
        }
    }

    public void addDrinkToRecipeBook(Edible newDrink) {
        if (newDrink != null) {
            this.dbRecipeDrink.add(newDrink);
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

    private void sortDBFoodLog() {
        Collections.sort(dbFoodLog, new Comparator<DailyLog>() {
            @Override
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

    private ArrayList<Edible> randomLog() {
        ArrayList<Edible> result = new ArrayList<Edible>();
        int randomCount = 0;
        int randomIndex = 0;

        if (dbRecipeFood != null) {
            result.addAll(dbRecipeFood);
            Collections.shuffle(result.subList(0, 14));
            randomCount = ThreadLocalRandom.current().nextInt(result.size() / 2, result.size());

            for (int i = 0; i < randomCount; i++) {
                randomIndex = ThreadLocalRandom.current().nextInt(0, result.size() - 1);
                result.remove(randomIndex);
            }
        }

        for (int i = 0; i < result.size(); i++) {
            result.get(i).setFragmentType(ListItem.FragmentType.diaryEntry);
        }

        result.addAll(emptyLog());

        return result;
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
}
