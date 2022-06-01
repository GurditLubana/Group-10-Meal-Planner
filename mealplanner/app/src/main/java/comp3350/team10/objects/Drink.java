package comp3350.team10.objects;

public class Drink extends PreparedItem {
    private static int CALORIES_PER_DRINK = 250;
    private DrinkIngredient[] ingredients;

    public Drink(String name, String path, String[] instructions, DrinkIngredient[] ingredients) {
        super(name, path, CALORIES_PER_DRINK, instructions);
        this.ingredients = ingredients;
    }


    public int getIngredients() {
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
