package comp3350.team10.presentation;

import comp3350.team10.objects.*;

public interface FragToRecipeBook {
    public void showContextUI(int pos);
    public void addToMealDiary(int pos);
    public void addFood(String name, int iconPath, int calories, Edible.Unit baseUnit, int quantity); //change this to correct signature
    public void addMeal(String drinkName, int ic_eggplant, int calories, MealIngredient[] ingredientsArray, String[] dInstruct, Edible.Unit ml, int quantity);
    public void addDrink(String drinkName, int ic_eggplant, int calories, DrinkIngredient[] ingredientsArray, String[] dInstruct, Edible.Unit ml, int quantity);
}
