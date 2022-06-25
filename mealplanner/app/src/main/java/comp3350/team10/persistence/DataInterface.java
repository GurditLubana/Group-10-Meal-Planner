package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import comp3350.team10.objects.Edible; //might be a good idea to create a helper class that implements intermediate details

public interface DataInterface {
    public final static int GOAL_LIMIT = 9999;         //Goal limit (standalone variable as per MealDiaryLog)
    public static enum DATA_TYPES {FOOD, MEAL, DRINK}  //The different types of food types

    //user operations
    public int getCalorieGoal();
    public void setCalorieGoal(int goal);
    public int getExerciseGoal();
    public void setExerciseGoal(int goal);
    public void setExerciseActual(int exerciseActual);
    public int getExerciseActual();

    //Diary log operations
    public ArrayList<Edible> getFoodList(Calendar date);
    //pushUpdatedLogToDB() or addToFoodLog and deleteFoodLog

    //Recipe book operations
    public LinkedList<Edible> getRecipes(String edibleType);
    public void addFoodToRecipeBook(Edible newFood);
    public void addMealToRecipeBook(Edible newMeal);
    public void addDrinkToRecipeBook(Edible newDrink);
    public Edible findEdibleByKey(int key); //once we load do we still need this?
}
