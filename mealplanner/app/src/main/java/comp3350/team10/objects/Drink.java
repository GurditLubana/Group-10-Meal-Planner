package comp3350.team10.objects;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Drink extends PreparedItem {
    private ArrayList<DrinkIngredient> ingredients;     //A list of ingredients for the given drink
    private boolean alcoholic;                          //Flag representing whether the drink contains alcohol or not
    private boolean spicy;                              //Flat representing whether the drink is spicy or not

    public Drink() {
        super();

        this.ingredients = null;
        this.alcoholic = false;
        this.spicy = false;
    }


    public void init(int id, String name, String desc, int qty, Unit unit, imageView photo, ListItem.FragmentType view, 
            ArrayList<String> instructions, ArrayList<DrinkIngredients> ingredients) throws IOException {
        super.init(id, name, desc, qty, unit, photo, view, instructions);
        this.setIngredients(ingredients);
        this.setAlcoholic(ingredients);
        this.setSpicy(ingredients);
    }

    public void setIngredients(ArrayList<DrinkIngredients> newIngredients) throws IOException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IOException("Invalid drink ingredients");
        }
    }

    private void setAlcoholic(ArrayList<DrinkIngredients> ingredients) {
        boolean isAlcoholic = false;

        for(int i = 0 ; i < ingredients.size() && !isAlcoholic; i++) {
            if(ingredients.get(i).getIngredient().isAlcoholic()) {
                isAlcoholic = true;
            }
        }
    }

    private void setSpicy(ArrayList<DrinkIngredients> ingredients) {
        boolean isSpicy = false;

        for(int i = 0 ; i < ingredients.size() && !isSpicy; i++) {
            if(ingredients.get(i).getIngredient().isSpicy()) {
                isSpicy = true;
            }
        }
    }

    public DrinkIngredient[] getIngredients() {
        return this.ingredients;
    }

    public boolean isAlcoholic() {
        return this.alcoholic;
    }

    public boolean isSpicy() {
        return this.spicy;
    }
}
