package comp3350.team10.business;

import android.widget.ImageView;


import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.SharedDB;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeBookOps {
    private DBSelector db;      //Access to the database

    public RecipeBookOps() {
        this.db = SharedDB.getSharedDB();
    }


    public ArrayList<Edible> getFoodRecipes() {
        return db.getFoodRecipes();
    }

    public ArrayList<Edible> getDrinkRecipes() {
        return db.getDrinkRecipes();
    }

    public ArrayList<Edible> getMealRecipes() {
       return db.getMealRecipes();
    }

    //might want to just pass the object in here later?
    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat,
            boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, byte[] photo) {
        Edible newFood = new Edible();

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