package comp3350.team10.presentation;

import comp3350.team10.objects.*;

public interface FragToRecipeBook {
    public enum EntryMode {ADD_FOOD, ADD_MEAL, ADD_DRINK}

    public void addDrink(String drinkName, int ic_eggplant, int calories, String ingredients, String dInstruct, Edible.Unit ml, int quantity);

    public void addMeal(String drinkName, int ic_eggplant, int calories, String ingredients, String dInstruct, Edible.Unit ml, int quantity);

    public void addFood(String name, int iconPath, int calories, Edible.Unit baseUnit, int quantity);

    public String getIntentExtra(String key);

    public void showContextUI(int pos);

    public EntryMode getEntryMode();

    public void addToMealDiary();

    public boolean getDetails();

    public void showDetails();
}
