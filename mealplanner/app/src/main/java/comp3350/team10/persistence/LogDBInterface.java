package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

public interface LogDBInterface {
    public final static int GOAL_LIMIT = 9999;         //Goal limit (standalone variable as per MealDiaryLog)
    
    public DailyLog searchFoodLogByDate(Calendar date, int userID);
    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom);
    public void addLog(DailyLog newLog, int userID);
    public void deleteLog(DailyLog delLog, int userID);
    public void setExerciseActual(double newExercise, DailyLog currLog, int userID);
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