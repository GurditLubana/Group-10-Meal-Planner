package comp3350.team10.objects;

import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class PreparedItem extends Edible {
    private String instructions;     //The instructions required for the Edible to prepare

    public PreparedItem() {
        super();

        this.instructions = "";
    }


    public void setInstructions(String newInstructions) throws IOException {
        if (newInstructions != null) {
            this.instructions = newInstructions;
        }
        else {
            throw new IOException("Invalid instructions");
        }
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void calcCalories(ArrayList<Ingredient> ingredients) throws IOException {
        int calculatedCalories = 0;

        for(int i = 0; i < ingredients.size(); i++) {
            calculatedCalories = ingredients.get(i).getIngredient().getCalories();
        }

        this.setCalories(calculatedCalories);
    }

    public void calcProtein(ArrayList<Ingredient> ingredients) throws IOException {
        int calculatedProtein = 0;

        for(int i = 0; i < ingredients.size(); i++) {
            calculatedProtein = ingredients.get(i).getIngredient().getProtein();
        }

        this.setProtein(calculatedProtein);
    }

    public void calcCarbs(ArrayList<Ingredient> ingredients) throws IOException {
        int calculatedCarbs = 0;

        for(int i = 0; i < ingredients.size(); i++) {
            calculatedCarbs = ingredients.get(i).getIngredient().getCarbs();
        }

        this.setCarbs(calculatedCarbs);
    }

    public void calcFat(ArrayList<Ingredient> ingredients) throws IOException {
        int calculatedFat = 0;

        for(int i = 0; i < ingredients.size(); i++) {
            calculatedFat = ingredients.get(i).getIngredient().getFat();
        }

        this.setFat(calculatedFat);
    }

    public void checkIfAlcoholic(ArrayList<Ingredient> ingredients) {
        boolean isAlcoholic = false;

        for(int i = 0 ; i < ingredients.size() && !isAlcoholic; i++) {
            if(ingredients.get(i).getIngredient().getIsAlcoholic()) {
                isAlcoholic = true;
            }
        }

        this.setAlcoholic(isAlcoholic);
    }

    public void checkIfSpicy(ArrayList<Ingredient> ingredients) {
        boolean isSpicy = false;

        for(int i = 0 ; i < ingredients.size() && !isSpicy; i++) {
            if(ingredients.get(i).getIngredient().getIsSpicy()) {
                isSpicy = true;
            }
        }

        this.setSpicy(isSpicy);
    }

    public void checkIfVegan(ArrayList<Ingredient> ingredients) {
        boolean isVegan = true;

        for(int i = 0 ; i < ingredients.size() && isVegan; i++) {
            if(!ingredients.get(i).getIngredient().getIsVegan()) {
                isVegan = false;
            }
        }

        this.setVegan(isVegan);
    }

    public void checkIfVegetarian(ArrayList<Ingredient> ingredients) {
        boolean isVegetarian = true;

        for(int i = 0 ; i < ingredients.size() && isVegetarian; i++) {
            if(!ingredients.get(i).getIngredient().getIsVegetarian()) {
                isVegetarian = false;
            }
        }

        this.setVegetarian(isVegetarian);
    }

    public void checkIfGlutenFree(ArrayList<Ingredient> ingredients) {
        boolean isGlutenFree = true;

        for(int i = 0 ; i < ingredients.size() && isGlutenFree; i++) {
            if(!ingredients.get(i).getIngredient().getIsGlutenFree()) {
                isGlutenFree = false;
            }
        }

        this.setGlutenFree(isGlutenFree);
    }
}