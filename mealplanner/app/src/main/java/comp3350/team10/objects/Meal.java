package comp3350.team10.objects;

import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class Meal extends PreparedItem {
    private ArrayList<Ingredient> ingredients;  //The ingredients in the meal

    public Meal() {
        super();

        this.ingredients = new ArrayList<Ingredient>();
    }


    public void setIngredients(ArrayList<Ingredient> newIngredients) throws IOException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IOException("Invalid meal ingredients");
        }
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }
}