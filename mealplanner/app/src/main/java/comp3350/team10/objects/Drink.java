package comp3350.team10.objects;

import java.util.concurrent.ThreadLocalRandom;

public class Drink extends PreparedItem {
    private DrinkIngredient[] ingredients;  //A list of ingredients for the given drink
    private boolean alcoholic;              //Flag representing whether the drink contains alcohol or not
    private boolean spicy;                  //Flat representing whether the drink is spicy or not

    public Drink() {
        super();

        this.ingredients = null;
        this.alcoholic = false;
        this.spicy = false;
    }


    public boolean init(String name, int iconPath, int cals, String instructions, String ingredients, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        return super.init(name, iconPath, cals, instructions, type, baseUnit, quantity, dbkey) && setIngredients(ingredients);
    }


    public String getIngredients() {
        return this.ingredients;
    }

    public boolean setIngredients(String newIngredients) {
        boolean results = false;

        if (newIngredients != null) {
            this.ingredients = newIngredients;
            results = true;
        }

        return results;
    }

}
