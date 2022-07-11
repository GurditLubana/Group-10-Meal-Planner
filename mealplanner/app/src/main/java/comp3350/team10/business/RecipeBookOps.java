package comp3350.team10.business;


import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;
import comp3350.team10.persistence.RecipeDBInterface;
import comp3350.team10.persistence.SharedDB;

import java.util.ArrayList;

public class RecipeBookOps {
    private RecipeDBInterface db;      //Access to the database

    public RecipeBookOps() {
        this.db = SharedDB.getRecipeDB();
    }


    public ArrayList<Edible> getFoodRecipes() {
        return this.db.getFoodRecipes();
    }

    public ArrayList<Edible> getDrinkRecipes() {
        return db.getDrinkRecipes();
    }

    public ArrayList<Edible> getMealRecipes() {
        return db.getMealRecipes();
    }

    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat,
                        boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo) throws IllegalArgumentException {
        Edible newFood = new Edible();

        newFood.initDetails(db.getNextKey(), name, desc, qty, unit);
        newFood.initNutrition(calories, protein, carbs, fat);
        newFood.initCategories(alcoholic, spicy, vegan, vegetarian, glutenFree);
        newFood.setCustom(true);
        newFood.setPhoto(photo);
        db.addFoodToRecipeBook(newFood);
    }

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, String photo, String instructions,
                        ArrayList<Ingredient> ingredients) throws IllegalArgumentException {
        Meal newMeal = new Meal();

        if (ingredients == null || ingredients.size() <= 0) {
            throw new IllegalArgumentException("Meals need ingredients!  This is not a valid meal");
        }

        newMeal.initDetails(db.getNextKey(), name, desc, qty, unit);
        newMeal.setInstructions(instructions);
        newMeal.setIngredients(ingredients);
        newMeal.setCustom(true);
        newMeal.setPhoto(photo);
        newMeal.readIngredientData();

        db.addMealToRecipeBook(newMeal);
    }

    public void addSimpleDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat,
                               boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo) throws IllegalArgumentException {
        Drink newDrink = new Drink();

        newDrink.initDetails(db.getNextKey(), name, desc, qty, unit);
        newDrink.initNutrition(calories, protein, carbs, fat);
        newDrink.initCategories(alcoholic, spicy, vegan, vegetarian, glutenFree);
        newDrink.setCustom(true);
        newDrink.setPhoto(photo);
        db.addDrinkToRecipeBook(newDrink);
    }

    public void addPreparedDrink(String name, String desc, int qty, Edible.Unit unit, String photo, String instructions,
                                 ArrayList<DrinkIngredient> ingredients) throws IllegalArgumentException {
        Drink newDrink = new Drink();

        if (ingredients == null || ingredients.size() <= 0) {
            throw new IllegalArgumentException("Prepared Drinks need ingredients!  This is not a valid drink");
        }

        newDrink.initDetails(db.getNextKey(), name, desc, qty, unit);
        newDrink.setInstructions(instructions);
        newDrink.setIngredients(ingredients);
        newDrink.setCustom(true);
        newDrink.setPhoto(photo);
        newDrink.readIngredientData();

        db.addDrinkToRecipeBook(newDrink);
    }
}