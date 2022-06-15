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

    public DataAccessStub() {
        this(SharedDB.dbName);

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
        selectedDate = this.calendarToInt(calendar);
        selectedFoodLog = this.setSelectedFoodLog(selectedDate);
    }

    public void close() {
        System.out.println("Closed " + this.dbType + " database " + this.dbName);
    }

    public int getCalorieGoal() {
        return selectedFoodLog.getCalGoal();
    }

    public void setCalorieGoal(int goal) {
        if (goal >= 0 && goal <= GOAL_LIMIT) {
            selectedFoodLog.setCalGoal(goal);
            this.updateFoodLogDB();
        }
    }

    public int getExerciseGoal() {
        return selectedFoodLog.getExcGoal();
    }

    public void setExerciseGoal(int goal) {
        if (goal >= 0 && goal <= GOAL_LIMIT) {
            selectedFoodLog.setExcGoal(goal);
            this.updateFoodLogDB();
        }
    }

    public void setExerciseActual(int exerciseActual) {
        selectedFoodLog.setExcActual(exerciseActual);
    }

    public int getExerciseActual() {
        return selectedFoodLog.getExcActual();
    }

    public ArrayList<Edible> getFoodList(Calendar date) {
        if (selectedDate.intValue() != calendarToInt(date).intValue()) {
            selectedFoodLog = setSelectedFoodLog(calendarToInt(date));
            selectedDate = calendarToInt(date);
        }

        return selectedFoodLog.getFoodList();
    }

    public void updateSelectedFoodLogFoodList(ArrayList<Edible> newList) {
        if (newList != null) {
            selectedFoodLog.setFoodList(newList);
            this.updateFoodLogDB();
        }
    }

    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }

    private DailyLog setSelectedFoodLog(Integer date) {
        DailyLog result = new DailyLog(date, 1500, 0, 0, emptyLog());
        int position = this.getFoodLogDBIndex(date);

        if (position == -1) {
            dbFoodLog.add(result);
        } else {
            result = dbFoodLog.get(position);
        }

        this.sortDBFoodLog();

        return result;
    }

    private void updateFoodLogDB() { // not necessary for java, but necessary for actual db
        int position = getFoodLogDBIndex(selectedDate);

        dbFoodLog.remove(position);
        dbFoodLog.add(selectedFoodLog);
    }

    private int getFoodLogDBIndex(Integer date) {
        boolean found = false;
        int result = -1;

        if (dbFoodLog.size() > 0) {
            for (int i = 0; i < dbFoodLog.size() && !found; i++) {
                if (date.intValue() == dbFoodLog.get(i).getDate().intValue()) {
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
            currEdibles.addAll(dbRecipeFood);
        } else if (edibleType == "DRINKS") {
            currEdibles.addAll(dbRecipeDrink);
        } else if (edibleType == "MEALS") {
            currEdibles.addAll(dbRecipeMeal);
        }

        Collections.shuffle(currEdibles);

        return currEdibles;
    }

    public void addFoodToRecipeBook(Edible newFood) {
        if (newFood != null) {
            dbRecipeFood.add(newFood);
        }
    }

    public void addMealToRecipeBook(Edible newMeal) {
        if (newMeal != null) {
            dbRecipeMeal.add(newMeal);
        }
    }

    public void addDrinkToRecipeBook(Edible newDrink) {
        if (newDrink != null) {
            dbRecipeDrink.add(newDrink);
        }
    }

    public Edible findEdibleByKey(int key) {
        Edible result = null;
        System.out.println("key: " + key);

        for (int i = 0; i < dbRecipeFood.size() && result == null; i++) {
            if (dbRecipeFood.get(i).getDbkey() == key) {
                result = dbRecipeFood.get(i);
            }
        }
        for (int i = 0; i < dbRecipeMeal.size() && result == null; i++) {
            if (dbRecipeMeal.get(i).getDbkey() == key) {
                result = dbRecipeMeal.get(i);
            }
        }
        for (int i = 0; i < dbRecipeDrink.size() && result == null; i++) {
            if (dbRecipeDrink.get(i).getDbkey() == key) {
                result = dbRecipeDrink.get(i);
            }
        }

        return result;
    }

    public Integer getNextKey() {
        Integer result = currKey.intValue();

        currKey += 1;

        return result;
    }

    private void loadFoodlog() {
        Integer today = calendarToInt(calendar);
        this.dbFoodLog = new ArrayList<DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog

        this.dbFoodLog.add(new DailyLog(today, 1500, 300, 0, randomLog()));
        this.dbFoodLog.add(new DailyLog(today - 1, 1700, 300, 200, randomLog()));
        this.dbFoodLog.add(new DailyLog(today - 2, 1600, 300, 120, randomLog()));
        this.dbFoodLog.add(new DailyLog(today - 3, 1300, 300, 30, randomLog()));
        this.dbFoodLog.add(new DailyLog(today - 4, 1800, 300, 300, randomLog()));
        this.dbFoodLog.add(new DailyLog(today - 5, 1500, 300, 100, randomLog()));
        this.dbFoodLog.add(new DailyLog(today - 6, 1600, 300, 300, randomLog()));
        this.dbFoodLog.add(new DailyLog(today + 1, 1600, 300, 400, randomLog()));

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
            ((Edible) result.get(i)).setFragmentType(ListItem.FragmentType.diaryEntry);
        }

        result.addAll(emptyLog());

        return result;
    }

    private ArrayList<Edible> emptyLog() {
        ArrayList<Edible> result = new ArrayList<Edible>();

        result.add(new Food(null, -1, 0, ListItem.FragmentType.diaryAdd, null, -1, -1));

        return result;
    }

    private void loadRecipeDrinks() {
        int caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink = new ArrayList<Edible>();

        this.dbRecipeDrink.add(new Drink("Mojito", R.drawable.food, caloriesRandom, new String("Mix them"),
                new String("White Rum 1.5oz\nSugar 2TBSP\nMint 8leaves\nLime 1.2 lime\nClub soda 0 fill\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Mai-tai", R.drawable.food2, caloriesRandom, new String("Mix them"),
                new String("Coconut Malibu 1.5oz\nRum 1.5oz\nPineapple juice 3oz\nOrange juice 2oz\nGrenadine 1dash\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Red Headed \"friend\"", R.drawable.food3, caloriesRandom, new String("Mix them"),
                new String("Jagermeister 1oz\nCranberry juice 1oz\nPeach Schnapps 1oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Fun On The Beach", R.drawable.food4, caloriesRandom, new String("Mix them"),
                new String("Vodka 1.5oz\nPeach Schnapps 0.5oz\nChamboard 1 / 2oz\nOrange juice 1.5oz\nCranberry juice 1.5oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Non Alcoholic Moscow Mule", R.drawable.food, caloriesRandom, new String("Mix them"),
                new String("Lime juice 1TBSP\nGinger beer 4oz\nClub soda 0fill\nMint 8leaves\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Blue Hawaiian", R.drawable.food2, caloriesRandom, new String("Mix them"),
                new String("Pineapple juice 2oz\nLight rum 1oz\nBlue Curacao 1oz\nCoconut Malibu 1oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("French Martini", R.drawable.food3, caloriesRandom, new String("Mix them"),
                new String("Pineapple juice 3oz\nChamboard 1oz\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Non Alcoholic Mojito", R.drawable.food4, caloriesRandom, new String("Mix them"),
                new String("Sugar 2TBSP\nMint 8leaves\nLime 1 / 2lime\nClub soda 0fill\n"
                ), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()
        ));

    }

    private void loadRecipeFood() {
        this.dbRecipeFood = new ArrayList<Edible>();

        this.dbRecipeFood.add(new Food("apple", R.drawable.apple, 20, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("pear", R.drawable.pear, 50, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("cracker", R.drawable.cracker, 10, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("grain of rice", R.drawable.rice, 5, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("walnut", R.drawable.walnut, 25, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("molasse", R.drawable.food2, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("cereal", R.drawable.cereal, 260, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("nutella", R.drawable.nutella, 460, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("steak", R.drawable.steak, 600, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("Banana", R.drawable.banana, 100, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 100, getNextKey()));
        this.dbRecipeFood.add(new Food("Burger", R.drawable.burger, 800, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 500, getNextKey()));
        this.dbRecipeFood.add(new Food("Bologna", R.drawable.bologna, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 150, getNextKey()));
        this.dbRecipeFood.add(new Food("Berry", R.drawable.berry, 10, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 20, getNextKey()));
        this.dbRecipeFood.add(new Food("Burrito", R.drawable.burrito, 300, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 400, getNextKey()));
        this.dbRecipeFood.add(new Food("Bean", R.drawable.bean, 30, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 5, getNextKey()));
        this.dbRecipeFood.add(new Food("Broccoli", R.drawable.broccoli, 20, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 120, getNextKey()));
        this.dbRecipeFood.add(new Food("Biscotti", R.drawable.biscotti, 110, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 20, getNextKey()));
        this.dbRecipeFood.add(new Food("Bun", R.drawable.bun, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 200, getNextKey()));
        this.dbRecipeFood.add(new Food("Risotto", R.drawable.risotto, 100, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 100, getNextKey()));
        this.dbRecipeFood.add(new Food("Ham", R.drawable.ham, 800, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 500, getNextKey()));
        this.dbRecipeFood.add(new Food("Pizza", R.drawable.pizza, 200, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 150, getNextKey()));
        this.dbRecipeFood.add(new Food("Steak", R.drawable.steak, 10, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 20, getNextKey()));
        this.dbRecipeFood.add(new Food("Potatoes", R.drawable.potatoes, 300, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 400, getNextKey()));
        this.dbRecipeFood.add(new Food("Carrot", R.drawable.carrot, 30, ListItem.FragmentType.diaryEntry, Edible.Unit.g, 5, getNextKey()));
    }

    private void loadRecipeMeals() {
        this.dbRecipeMeal = new ArrayList<Edible>();

        this.dbRecipeMeal.add(new Meal("soup", R.drawable.soup, 270, new String(
                "broth 10 cups 100 cals\n onion 5 cups 50 cals\n brocoli 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("salad", R.drawable.salad, 150, new String(
                "lettuce 10 cups 100 cals\n tomato 5 cups 50 cals\n onion 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("yogurt parfait", R.drawable.parfait, 175, new String(
                "yogurt 10 cups 100 cals\n oats 5 cups 50 cals\n Stawberry 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("smoothie", R.drawable.food3, 500, new String(
                "milk 10 cups 100 cals\n oats 5 cups 50 cals\n banana 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("rice pilaf", R.drawable.food, 420, new String(
                "cucumber 10 cups 100 cals\n rice 5 cups 50 cals\n bread 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("sushi", R.drawable.food4, 320, new String(
                "rice 10 cups 100 cals\n cream cheese5 cups 50 cals\n nori 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("wrap", R.drawable.food2, 200, new String(
                "steak 10 cups 100 cals\n pesto 5 cups 50 cals\n lettuce 7 cups 80 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("shrimp tacos", R.drawable.food, 160, new String(
                "shrimp 10 cups 100 cals\n taco shell 10 cups 100 cals\n cheese 10 cups 100 cals\n lettuce 10 cups 100 cals\n"
        ), new String("Cooking Instructions:\nMix pot\nCook it"), ListItem.FragmentType.recipe, Edible.Unit.serving, 1, getNextKey()));
    }
}