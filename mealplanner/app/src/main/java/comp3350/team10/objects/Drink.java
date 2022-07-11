package comp3350.team10.objects;

import java.util.ArrayList;

public class Drink extends PreparedEdible {
    private ArrayList<DrinkIngredient> ingredients;     //A list of ingredients for the given drink

    public Drink() {
        super();

        this.ingredients = new ArrayList<DrinkIngredient>();
    }


    public void setIngredients(ArrayList<DrinkIngredient> newIngredients) throws IllegalArgumentException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        } else {
            throw new IllegalArgumentException("Invalid drink ingredients");
        }
    }

    public void readIngredientData() throws IllegalArgumentException {
        ArrayList<Ingredient> temp = new ArrayList<Ingredient>();

        //Downcast to ingredients because ArrayLists are finiky!
        for (int i = 0; i < this.ingredients.size(); i++) {
            temp.add(this.ingredients.get(i));
        }

        this.updateEdibleFromIngredients(temp);
    }

    public ArrayList<DrinkIngredient> getIngredients() {
        return this.ingredients;
    }

    public Drink clone() throws IllegalArgumentException {
        Drink copy = new Drink();

        copy.initDetails(this.getDbkey(), this.getName(), this.getDescription(), this.getQuantity(), this.getUnit());
        copy.initNutrition(this.getCalories(), this.getProtein(), this.getCarbs(), this.getFat());
        copy.initCategories(this.getIsAlcoholic(), this.getIsSpicy(), this.getIsVegan(), this.getIsVegetarian(), this.getIsGlutenFree());
        copy.initMetadata(this.getIsCustom(), this.getPhoto());
        copy.setInstructions(this.getInstructions());
        copy.setIngredients(this.getIngredients());

        return copy;
    }
}