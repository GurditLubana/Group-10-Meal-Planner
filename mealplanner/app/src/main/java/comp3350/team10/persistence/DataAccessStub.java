package comp3350.team10.persistence;

import comp3350.team10.objects.*;
import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.persistence.DailyLog;

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

    private ArrayList<comp3350.team10.persistence.DailyLog> dbFoodLog;      //Food log
    private ArrayList<Routine> dbRoutines;      //Routines
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
        this.loadExercises();
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
        if(goal >= 0 && goal <= GOAL_LIMIT) {
            selectedFoodLog.setCalGoal(goal);
            this.updateFoodLogDB();
        }
    }

    public int getExerciseGoal() {
        return selectedFoodLog.getExcGoal();
    }

    public void setExerciseGoal(int goal) {
        if(goal >= 0 && goal <= GOAL_LIMIT) {
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
        if(selectedDate.intValue() != calendarToInt(date).intValue()) {
            selectedFoodLog = setSelectedFoodLog(calendarToInt(date));
            selectedDate = calendarToInt(date);
        }

        return selectedFoodLog.getFoodList();
    }

    public void updateSelectedFoodLogFoodList(ArrayList<Edible> newList) {
        if(newList != null) {
            selectedFoodLog.setFoodList(newList);
            this.updateFoodLogDB();
        }
    }

    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }

    private comp3350.team10.persistence.DailyLog setSelectedFoodLog(Integer date) {
        comp3350.team10.persistence.DailyLog result = new comp3350.team10.persistence.DailyLog(date, 1500, 0, 0, emptyLog());
        int position = this.getFoodLogDBIndex(date);

        if(position == -1) {
            dbFoodLog.add(result);
        } 
        else {
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

        if(dbFoodLog.size() > 0) {
            for(int i = 0; i < dbFoodLog.size() && !found; i++) {
                if(date.intValue() == dbFoodLog.get(i).getDate().intValue()) {
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
        } 
        else if (edibleType == "DRINKS") {
            currEdibles.addAll(dbRecipeDrink);
        } 
        else if (edibleType == "MEALS") {
            currEdibles.addAll(dbRecipeMeal);
        }

        Collections.shuffle(currEdibles);

        return currEdibles;
    }

    public void addFoodToRecipeBook(Edible newFood){
        if(newFood != null) {
            dbRecipeFood.add(newFood);
        }
    }

    public void addMealToRecipeBook(Edible newMeal){
        if(newMeal != null) {
            dbRecipeMeal.add(newMeal);
        }
    }

    public void addDrinkToRecipeBook(Edible newDrink){
        if(newDrink != null) {
            dbRecipeDrink.add(newDrink);
        }
    }

    public Edible findEdibleByKey(int key) {
        boolean found = false;
        Edible result = null;

        for(int i = 0; i < dbRecipeFood.size() && !found; i++) {
            if(dbRecipeFood.get(i).getDbkey() == key) {
                found = true;
                result = dbRecipeFood.get(i);
            }
        }
        for(int i = 0; i < dbRecipeMeal.size() && !found; i++) {
            if(dbRecipeFood.get(i).getDbkey() == key) {
                found = true;
                result = dbRecipeMeal.get(i);
            }
        }
        for(int i = 0; i < dbRecipeDrink.size() && !found; i++) {
            if(dbRecipeFood.get(i).getDbkey() == key) {
                found = true;
                result = dbRecipeDrink.get(i);
            }
        }

        return result;
    }

    public Integer getNextKey() {
        Integer result = currKey.intValue();

        return ++result;
    }

    private void loadFoodlog() {
        Integer today = calendarToInt(calendar);
        this.dbFoodLog = new ArrayList<comp3350.team10.persistence.DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog

        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today, 1500, 300, 0, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today - 1, 1700, 300, 200, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today - 2, 1600, 300, 120, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today - 3, 1300, 300, 30, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today - 4, 1800, 300, 300, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today - 5, 1500, 300, 100, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today - 6, 1600, 300, 300, randomLog()));
        this.dbFoodLog.add(new comp3350.team10.persistence.DailyLog(today + 1, 1600, 300, 400, randomLog()));

        this.sortDBFoodLog();
    }

    private void sortDBFoodLog() {
        Collections.sort(dbFoodLog, new Comparator<comp3350.team10.persistence.DailyLog>() {
            @Override
            public int compare(comp3350.team10.persistence.DailyLog left, comp3350.team10.persistence.DailyLog right) {
                int result = 0;

                if(left.getDate() < right.getDate()) {
                    result = -1;
                } 
                else if(left.getDate() < right.getDate()) {
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

        if(dbRecipeFood != null) {
            result.addAll(dbRecipeFood);
            Collections.shuffle(result.subList(0, 14));
            randomCount = ThreadLocalRandom.current().nextInt(result.size() / 2, result.size());

            for(int i = 0; i < randomCount; i++) {
                randomIndex = ThreadLocalRandom.current().nextInt(0, result.size() - 1);
                result.remove(randomIndex);
            }
        }

        for(int i = 0; i < result.size(); i++) {
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

    private void loadExercises() {
        this.dbRoutines = new ArrayList<Routine>();

        Workout chestWorkout = new Workout(new Exercise[]{
                new Exercise("Incline dumbell press", "Put the bench at 45 degrees", 9, 3, 5),
                new Exercise("Lat pull down", "Lead with elbows and go slow", 9, 3, 4),
                new Exercise("Benchpress", "ego lifting is bad", 9, 3, 8),
                new Exercise("Bent over rows", "keep that back flat", 8, 3, 4),
                new Exercise("Chest fly", "Move arms accross chest, dont go too low", 9, 3, 3)
        });

        Workout armWorkout = new Workout(new Exercise[]{
                new Exercise("Dumbbell waiter curls", "keep those elbows in", 8, 4, 5),
                new Exercise("Cable tricep pulldowns", "focus on pushing with triceps", 8, 4, 4),
                new Exercise("Cheat curls", "dont go toooo crazy", 6, 4, 5),
                new Exercise("Banded tricep extensions", "use a close grip", 12, 3, 4),
                new Exercise("Dumbell curls", "keep those elbows in", 8, 4, 5)
        });

        Workout legWorkout = new Workout(new Exercise[]{
                new Exercise("Leg press", "focus on pressing into the machine/ground", 16, 3, 7),
                new Exercise("Fire hydrants", "keep your knee in", 20, 3, 7),
                new Exercise("Squat", "focus on pressing into the machine/ground", 16, 3, 8),
                new Exercise("Donkey kicks", "try to point your toes and get them up", 20, 3, 7),
                new Exercise("Calf extensions", "you still need to be able to walk to get home", 12, 3, 8)
        });

        Workout cardio = new Workout(new Exercise[]{
                new Exercise("Eliptical", "its better to sprint for a little than walk for awhile", 20, 3, 100)
        });

        Workout rest = new Workout(new Exercise[]{
                new Exercise("Rest", "take a break you deserve it... hopefully", 0, 0, 0)
        });

        this.dbRoutines.add(new Routine("build muscle", new Workout[]{chestWorkout, armWorkout, legWorkout, rest, chestWorkout, legWorkout, rest}));
        this.dbRoutines.add(new Routine("lose weight", new Workout[]{cardio, cardio, cardio, cardio, cardio, cardio, cardio}));
        this.dbRoutines.add(new Routine("tone", new Workout[]{chestWorkout, cardio, armWorkout, cardio, legWorkout, cardio, rest}));
    }

    private void loadRecipeDrinks() {
        int caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink = new ArrayList<Edible>();

        this.dbRecipeDrink.add(new Drink("Mojito", R.drawable.food, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("White Rum", 1.5, "oz", false, true),
                        new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                        new DrinkIngredient("Mint", 8, "leaves", false, false),
                        new DrinkIngredient("Lime", 1.2, "lime", false, false),
                        new DrinkIngredient("Club soda", 0, "fill", true, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Mai-tai", R.drawable.food2, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Coconut Malibu", 1.5, "oz", true, false),
                        new DrinkIngredient("Rum", 1.5, "oz", true, false),
                        new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                        new DrinkIngredient("Orange juice", 2, "oz", true, false),
                        new DrinkIngredient("Grenadine", 1, "dash", false, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Red Headed \"friend\"", R.drawable.food3, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Jagermeister", 1, "oz", false, true),
                        new DrinkIngredient("Cranberry juice", 1, "oz", true, false),
                        new DrinkIngredient("Peach Schnapps", 1, "oz", false, true),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Fun On The Beach", R.drawable.food4, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Vodka", 1.5, "oz", false, true),
                        new DrinkIngredient("Peach Schnapps", 0.5, "oz", false, true),
                        new DrinkIngredient("Chamboard", 1 / 2, "oz", false, true),
                        new DrinkIngredient("Orange juice", 1.5, "oz", true, false),
                        new DrinkIngredient("Cranberry juice", 1.5, "oz", true, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Non Alcoholic Moscow Mule", R.drawable.food, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Lime juice", 1, "TBSP", false, false),
                        new DrinkIngredient("Ginger beer", 4, "oz", false, false),
                        new DrinkIngredient("Club soda", 0, "fill", false, false),
                        new DrinkIngredient("Mint", 8, "leaves", false, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Blue Hawaiian", R.drawable.food2, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Pineapple juice", 2, "oz", true, false),
                        new DrinkIngredient("Light rum", 1, "oz", false, true),
                        new DrinkIngredient("Blue Curacao", 1, "oz", false, true),
                        new DrinkIngredient("Coconut Malibu", 1, "oz", false, true),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("French Martini", R.drawable.food3, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                        new DrinkIngredient("Chamboard", 1, "oz", false, true),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        this.dbRecipeDrink.add(new Drink("Non Alcoholic Mojito", R.drawable.food4, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                        new DrinkIngredient("Mint", 8, "leaves", false, false),
                        new DrinkIngredient("Lime", 1 / 2, "lime", false, false),
                        new DrinkIngredient("Club soda", 0, "fill", false, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

    }

    private void loadRecipeFood() {
        this.dbRecipeFood = new ArrayList<Edible>();

        this.dbRecipeFood.add(new Food("apple", R.drawable.apple, 20, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("pear", R.drawable.pear, 50, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("cracker", R.drawable.cracker, 10, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("grain of rice", R.drawable.rice, 5, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("walnut", R.drawable.walnut, 25, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("molasse", R.drawable.food2, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("cereal", R.drawable.cereal, 260, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("nutella", R.drawable.nutella, 460, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("steak", R.drawable.steak, 600, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        this.dbRecipeFood.add(new Food("Banana", R.drawable.banana, 100, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 100, getNextKey()));
        this.dbRecipeFood.add(new Food("Burger", R.drawable.burger, 800, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 500, getNextKey()));
        this.dbRecipeFood.add(new Food("Bologna", R.drawable.bologna, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 150, getNextKey()));
        this.dbRecipeFood.add(new Food("Berry", R.drawable.berry, 10, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20, getNextKey()));
        this.dbRecipeFood.add(new Food("Burrito", R.drawable.burrito, 300, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 400, getNextKey()));
        this.dbRecipeFood.add(new Food("Bean", R.drawable.bean, 30, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 5, getNextKey()));
        this.dbRecipeFood.add(new Food("Broccoli", R.drawable.broccoli, 20, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 120, getNextKey()));
        this.dbRecipeFood.add(new Food("Biscotti", R.drawable.biscotti, 110, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20, getNextKey()));
        this.dbRecipeFood.add(new Food("Bun", R.drawable.bun, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 200, getNextKey()));
        this.dbRecipeFood.add(new Food("Risotto", R.drawable.risotto, 100, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 100, getNextKey()));
        this.dbRecipeFood.add(new Food("Ham", R.drawable.ham, 800, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 500, getNextKey()));
        this.dbRecipeFood.add(new Food("Pizza", R.drawable.pizza, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 150, getNextKey()));
        this.dbRecipeFood.add(new Food("Steak", R.drawable.steak, 10, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20, getNextKey()));
        this.dbRecipeFood.add(new Food("Potatoes", R.drawable.potatoes, 300, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 400, getNextKey()));
        this.dbRecipeFood.add(new Food("Carrot", R.drawable.carrot, 30, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 5, getNextKey()));
    }

    private void loadRecipeMeals() {
        this.dbRecipeMeal = new ArrayList<Edible>();

        this.dbRecipeMeal.add(new Meal("soup", R.drawable.soup, 270, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("broth", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("onion", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("brocoli", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("salad", R.drawable.salad, 150, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("tomato", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("onion", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("yogurt parfait", R.drawable.parfait, 175, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("yogurt", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("oats", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("Stawberry", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("smoothie", R.drawable.food3, 500, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("milk", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("oats", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("banana", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("rice pilaf", R.drawable.food, 420, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("cucumber", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("rice", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("bread", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("sushi", R.drawable.food4, 320, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("rice", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("cream cheese", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("nori", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("wrap", R.drawable.food2, 200, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("steak", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("pesto", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        this.dbRecipeMeal.add(new Meal("shrimp tacos", R.drawable.food, 160, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("shrimp", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("taco shell", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("cheese", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));
    }
}