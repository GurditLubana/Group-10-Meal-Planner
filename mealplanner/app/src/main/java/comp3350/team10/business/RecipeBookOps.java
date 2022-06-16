package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class RecipeBookOps {
    private static enum RecipeBook {FOOD, DRINKS, MEALS}

    ; //Possible Edible type views

    private LinkedList<Edible> selectedList;    //The recipes available for the current view
    private RecipeBook selectedType;            //The selected Edible type view (see enum on line 18)
    private DataAccessStub db;                  //Accesses the database

    public RecipeBookOps(DataAccessStub db) {
        this.selectedType = RecipeBook.FOOD;
        this.selectedList = null;
        this.db = db;

        this.pullDBdata();
    }

    private void pullDBdata() {
        if (this.selectedType == RecipeBook.FOOD) {
            this.selectedList = db.getRecipes(RecipeBook.FOOD.name());
        } else if (this.selectedType == RecipeBook.MEALS) {
            this.selectedList = db.getRecipes(RecipeBook.MEALS.name());
        } else {
            this.selectedList = db.getRecipes(RecipeBook.DRINKS.name());
        }
    }

    public LinkedList<Edible> getFoodRecipes() {
        this.selectedType = RecipeBook.FOOD;
        this.pullDBdata();

        return this.selectedList;
    }

    public LinkedList<Edible> getDrinkRecipes() {
        this.selectedType = RecipeBook.DRINKS;
        this.pullDBdata();

        return this.selectedList;
    }

    public LinkedList<Edible> getMealRecipes() {
        this.selectedType = RecipeBook.MEALS;
        this.pullDBdata();

        return this.selectedList;
    }

    public void addFood(String name, int iconPath, int calories, Edible.Unit baseUnit, int quantity) {
        Food newFood = new Food();

        if (newFood.init(name, iconPath, calories, ListItem.FragmentType.diaryEntry, baseUnit, quantity, db.getNextKey())) {
            db.addFoodToRecipeBook(newFood);
        }
    }

    public void addMeal(String name, int iconPath, int calories, String ingredients, String instructions, Edible.Unit baseUnit, int quantity) {
        Meal newMeal = new Meal();

        if (newMeal.init(name, iconPath, calories, ingredients, instructions, ListItem.FragmentType.diaryEntry, baseUnit, quantity, db.getNextKey())) {
            db.addMealToRecipeBook(newMeal);
        }
    }

    public void addDrink(String name, int iconPath, int cals, String instructions, String ingredients, Edible.Unit baseUnit, int quantity) {
        Drink newDrink = new Drink();

        if (newDrink.init(name, iconPath, cals, instructions, ingredients, ListItem.FragmentType.diaryEntry, baseUnit, quantity, db.getNextKey())) {
            db.addDrinkToRecipeBook(newDrink);
        }
    }
}