package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.Meal;

public interface RecipeDBInterface {
    public ArrayList<Edible> getFoodRecipes();
    public ArrayList<Edible> getMealRecipes();
    public ArrayList<Edible> getDrinkRecipes();
    
    public void addFoodToRecipeBook(Food newFood);
    public void addMealToRecipeBook(Meal newMeal);
    public void addDrinkToRecipeBook(Drink newDrink);
}
