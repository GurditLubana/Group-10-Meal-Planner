package comp3350.team10.presentation;

import comp3350.team10.objects.*;

public interface FragToRecipeBook {
    public void showContextUI(int pos);
    public void addToMealDiary(int pos);
    public void addFood(String name, int iconPath, int calories, Edible.Unit baseUnit, int quantity);
    public void addMeal();
    public void addDrink();
}
