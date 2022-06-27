package comp3350.team10.business;

import android.widget.ImageView;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

import java.util.ArrayList;
import java.util.ArrayList;

public class RecipeBookOps { //this needs to select the corect fragment
    private static enum RecipeBook {FOOD, DRINKS, MEALS} ; //Possible Edible type views

    private ArrayList<Edible> selectedList;    //The recipes available for the current view
    private RecipeBook selectedType;            //The selected Edible type view (see enum on line 18)
    private DataAccessStub db;                  //Accesses the database

    public RecipeBookOps(DataAccessStub db) { //will need cache added
        this.selectedType = RecipeBook.FOOD;
        this.selectedList = null;
        this.db = db;

        this.pullDBdata();
    }

    private void pullDBdata() {
        if (this.selectedType == RecipeBook.FOOD) {
            this.selectedList = db.getFoodRecipes();
        } else if (this.selectedType == RecipeBook.MEALS) {
            this.selectedList = db.getMealRecipes();
        } else {
            this.selectedList = db.getDrinkRecipes();
        }
    }

    public ArrayList<Edible> getFoodRecipes() {
        this.selectedType = RecipeBook.FOOD;
        this.pullDBdata();

        return this.selectedList;
    }

    public ArrayList<Edible> getDrinkRecipes() {
        this.selectedType = RecipeBook.DRINKS;
        this.pullDBdata();

        return this.selectedList;
    }

    public ArrayList<Edible> getMealRecipes() {
        this.selectedType = RecipeBook.MEALS;
        this.pullDBdata();

        return this.selectedList;
    }

    public void addFood(String name, String desc, int qty, Edible.Unit unit, ImageView photo, ListItem.FragmentType view,
             boolean isVegan, boolean isVegetarian, boolean isGlutenFree, boolean isSpicy, boolean isBreakfastFood,
             boolean isLunchFood, boolean isSupperFood, boolean isAlcoholic) {
        Food newFood = new Food();

        try {
            newFood.init(db.getNextKey(), name, desc, qty, unit, photo, view, isVegan, isVegetarian, isGlutenFree, isSpicy, isBreakfastFood,
                isLunchFood, isSupperFood, isAlcoholic);
            db.addFoodToRecipeBook(newFood);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, ImageView photo, ListItem.FragmentType view,
            ArrayList<String> instructions, ArrayList<Ingredient> ingredients, boolean isBreakfastMeal, boolean isLunchMeal,
            boolean isSupperMeal) {
        Meal newMeal = new Meal();

        try {
            newMeal.init(db.getNextKey(), name, desc, qty, unit, photo, view, instructions, ingredients, isBreakfastMeal,
                    isLunchMeal, isSupperMeal);
            db.addMealToRecipeBook(newMeal);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void addDrink(String name, String desc, int qty, Edible.Unit unit, ImageView photo, ListItem.FragmentType view,
            ArrayList<String> instructions, ArrayList<DrinkIngredient> ingredients) {
        Drink newDrink = new Drink();

        try {
            newDrink.init(db.getNextKey(), name, desc, qty, unit, photo, view, instructions, ingredients);
            db.addDrinkToRecipeBook(newDrink);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}