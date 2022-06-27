package comp3350.team10.objects;

import java.io.IOException;
import java.util.ArrayList;

public class Drink extends PreparedItem {
    private ArrayList<DrinkIngredient> ingredients;     //A list of ingredients for the given drink

    public Drink() {
        super();

        this.ingredients = new ArrayList<DrinkIngredient>();
    }


    public void setIngredients(ArrayList<DrinkIngredient> newIngredients) throws IOException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IOException("Invalid drink ingredients");
        }
    }

    public ArrayList<DrinkIngredient> getIngredients() {
        return this.ingredients;
    }
}