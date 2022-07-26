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

    public int addFoodToRecipeBook(Edible newFood) throws IllegalArgumentException;

    public int addMealToRecipeBook(Meal newMeal) throws IllegalArgumentException;

    public int addDrinkToRecipeBook(Drink newDrink) throws IllegalArgumentException;

    public Edible findIngredientByKey(int key, boolean isCustom);
}
