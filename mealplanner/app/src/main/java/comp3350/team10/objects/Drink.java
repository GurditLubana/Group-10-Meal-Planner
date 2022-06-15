package comp3350.team10.objects;

import java.util.concurrent.ThreadLocalRandom;

public class Drink extends PreparedItem {
    private String ingredients;                           //A list of ingredients for the given drink

    public Drink() {
        super();

        this.ingredients = null;
    }


    public boolean init(String name, int iconPath, int cals, String instructions, String ingredients, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        boolean results = super.init(name, iconPath, cals, instructions, type, baseUnit, quantity, dbkey);
        
        if(!results || !setIngredients(ingredients)) {
            results = false;
        }

        return results;
    }


    public String getIngredients() {
        return this.ingredients;
    }

    public boolean setIngredients(String newIngredients) {
        boolean results = false;

        if(newIngredients != null) {
            this.ingredients = newIngredients;
            results = true;
        }
        
        return results;
    }

}
