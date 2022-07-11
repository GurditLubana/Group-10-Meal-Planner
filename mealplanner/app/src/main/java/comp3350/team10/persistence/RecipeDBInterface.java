package comp3350.team10.persistence;

import java.util.ArrayList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.objects.Meal;

public interface RecipeDBInterface {
    public ArrayList<Edible> getFoodRecipes();

    public ArrayList<Edible> getMealRecipes();

    public ArrayList<Edible> getDrinkRecipes();

    public int getNextKey();

    public void addFoodToRecipeBook(Edible newFood) throws IllegalArgumentException;

    public void addMealToRecipeBook(Meal newMeal) throws IllegalArgumentException;

    public void addDrinkToRecipeBook(Drink newDrink) throws IllegalArgumentException;

    public EdibleLog findEdibleByKey(int key, boolean isCustom);
}
