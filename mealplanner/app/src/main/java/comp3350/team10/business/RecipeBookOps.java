package comp3350.team10.business;

import comp3350.team10.objects.Drink;

import comp3350.team10.objects.Food;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;
import comp3350.team10.objects.MealIngredient;
import comp3350.team10.objects.RecipeBookItem;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.persistence.DataAccessStub;

import java.util.ArrayList;
import java.util.LinkedList;

public class RecipeBookOps {
    private final static enum RecipeBook {FOOD, DRINKS, MEALS}; //Possible Edible type views

    private LinkedList<Edible> selectedList;    //The recipes availible for the current view
    private RecipeBook selectedType;            //The selected Edible type view (see enum on line 18)
    private DataAccessStub db;                  //Accesses the database

    public RecipeBookOps(DataAccessStub db) {
        this.selectedType = RecipeBook.FOOD;
        this.selectedList = null;
        this.db = db;
        
        this.pullDBdata();
    }


    private void pullDBdata() {
        if(this.selectedType == RecipeBook.FOOD) {
            this.selectedList = db.getRecipes(RecipeBook.FOOD.name());
        }
        else if(this.selectedType == RecipeBook.MEALS) {
            this.selectedList = db.getRecipes(RecipeBook.MEALS.name());
        }
        else {
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

    public void addFood(String name, int iconPath, int calories, ListItem.Unit baseUnit, int quantity) {
        Edible newFood = new Food(name, iconPath, calories, ListItem.FragmentType.diaryEntry, baseUnit, quantity, db.getNextKey());
        
        db.addFoodToRecipeBook(newFood);
    }

    public void addMeal(String name, int iconPath, int calories, MealIngredient[] ingredients, String[] instructions, ListItem.Unit baseUnit, int quantity){
        Edible newMeal = new Meal(name, iconPath, calories, ingredients, instructions, ListItem.FragmentType.diaryEntry, baseUnit, quantity, db.getNextKey());
        
        db.addMealToRecipeBook(newMeal);
    }

    public void addDrink(String name, int iconPath, int cals, String[] instructions, DrinkIngredient[] ingredients, ListItem.Unit baseUnit, int quantity){
        Edible newDrink = new Drink(name, iconPath, cals, instructions, ingredients, ListItem.FragmentType.diaryEntry, baseUnit, quantity, db.getNextKey());
        
        db.addDrinkToRecipeBook(newDrink);
    }
}