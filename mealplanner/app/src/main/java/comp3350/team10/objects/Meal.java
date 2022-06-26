package comp3350.team10.objects;

import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class Meal extends PreparedItem {
    private ArrayList<Ingredient> ingredients;  //The ingredients in the meal
    private boolean vegan;                      //Flag that represents whether this meal is vegan or not
    private boolean vegetarian;                 //Flag that represents whether this meal is vegetarian or not
    private boolean glutenFree;                 //Flag that represents whether this meal is glutenFree or not
    private boolean spicy;                      //Flag that represents whether this meal is spicy or not
    private boolean breakfast;                  //Flag that represents whether this meal is breakfast or not
    private boolean lunch;                      //Flag that represents whether this meal is lunch or not
    private boolean supper;                     //Flag that represents whether this meal is supper or not

    public Meal() {
        super();

        this.ingredients = null;
        this.vegan = false;
        this.vegetarian = false;
        this.glutenFree = false;
        this.spicy = false;
        this.breakfast = false;
        this.lunch = false;
        this.supper = false;
    }


    public void init(int id, String name, String desc, int qty, Unit unit, ImageView photo, ListItem.FragmentType view,
                ArrayList<String> instructions, ArrayList<Ingredient> ingredients, boolean isBreakfastMeal,
                boolean isLunchMeal, boolean isSupperMeal) throws IOException {
        super.init(id, name, desc, qty, unit, photo, view, instructions);
        this.setIngredients(ingredients);
        this.setVegan(ingredients);
        this.setVegetarian(ingredients);
        this.setGlutenFree(ingredients);
        this.setSpicy(ingredients);
        this.setBreakfast(isBreakfastMeal);
        this.setLunch(isLunchMeal);
        this.setSupper(isSupperMeal);
    }

    public void setIngredients(ArrayList<Ingredient> newIngredients) throws IOException {
        if (newIngredients != null && !newIngredients.contains(null)) {
            this.ingredients = newIngredients;
        }
        else {
            throw new IOException("Invalid meal ingredients");
        }
    }

    private void setVegan(ArrayList<Ingredient> ingredients) {
        boolean isVegan = true;

        for(int i = 0 ; i < ingredients.size() && isVegan; i++) {
            if(!ingredients.get(i).getIngredient().isVegan()) {
                isVegan = false;
            }
        }
    }

    private void setVegetarian(ArrayList<Ingredient> ingredients) {
        boolean isVegetarian = true;

        for(int i = 0 ; i < ingredients.size() && isVegetarian; i++) {
            if(!ingredients.get(i).getIngredient().isVegetarian()) {
                isVegetarian = false;
            }
        }
    }

    private void setGlutenFree(ArrayList<Ingredient> ingredients) {
        boolean isGlutenFree = true;

        for(int i = 0 ; i < ingredients.size() && isGlutenFree; i++) {
            if(!ingredients.get(i).getIngredient().isGlutenFree()) {
                isGlutenFree = false;
            }
        }
    }

    private void setSpicy(ArrayList<Ingredient> ingredients) {
        boolean isSpicy = false;

        for(int i = 0 ; i < ingredients.size() && !isSpicy; i++) {
            if(ingredients.get(i).getIngredient().isSpicy()) {
                isSpicy = true;
            }
        }
    }

    private void setBreakfast(boolean isBreakfastMeal) {
        this.breakfast = isBreakfastMeal;
    }

    private void setLunch(boolean isLunchMeal) {
        this.lunch = isLunchMeal;
    }

    private void setSupper(boolean isSupperMeal) {
        this.supper = isSupperMeal;
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }
}