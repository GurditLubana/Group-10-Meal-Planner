package comp3350.team10.objects;

import java.io.IOException;

public class Ingredient {
    private Edible ingredient;          //The food that we will use as an ingredient
    private double quantity;            //The quantity to use of the ingredient
    private Edible.Unit quantityUnit;   //The units of the quantity of the ingredient

    public Ingredient() {
        this.ingredient = null;
        this.quantity = -1;
        this.quantityUnit = null;
    }


    public Ingredient init(Edible ingredient, double quantity, Edible.Unit quantityUnit) throws IOException {
        this.setIngredient(ingredient);
        this.setQuantity(quantity);
        this.setQuantityUnit(quantityUnit);

        return this;
    }

    public void setIngredient(Edible newIngredient) throws IOException {
        if(newIngredient != null && newIngredient.getCalories() != -1 && newIngredient.getProtein() != -1 &&
        newIngredient.getCarbs() != -1 && newIngredient.getFat() != -1) {
            this.ingredient = newIngredient;
        }
        else {
            throw new IOException("Invalid ingredient food");
        }
    }

    public void setQuantity(double newQuantity) throws IOException {
        if(newQuantity > 0 && newQuantity <= Constant.ENTRY_MAX_VALUE) {
            this.quantity = newQuantity;
        }
        else {
            throw new IOException("Invald ingredient quantity");
        }
    }

    public void setQuantityUnit(Edible.Unit newUnit) throws IOException {
        if(newUnit != null) {
            this.quantityUnit = newUnit;
        }
        else {
            throw new IOException("Invald ingredient unit");
        }
    }

    public Edible getIngredient() {
        return this.ingredient;
    }
    
    public double getQuantity() {
        return this.quantity;
    }

    public Edible.Unit getQuantityUnits() {
        return this.quantityUnit;
    }
}
