package comp3350.team10.presentation;

import java.util.ArrayList;

import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;

public interface FragToRecipeBook {
    public enum EntryMode {ADD_FOOD, ADD_MEAL, ADD_DRINK}

    public void addDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
                         boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo, String instructions, ArrayList<DrinkIngredient> ingredients);

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, String photo, String instructions, ArrayList<Ingredient> ingredients);

    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
                        boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo);

    public String getIntentExtra(String key);

    public void showContextUI(int pos);

    public EntryMode getEntryMode();

    public void addToMealDiary();

    public boolean getDetails();

    public void showDetails();
}
