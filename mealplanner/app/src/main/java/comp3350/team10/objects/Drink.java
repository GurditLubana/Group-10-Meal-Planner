package comp3350.team10.objects;

import java.util.concurrent.ThreadLocalRandom;

public class Drink extends PreparedItem {
    private static int CALORIES_PER_DRINK = 250;
    private DrinkIngredient[] ingredients;

    public Drink(String name, int iconPath, String[] instructions, DrinkIngredient[] ingredients, FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, CALORIES_PER_DRINK, instructions, type, baseUnit, quantity, dbkey);
        this.ingredients = ingredients;
        //System.out.println("after drink constructor");
    }

    public Drink(String name, int iconPath, int cals, String[] instructions, DrinkIngredient[] ingredients, FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, cals, instructions, type, baseUnit, quantity, dbkey);
        this.ingredients = ingredients;
        //System.out.println("after drink constructor");
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
