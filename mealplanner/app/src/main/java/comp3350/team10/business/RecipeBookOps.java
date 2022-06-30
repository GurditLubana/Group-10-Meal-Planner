package comp3350.team10.business;

import android.widget.ImageView;

import comp3350.team10.objects.*;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.persistence.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeBookOps { //this needs to select the corect fragment
    private static enum RecipeBook {FOOD, DRINKS, MEALS} ; //Possible Edible type views

    private ArrayList<Edible> selectedList;    //The recipes available for the current view
    private RecipeBook selectedType;            //The selected Edible type view (see enum on line 18)
    private DBSelector db;                  //Accesses the database

    public RecipeBookOps(DBSelector db) {
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
    //might want to just pass the object in here later?
    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat,
            boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, byte[] photo) {
        Food newFood = new Food();

        try {
            newFood.initDetails(db.getNextKey(), name, desc, qty, unit);
            newFood.initNutrition(calories, protein, carbs, fat);
            newFood.initCategories(alcoholic, spicy, vegan, vegetarian, glutenFree);
            newFood.setCustom(true);
            newFood.setPhotoBytes(photo);
            db.addFoodToRecipeBook(newFood);
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, byte[] photo, String instructions,
            ArrayList<Ingredient> ingredients) {
        Meal newMeal = new Meal();

        try {
            newMeal.initDetails(db.getNextKey(), name, desc, qty, unit);
            newMeal.setInstructions(instructions);
            newMeal.setIngredients(ingredients);
            newMeal.setCustom(true);
            newMeal.setPhotoBytes(photo);

            db.addMealToRecipeBook(newMeal);
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void addSimpleDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat,
            boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, byte[] photo) {
        Drink newDrink = new Drink();

        try {
            newDrink.initDetails(db.getNextKey(), name, desc, qty, unit);
            newDrink.initNutrition(calories, protein, carbs, fat);
            newDrink.initCategories(alcoholic, spicy, vegan, vegetarian, glutenFree);
            newDrink.setCustom(true);
            newDrink.setPhotoBytes(photo);
            db.addDrinkToRecipeBook(newDrink);
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void addPreparedDrink(String name, String desc, int qty, Edible.Unit unit, byte[] photo, String instructions,
            ArrayList<DrinkIngredient> ingredients) {
        Drink newDrink = new Drink();

        try {
            newDrink.initDetails(db.getNextKey(), name, desc, qty, unit);
            newDrink.setInstructions(instructions);
            newDrink.setIngredients(ingredients);
            newDrink.setCustom(true);
            newDrink.setPhotoBytes(photo);

            db.addDrinkToRecipeBook(newDrink);
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}