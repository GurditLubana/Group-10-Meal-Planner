package comp3350.team10.persistence;

import comp3350.team10.objects.*;
import comp3350.team10.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class DataAccessStub  {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<DailyLog> dbFoodLog = new ArrayList<>();           // database of daily food logs DailyLog methods represent db operations
    private ArrayList<Routine> dbRoutines = new ArrayList<Routine>();
    private ArrayList<Edible> dbRecipeDrink = new ArrayList<Edible>();                 // db of drinks
    private ArrayList<Edible> dbRecipeFood = new ArrayList<Edible>();                   // db of food items
    private ArrayList<Edible> dbRecipeMeal = new ArrayList<Edible>();                   // db of meals with multiple food items
    Calendar calendar = Calendar.getInstance();                          // today's date
    private DailyLog selectedFoodLog = null;       // single food log from the db of food logs
    private Integer selectedDate = null;
    private Integer currKey = 1;



    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(SharedDB.dbName);
    }

    public void open(String dbName) {
        DiaryItem mealEntry;
        RecipeBookItem recipeEntry;
        int caloriesRandom = 0;


        //System.out.println("before calandar"); //bruh
        Calendar calendar = Calendar.getInstance();
        //System.out.println("after calandar");
        //System.out.println("TIME: " + calendar.getTime());
        loadExercises();
        loadRecipeDrinks();
        loadRecipeFood();
        loadRecipeMeals();
        loadFoodlog();

        selectedDate = calendarToInt(calendar);
        selectedFoodLog = setSelectedFoodLog(selectedDate);
        //caches database recipes into memory
        //System.out.println("Cached recipes");
        //System.out.println("Opened " + dbType + " database " + dbName);



    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public int getCalorieGoal() {
        return selectedFoodLog.getCalGoal();
    }

    public void setCalorieGoal(int goal) {
        if (goal >= 0 && goal <= 9999) {
            selectedFoodLog.setCalGoal(goal);
            updateFoodLogDB();
        }
    }

    public int getExerciseGoal() {
        return selectedFoodLog.getExcGoal();
    }

    public void setExerciseGoal(int goal) {
        if (goal >= 0 && goal <= 9999) {
            selectedFoodLog.setExcGoal(goal);
            updateFoodLogDB();
        }
    }

    public void setExerciseActual(int exerciseActual) {
        selectedFoodLog.setExcActual(exerciseActual);
    }

    public int getExerciseActual() {
        return selectedFoodLog.getExcActual();
    }

    public ArrayList<ListItem> getFoodList(Calendar date) {
        ArrayList<ListItem> result = null;

        if (selectedDate.intValue() != calendarToInt(date).intValue()) {
            selectedFoodLog = setSelectedFoodLog(calendarToInt(date));
            selectedDate = calendarToInt(date);
        }
        return selectedFoodLog.getFoodList();
    }

    public void updateSelectedFoodLogFoodList(ArrayList<ListItem> newList) {
        if (newList != null) {
            selectedFoodLog.setFoodList(newList);
            updateFoodLogDB();
        }
    }

    private Integer calendarToInt(Calendar date) {
        String stringDate = String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR));
        return Integer.parseInt(stringDate);
    }

    private DailyLog setSelectedFoodLog(Integer date) {

        DailyLog result = new DailyLog(date, 1500, 0, 0, emptyLog());
        int position = getFoodLogDBIndex(date);

        if (position == -1) {
            dbFoodLog.add(result);
        } else {
            result = dbFoodLog.get(position);
        }
        sortDBFoodLog();
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

    public LinkedList<ListItem> getRecipes(String edibleType) {
        LinkedList<ListItem> currEdibles = new LinkedList<ListItem>();

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

    public void addFoodToRecipeBook(Edible newFood){
        if(newFood != null){
            dbRecipeFood.add(newFood);
        }
    }

    public void addMealToRecipeBook(Edible newMeal){
        if(newMeal != null){
            dbRecipeMeal.add(newMeal);
        }
    }

    public void addDrinkToRecipeBook(Edible newDrink){
        if(newDrink != null){
            dbRecipeDrink.add(newDrink);
        }
    }

    public Edible findEdibleByKey(int key) {
        boolean found = false;
        Edible result = null;
        for (int i = 0; i < dbRecipeFood.size() && !found; i++) {
            if (dbRecipeFood.get(i).getDbkey() == key) {
                found = true;
                result = dbRecipeFood.get(i);
            }
        }
        for (int i = 0; i < dbRecipeMeal.size() && !found; i++) {
            if (dbRecipeFood.get(i).getDbkey() == key) {
                found = true;
                result = dbRecipeMeal.get(i);
            }
        }
        for (int i = 0; i < dbRecipeDrink.size() && !found; i++) {
            if (dbRecipeFood.get(i).getDbkey() == key) {
                found = true;
                result = dbRecipeDrink.get(i);
            }
        }
        return result;
    }


    public void insertItem(int specDataSet, Food item)
    {
        if(specDataSet == 0) {


            dbRecipeFood.add(new Food("apple", R.drawable.apple, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50, 24));
            System.out.println("Hello world");
//            food.add((Food) item);
        }
//        else if(specDataSet == 1) {

//
//            meal.add((Meal) item);
//
//        }
//        else if(specDataSet == 2) {
//
//            drink.add((Drink) item);
//
//
//        }

    }




    public void addRecipeToLog(Edible item) { //Needs date added to this later, only adds to current
//        System.out.println("Before: " + dailyFoodLog.size());
//        dailyFoodLog.add(item);
//        System.out.println("After: " + dailyFoodLog.size());
    }

    public Integer getNextKey() {
        Integer result = currKey.intValue();
        currKey += 1;
        return result;
    }

    private void loadFoodlog() {
        DailyLog log;
        Integer today = calendarToInt(calendar);
        dbFoodLog = new ArrayList<DailyLog>(); // key = yyyyddd integer , Calorie goal, Exercise goal, actual exercise, Foodlog

        log = new DailyLog(today, 1500, 300, 0, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today - 1, 1700, 300, 200, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today - 2, 1600, 300, 120, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today - 3, 1300, 300, 30, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today - 4, 1800, 300, 300, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today - 5, 1500, 300, 100, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today - 6, 1600, 300, 300, randomLog());
        dbFoodLog.add(log);
        log = new DailyLog(today + 1, 1600, 300, 400, randomLog());
        dbFoodLog.add(log);
        sortDBFoodLog();
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

    private ArrayList<ListItem> randomLog() {
        ArrayList<ListItem> result = new ArrayList<ListItem>();
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

    private ArrayList<ListItem> emptyLog() {
        ArrayList<ListItem> result = new ArrayList<ListItem>();
        result.add(new Food(null, -1, 0, ListItem.FragmentType.diaryAdd, null, -1, -1));
        return result;
    }

    private void loadExercises() {
        //Workouts
        dbRoutines = new ArrayList<Routine>();
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

        dbRoutines.add(new Routine("build muscle", new Workout[]{chestWorkout, armWorkout, legWorkout, rest, chestWorkout, legWorkout, rest}));
        dbRoutines.add(new Routine("lose weight", new Workout[]{cardio, cardio, cardio, cardio, cardio, cardio, cardio}));
        dbRoutines.add(new Routine("tone", new Workout[]{chestWorkout, cardio, armWorkout, cardio, legWorkout, cardio, rest}));

        //System.out.println("added workouts...");
    }

    private void loadRecipeDrinks() {
        int caloriesRandom = 0;
        //Create Drinks
        dbRecipeDrink = new ArrayList<Edible>();
        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Mojito", R.drawable.food, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("White Rum", 1.5, "oz", false, true),
                        new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                        new DrinkIngredient("Mint", 8, "leaves", false, false),
                        new DrinkIngredient("Lime", 1.2, "lime", false, false),
                        new DrinkIngredient("Club soda", 0, "fill", true, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        //System.out.println("added first drink...");
        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Mai-tai", R.drawable.food2, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Coconut Malibu", 1.5, "oz", true, false),
                        new DrinkIngredient("Rum", 1.5, "oz", true, false),
                        new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                        new DrinkIngredient("Orange juice", 2, "oz", true, false),
                        new DrinkIngredient("Grenadine", 1, "dash", false, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Red Headed \"friend\"", R.drawable.food3, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Jagermeister", 1, "oz", false, true),
                        new DrinkIngredient("Cranberry juice", 1, "oz", true, false),
                        new DrinkIngredient("Peach Schnapps", 1, "oz", false, true),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Fun On The Beach", R.drawable.food4, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Vodka", 1.5, "oz", false, true),
                        new DrinkIngredient("Peach Schnapps", 0.5, "oz", false, true),
                        new DrinkIngredient("Chamboard", 1 / 2, "oz", false, true),
                        new DrinkIngredient("Orange juice", 1.5, "oz", true, false),
                        new DrinkIngredient("Cranberry juice", 1.5, "oz", true, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Non Alcoholic Moscow Mule", R.drawable.food, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Lime juice", 1, "TBSP", false, false),
                        new DrinkIngredient("Ginger beer", 4, "oz", false, false),
                        new DrinkIngredient("Club soda", 0, "fill", false, false),
                        new DrinkIngredient("Mint", 8, "leaves", false, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Blue Hawaiian", R.drawable.food2, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Pineapple juice", 2, "oz", true, false),
                        new DrinkIngredient("Light rum", 1, "oz", false, true),
                        new DrinkIngredient("Blue Curacao", 1, "oz", false, true),
                        new DrinkIngredient("Coconut Malibu", 1, "oz", false, true),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("French Martini", R.drawable.food3, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                        new DrinkIngredient("Chamboard", 1, "oz", false, true),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

        caloriesRandom = ThreadLocalRandom.current().nextInt(250, 450);
        dbRecipeDrink.add(new Drink("Non Alcoholic Mojito", R.drawable.food4, caloriesRandom, new String[]{"GET", "GOOD"},
                new DrinkIngredient[]{
                        new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                        new DrinkIngredient("Mint", 8, "leaves", false, false),
                        new DrinkIngredient("Lime", 1 / 2, "lime", false, false),
                        new DrinkIngredient("Club soda", 0, "fill", false, false),
                }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()
        ));

    }

    private void loadRecipeFood() {
        //
        dbRecipeFood = new ArrayList<Edible>();
        dbRecipeFood.add(new Food("apple", R.drawable.apple, 20, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("pear", R.drawable.pear, 50, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("cracker", R.drawable.cracker, 10, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("grain of rice", R.drawable.rice, 5, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("walnut", R.drawable.walnut, 25, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("molasse", R.drawable.food2, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("cereal", R.drawable.cereal, 260, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("nutella", R.drawable.nutella, 460, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("steak", R.drawable.steak, 600, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 50, getNextKey()));
        dbRecipeFood.add(new Food("Banana", R.drawable.banana, 100, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 100, getNextKey()));
        dbRecipeFood.add(new Food("Burger", R.drawable.burger, 800, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 500, getNextKey()));
        dbRecipeFood.add(new Food("Bologna", R.drawable.bologna, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 150, getNextKey()));
        dbRecipeFood.add(new Food("Berry", R.drawable.berry, 10, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20, getNextKey()));
        dbRecipeFood.add(new Food("Burrito", R.drawable.burrito, 300, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 400, getNextKey()));
        dbRecipeFood.add(new Food("Bean", R.drawable.bean, 30, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 5, getNextKey()));
        dbRecipeFood.add(new Food("Broccoli", R.drawable.broccoli, 20, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 120, getNextKey()));
        dbRecipeFood.add(new Food("Biscotti", R.drawable.biscotti, 110, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20, getNextKey()));
        dbRecipeFood.add(new Food("Bun", R.drawable.bun, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 200, getNextKey()));
        dbRecipeFood.add(new Food("Risotto", R.drawable.risotto, 100, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 100, getNextKey()));
        dbRecipeFood.add(new Food("Ham", R.drawable.ham, 800, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 500, getNextKey()));
        dbRecipeFood.add(new Food("Pizza", R.drawable.pizza, 200, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 150, getNextKey()));
        dbRecipeFood.add(new Food("Steak", R.drawable.steak, 10, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20, getNextKey()));
        dbRecipeFood.add(new Food("Potatoes", R.drawable.potatoes, 300, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 400, getNextKey()));
        dbRecipeFood.add(new Food("Carrot", R.drawable.carrot, 30, ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 5, getNextKey()));
    }

    private void loadRecipeMeals() {
        //adding meals
        dbRecipeMeal = new ArrayList<Edible>();
        dbRecipeMeal.add(new Meal("soup", R.drawable.soup, 270, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("broth", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("onion", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("brocoli", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("salad", R.drawable.salad, 150, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("tomato", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("onion", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("yogurt parfait", R.drawable.parfait, 175, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("yogurt", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("oats", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("Stawberry", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("smoothie", R.drawable.food3, 500, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("milk", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("oats", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("banana", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("rice pilaf", R.drawable.food, 420, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("cucumber", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("rice", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("bread", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("sushi", R.drawable.food4, 320, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("rice", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("cream cheese", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("nori", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("wrap", R.drawable.food2, 200, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("steak", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("pesto", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));

        dbRecipeMeal.add(new Meal("shrimp tacos", R.drawable.food, 160, new MealIngredient[]{
                new MealIngredient(5, "cups", new Food("shrimp", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("taco shell", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("cheese", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey())),
                new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10, getNextKey()))
        }, new String[]{"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1, getNextKey()));
        //System.out.println("Added meals");
    }




}
