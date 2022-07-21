package comp3350.team10.presentation;

import java.util.ArrayList;

import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;

public interface FragToRecipeBook {
    void removeItem(int position);

    void editEntry(Double value, String unit, boolean isSubstitute);

    void addEntry(int position);

    public void loadEditorView();

    void showIngredientContextUI(int position);

    Edible.Unit getEntryUnit();

    String getEntryQty();

    boolean getIsSubstitute();

    public enum EntryMode {ADD_FOOD, ADD_MEAL, ADD_DRINK, INGREDIENT, DRINK_INGREDIENT}

    public void addDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
                         boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo, String instructions, ArrayList<DrinkIngredient> ingredients);

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, String photo, String instructions, ArrayList<Ingredient> ingredients);

    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
                        boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo);

    public String getIntentExtra(String key);

    public void addToMealDiary();
}
