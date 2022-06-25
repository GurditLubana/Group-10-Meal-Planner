package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.Meal;

public interface RecipeDBInterface { //updates recipeDB
    public ArrayList<Edible> getFoodList(Calendar date);
    public ArrayList<Edible> getMealList(Calendar date);
    public ArrayList<Edible> getDrinkList(Calendar date);
    
    public void addFoodToRecipeBook(Food newFood);
    public void addMealToRecipeBook(Meal newMeal);
    public void addDrinkToRecipeBook(Drink newDrink);
}
