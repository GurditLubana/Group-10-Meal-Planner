package comp3350.team10.objects;

import java.util.ArrayList;

public class Drink extends PreparedItem {
    private ArrayList<DrinkIngredient> ingredients;     //A list of ingredients for the given drink

    public Drink() {
        super();

        this.ingredients = new ArrayList<DrinkIngredient>();
    }


    public void setIngredients(ArrayList<DrinkIngredient> newIngredients) throws IllegalArgumentException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IllegalArgumentException("Invalid drink ingredients");
        }
    }

    public void readIngredientData() throws IllegalArgumentException {
        ArrayList<Ingredient> temp = new ArrayList<Ingredient>();

        //Downcast to ingredients because ArrayLists are finiky!
        for(int i = 0; i < this.ingredients.size(); i++) {
            temp.add(this.ingredients.get(i));
        }
        
        this.updateEdibleFromIngredients(temp);
    }

    public ArrayList<DrinkIngredient> getIngredients() {
        return this.ingredients;
    }
}