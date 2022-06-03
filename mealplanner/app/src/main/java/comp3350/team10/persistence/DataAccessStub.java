package comp3350.team10.persistence;

import comp3350.team10.application.Main;
import comp3350.team10.objects.*;
import comp3350.team10.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<ListItem> dailyFoodLog;
    private ArrayList<ListItem> secondDailyFoodLog;
    private ArrayList recipeTest;
    private ArrayList<Routine> routines;
    private ArrayList<Drink> drink;
    private ArrayList<Food> food;
    private ArrayList<Meal> meal;
    private int calorieGoal;
    private int exerciseGoal;

    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    public void open(String dbName) {
        DiaryItem mealEntry;
        RecipeBookItem recipeEntry;
        calorieGoal = 1700;
        exerciseGoal = 200;

        //System.out.println("before calandar"); //bruh
        Calendar calendar = Calendar.getInstance();
        //System.out.println("after calandar");
        //System.out.println("TIME: " + calendar.getTime());

        //Meal entries
        dailyFoodLog = new ArrayList<ListItem>();
        dailyFoodLog.add(new Food("Banana", R.drawable.banana, 100,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 100 ));
        dailyFoodLog.add(new Food("Burger", R.drawable.burger, 800,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 500));
        dailyFoodLog.add(new Food("Bologna", R.drawable.bologna, 200,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 150));
        dailyFoodLog.add(new Food("Berry", R.drawable.berry, 10,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20));
        dailyFoodLog.add(new Food("Burrito", R.drawable.burrito, 300,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 400));
        dailyFoodLog.add(new Food("Bean", R.drawable.bean, 30,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 5));
        dailyFoodLog.add(new Food("Broccoli", R.drawable.broccoli, 20,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 120));
        dailyFoodLog.add(new Food("Biscotti", R.drawable.biscotti, 110,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20));
        dailyFoodLog.add(new Food("Bun", R.drawable.bun, 200,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 200));
        dailyFoodLog.add(new Food("Risotto", R.drawable.risotto, 100,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 100 ));
        dailyFoodLog.add(new Food("Ham", R.drawable.ham, 800,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 500));
        dailyFoodLog.add(new Food("Pizza", R.drawable.pizza, 200,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 150));
        dailyFoodLog.add(new Food("Steak", R.drawable.steak, 10,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 20));
        dailyFoodLog.add(new Food("Potatoes", R.drawable.potatoes, 300,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 400));
        dailyFoodLog.add(new Food("Carrot", R.drawable.carrot, 30,ListItem.FragmentType.diaryEntry, ListItem.Unit.g, 5));
        dailyFoodLog.add(new Food("Bell Pepper", R.drawable.food, 0,ListItem.FragmentType.diaryAdd, ListItem.Unit.g, 100));
//        recipeTest = new ArrayList<RecipeBookItem>();
//        recipeEntry = new RecipeBookItem(100, ListItem.FragmentType.recipe, "Banana", 100, ListItem.Unit.g, 50, R.drawable.food);
//        recipeTest.add(recipeEntry);
//        recipeEntry = new RecipeBookItem(101, ListItem.FragmentType.recipe, "Salad", 50, ListItem.Unit.g, 50, R.drawable.food2);
//        recipeTest.add(recipeEntry);
//        recipeEntry = new RecipeBookItem(102, ListItem.FragmentType.recipe, "Hamburglar", 700, ListItem.Unit.g, 400, R.drawable.food3);
//        recipeTest.add(recipeEntry);
//        recipeEntry = new RecipeBookItem(103, ListItem.FragmentType.recipe, "Notfries", 500, ListItem.Unit.g, 30, R.drawable.food4);
//        recipeTest.add(recipeEntry);
//        recipeEntry = new RecipeBookItem(104, ListItem.FragmentType.recipe, "Banana", 100, ListItem.Unit.g, 50, R.drawable.drinks);
//        recipeTest.add(recipeEntry);

        //Workouts
        routines = new ArrayList<Routine>();
        Workout chestWorkout = new Workout(new Exercise[] {
            new Exercise("Incline dumbell press", "Put the bench at 45 degrees", 9, 3, 5),
            new Exercise("Lat pull down", "Lead with elbows and go slow", 9, 3, 4),
            new Exercise("Benchpress", "ego lifting is bad", 9, 3, 8),
            new Exercise("Bent over rows", "keep that back flat", 8, 3, 4),
            new Exercise("Chest fly", "Move arms accross chest, dont go too low", 9, 3, 3)
        });

        Workout armWorkout = new Workout(new Exercise[] {
            new Exercise("Dumbbell waiter curls", "keep those elbows in", 8, 4, 5),
            new Exercise("Cable tricep pulldowns", "focus on pushing with triceps", 8, 4, 4),
            new Exercise("Cheat curls", "dont go toooo crazy", 6, 4, 5),
            new Exercise("Banded tricep extensions", "use a close grip", 12, 3, 4),
            new Exercise("Dumbell curls", "keep those elbows in", 8, 4, 5)
        });

        Workout legWorkout = new Workout(new Exercise[] {
            new Exercise("Leg press", "focus on pressing into the machine/ground", 16, 3, 7),
            new Exercise("Fire hydrants", "keep your knee in", 20, 3, 7),
            new Exercise("Squat", "focus on pressing into the machine/ground", 16, 3, 8),
            new Exercise("Donkey kicks", "try to point your toes and get them up", 20, 3, 7),
            new Exercise("Calf extensions", "you still need to be able to walk to get home", 12, 3, 8)
        });

        Workout cardio = new Workout(new Exercise[] {
            new Exercise("Eliptical", "its better to sprint for a little than walk for awhile", 20, 3, 100)
        });

        Workout rest = new Workout(new Exercise[] {
            new Exercise("Rest", "take a break you deserve it... hopefully", 0, 0, 0)
        });

        routines.add(new Routine("build muscle", new Workout[] {chestWorkout, armWorkout, legWorkout, rest, chestWorkout, legWorkout, rest}));
        routines.add(new Routine("lose weight", new Workout[] {cardio, cardio, cardio, cardio, cardio, cardio, cardio}));
        routines.add(new Routine("tone", new Workout[] {chestWorkout, cardio, armWorkout, cardio, legWorkout, cardio, rest}));

        System.out.println("added workouts...");

        //Create Drinks
        drink = new ArrayList<Drink>();
        drink.add(new Drink("Mojito", R.drawable.food, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("White Rum", 1.5, "oz", false, true),
                new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                new DrinkIngredient("Mint", 8, "leaves", false, false),
                new DrinkIngredient("Lime", 1.2, "lime", false, false),
                new DrinkIngredient("Club soda", 0, "fill", true, false),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        System.out.println("added first drink...");

        drink.add(new Drink("Mai-tai", R.drawable.food2, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Coconut Malibu", 1.5, "oz", true, false),
                new DrinkIngredient("Rum", 1.5, "oz", true, false),
                new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                new DrinkIngredient("Orange juice", 2, "oz", true, false),
                new DrinkIngredient("Grenadine", 1, "dash", false, false),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        drink.add(new Drink("Red Headed \"friend\"", R.drawable.food3, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Jagermeister", 1, "oz", false, true),
                new DrinkIngredient("Cranberry juice", 1, "oz", true, false),
                new DrinkIngredient("Peach Schnapps", 1, "oz", false, true),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        drink.add(new Drink("Fun On The Beach", R.drawable.food4, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Vodka", 1.5, "oz", false, true),
                new DrinkIngredient("Peach Schnapps", 0.5, "oz", false, true),
                new DrinkIngredient("Chamboard", 1/2, "oz", false, true),
                new DrinkIngredient("Orange juice", 1.5, "oz", true, false),
                new DrinkIngredient("Cranberry juice", 1.5, "oz", true, false),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        drink.add(new Drink("Non Alcoholic Moscow Mule", R.drawable.food, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Lime juice", 1, "TBSP", false, false),
                new DrinkIngredient("Ginger beer", 4, "oz", false, false),
                new DrinkIngredient("Club soda", 0, "fill", false, false),
                new DrinkIngredient("Mint", 8, "leaves", false, false),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        drink.add(new Drink("Blue Hawaiian", R.drawable.food2, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Pineapple juice", 2, "oz", true, false),
                new DrinkIngredient("Light rum", 1, "oz", false, true),
                new DrinkIngredient("Blue Curacao", 1, "oz", false, true),
                new DrinkIngredient("Coconut Malibu", 1, "oz", false, true),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        drink.add(new Drink("French Martini", R.drawable.food3, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Pineapple juice", 3, "oz", true, false),
                new DrinkIngredient("Chamboard", 1, "oz", false, true),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

        drink.add(new Drink("Non Alcoholic Mojito", R.drawable.food4, new String[] {"GET", "GOOD"},
            new DrinkIngredient[] {
                new DrinkIngredient("Sugar", 2, "TBSP", false, false),
                new DrinkIngredient("Mint", 8, "leaves", false, false),
                new DrinkIngredient("Lime", 1/2, "lime", false, false),
                new DrinkIngredient("Club soda", 0, "fill", false, false),
            }, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1
        ));

//        System.out.println("added drinks...");
//
        food = new ArrayList<Food>();
        food.add(new Food("apple", R.drawable.food, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("pear", R.drawable.food2, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("cracker", R.drawable.food3, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("grain of rice", R.drawable.food4, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("walnut", R.drawable.food, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("molasse", R.drawable.food2, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("cereal", R.drawable.food3, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("nutella", R.drawable.food4, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
        food.add(new Food("steak", R.drawable.food, 20, ListItem.FragmentType.recipe, ListItem.Unit.g, 50));
//
//        System.out.println("Added food");
//
        //adding meals
        meal = new ArrayList<Meal>();
        meal.add(new Meal("soup", R.drawable.food4, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("broth", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("onion", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("brocoli", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1 ));

        meal.add(new Meal("salad", R.drawable.food, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("tomato", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("onion", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));

        meal.add(new Meal("yogurt parfait", R.drawable.food2, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("yogurt", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("oats", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("Stawberry", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));

        meal.add(new Meal("smoothie", R.drawable.food3, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("milk", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("oats", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("banana", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));

        meal.add(new Meal("rice pilaf", R.drawable.food, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("cucumber", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("rice", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("bread", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));

        meal.add(new Meal("sushi", R.drawable.food4, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("rice", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("cream cheese", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("nori", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));

        meal.add(new Meal("wrap", R.drawable.food2, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("steak", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("pesto", R.drawable.food2, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food3, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));

        meal.add(new Meal("shrimp tacos", R.drawable.food, 100, new MealIngredient[] {
            new MealIngredient(5, "cups", new Food("shrimp", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("taco shell", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("cheese", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10)),
            new MealIngredient(5, "cups", new Food("lettuce", R.drawable.food, 10, ListItem.FragmentType.noType, ListItem.Unit.g, 10))
        }, new String[] {"Get", "Good"}, ListItem.FragmentType.recipe, ListItem.Unit.serving, 1));
//        System.out.println("Added meals");


        //caches database recipes into memory

        //System.out.println("Cached recipes");

        //System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public int getCalorieGoal(){
        return calorieGoal;
    }

    public void setCalorieGoal(int goal){
        if(goal >= 0 && goal <= 9999)
        {
            calorieGoal = goal;
        }
    }

    public int getExerciseGoal(){
        return exerciseGoal;
    }

    public void setExerciseGoal(int goal){
        if(goal >= 0 && goal <= 9999)
        {
            exerciseGoal = goal;
        }
    }

    public ArrayList<ListItem> getFoodLog(Calendar date){
        int count = 0;
        int index = 0;
        ArrayList<ListItem> temp = new ArrayList<ListItem>();

        if(dailyFoodLog != null){
            temp.addAll(dailyFoodLog);
            Collections.shuffle(temp.subList(0,14));
            count = ThreadLocalRandom.current().nextInt(6, 14);
            for (int i = 0; i < count; i++)
            {
                index = ThreadLocalRandom.current().nextInt(0, temp.size()-1);
                temp.remove(index);
            }
        }
        return temp;
    }

    public LinkedList<ListItem> getRecipe(int edibleType) {   //String currTab
        System.out.println("this is good");
        LinkedList<ListItem> currEdibles = new LinkedList<ListItem>();

        if(edibleType == 0) {   //food tab send cachedFood
            for(int i = 0; i < food.size(); i++) {//food.get(i) - RecipeBookItem needs Edible item, int image, int key
                currEdibles.add(new RecipeBookItem(food.get(i), R.drawable.food, i)); //i is not a unique key
            }
        }
        else if(edibleType == 1) {  //meal tab send cachedMeal
            for(int i = 0; i < drink.size(); i++) {
                currEdibles.add(new RecipeBookItem(drink.get(i), R.drawable.food2, i));
            }
        }
        else if(edibleType == 2) {  //drink tab send cachedDrink
            for(int i = 0; i < meal.size(); i++) {
                currEdibles.add(new RecipeBookItem(meal.get(i), R.drawable.food3, i));
            }
        }

        System.out.println("exiting getRecipe...");

        return currEdibles;
    }

    public void addRecipeToLog(DiaryItem item) { //Needs date added to this later, only adds to current
        System.out.println("Before: " + dailyFoodLog.size());
        dailyFoodLog.add(item);
        System.out.println("After: " + dailyFoodLog.size());
    }

}
