package comp3350.team10.objects;

import java.util.concurrent.ThreadLocalRandom;

public class Drink extends PreparedItem {
    private final static Integer CALORIES_PER_DRINK = 250;  //The default quantity of calories per drink
    private DrinkIngredient[] ingredients;                  //A list of ingredients for the given drink

    public Drink(String name, int iconPath, String[] instructions, DrinkIngredient[] ingredients, ListItem.FragmentType type, ListItem.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, CALORIES_PER_DRINK, instructions, type, baseUnit, quantity, dbkey);
        this.ingredients = ingredients;
    }

    public Drink(String name, int iconPath, int cals, String[] instructions, DrinkIngredient[] ingredients, ListItem.FragmentType type, ListItem.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, cals, instructions, type, baseUnit, quantity, dbkey);
        this.ingredients = ingredients;
    }

    public DrinkIngredient[] getIngredients() {
        return this.ingredients;
    }

    public void changeIngredients(DrinkIngredient[] newIngredients) {
        this.ingredients = newIngredients;
    }

    public void flexIngredients(float prefAlcoholQty) {
        //calculate alcohol lvls removed from each drink
        //distribute this to all replacements
    }

    public void getReplacements() {

    }
}
