package comp3350.team10.objects;

import java.util.ArrayList;

public class Meal extends PreparedItem {
    private ArrayList<Ingredient> ingredients;  //The ingredients in the meal

    public Meal() {
        super();

        this.ingredients = new ArrayList<Ingredient>();
    }


    public void setIngredients(ArrayList<Ingredient> newIngredients) throws IllegalArgumentException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IllegalArgumentException("Invalid meal ingredients");
        }
    }

    public void readIngredientData() throws IllegalArgumentException {
        this.updateEdibleFromIngredients(this.ingredients);
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

        public Meal clone() throws IllegalArgumentException {
        Meal copy = new Meal();

        copy.initDetails(this.getDbkey(), this.getName(), this.getDescription(), this.getQuantity(), this.getUnit());
        copy.initNutrition(this.getCalories(), this.getProtein(), this.getCarbs(), this.getFat());
        copy.initCategories(this.getIsAlcoholic(), this.getIsSpicy(), this.getIsVegan(), this.getIsVegetarian(), this.getIsGlutenFree());
        copy.initMetadata(this.getIsCustom(), this.getPhoto());
        copy.setIngredients(this.getIngredients());
        
        return copy;
    }
}