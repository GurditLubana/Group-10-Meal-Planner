package comp3350.team10.objects;

import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
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


    public void init(int id, String name, String desc, int qty, Unit unit, ImageView photo, ListItem.FragmentType view,
                     ArrayList<String> instructions, ArrayList<DrinkIngredient> ingredients) throws IOException {
        super.init(id, name, desc, qty, unit, photo, view, instructions);
        this.setIngredients(ingredients);
        this.setAlcoholic(ingredients);
        this.setSpicy(ingredients);
    }

    public void setIngredients(ArrayList<DrinkIngredient> newIngredients) throws IOException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IOException("Invalid drink ingredients");
        }
    }

    private void setAlcoholic(ArrayList<DrinkIngredient> ingredients) { //has two .. this probably should be refactored later
        boolean isAlcoholic = false;

        for(int i = 0 ; i < ingredients.size() && !isAlcoholic; i++) {
            if(ingredients.get(i).getIngredient().isAlcoholic()) {
                isAlcoholic = true;
            }
        }
    }

    private void setSpicy(ArrayList<DrinkIngredient> ingredients) {
        boolean isSpicy = false;

        for(int i = 0 ; i < ingredients.size() && !isSpicy; i++) {
            if(ingredients.get(i).getIngredient().isSpicy()) {
                isSpicy = true;
            }
        }
    }

    public ArrayList<DrinkIngredient> getIngredients() {
        return this.ingredients;
    }

    public boolean isAlcoholic() {
        return this.alcoholic;
    }

    public boolean isSpicy() {
        return this.spicy;
    }
}
