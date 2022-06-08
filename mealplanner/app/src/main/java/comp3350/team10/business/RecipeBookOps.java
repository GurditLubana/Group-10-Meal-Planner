package comp3350.team10.business;

import java.util.ArrayList;
import java.util.LinkedList;

import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.ListItem;
import comp3350.team10.objects.Meal;
import comp3350.team10.objects.MealIngredient;
import comp3350.team10.objects.RecipeBookItem;
import comp3350.team10.persistence.DataAccessStub;

public class RecipeBookOps {
    public enum RecipeBook {FOOD,DRINKS,MEALS};

    private LinkedList<ListItem> selectedList = null;
    private RecipeBook selectedType = RecipeBook.FOOD;
    private DataAccessStub db = null;

    public RecipeBookOps(DataAccessStub db){
        this.db = db;
        pullDBdata();
    }

    private void pullDBdata(){
        if(selectedType == RecipeBook.FOOD){
            selectedList = db.getRecipes(RecipeBook.FOOD.name());
        }
        else if(selectedType == RecipeBook.MEALS){
            selectedList = db.getRecipes(RecipeBook.MEALS.name());
        }
        else{
            selectedList = db.getRecipes(RecipeBook.DRINKS.name());
        }
    }

    public LinkedList<ListItem> getFoodRecipes(){
        selectedType = RecipeBook.FOOD;
        pullDBdata();
        return selectedList;
    }

    public LinkedList<ListItem> getDrinkRecipes(){
        selectedType = RecipeBook.DRINKS;
        pullDBdata();
        return selectedList;
    }

    public LinkedList<ListItem> getMealRecipes(){
        selectedType = RecipeBook.MEALS;
        pullDBdata();
        return selectedList;
    }

    public void addFood(String name, int iconPath, int calories, ListItem.Unit baseUnit, int quantity){ //
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
