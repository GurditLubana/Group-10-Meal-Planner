package comp3350.team10.business;

import java.util.ArrayList;
import java.util.LinkedList;

import comp3350.team10.objects.ListItem;
import comp3350.team10.objects.RecipeBookItem;
import comp3350.team10.persistence.DataAccessStub;

public class RecipeBookOps {
    private static final int FOOD_TAB = 0;
    private static final int MEAL_TAB = 1;
    private static final int DRINK_TAB = 2;

    private LinkedList<ListItem> foods = new LinkedList<ListItem>();
    private LinkedList<ListItem> meals = new LinkedList<ListItem>();
    private LinkedList<ListItem> drinks = new LinkedList<ListItem>();
    private DataAccessStub db = new DataAccessStub();


    public LinkedList<ListItem> getData(int specDataSet) {
        System.out.println("inside getData");
        LinkedList<ListItem> data = null;
        db.open("someDB");
        
        if(specDataSet == FOOD_TAB) {
            if(this.foods.size() == 0) {
                this.foods = db.getRecipe(specDataSet);
            }

            data = this.foods;
        }
        else if(specDataSet == MEAL_TAB) {
            if(this.meals.size() == 0) {
                this.meals = db.getRecipe(specDataSet);
            }

            data = this.meals;
        }
        else if(specDataSet == DRINK_TAB) {
            if(this.drinks.size() == 0) {
                this.drinks = db.getRecipe(specDataSet);
            }

            data = this.drinks;
        }

        System.out.println("returning data");
        return data;
    }
}
