package comp3350.team10.objects;

import java.util.concurrent.ThreadLocalRandom;

public class Drink extends PreparedItem {
    private final static Integer CALORIES_PER_DRINK = 250;  //The default quantity of calories per drink
    private String ingredients;                  //A list of ingredients for the given drink

    public Drink(String name, int iconPath, int cals, String instructions, String ingredients, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, CALORIES_PER_DRINK, instructions, type, baseUnit, quantity, dbkey);
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String newIngredients) {
        this.ingredients = newIngredients;
    }

}
