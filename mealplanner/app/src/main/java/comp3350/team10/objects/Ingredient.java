import java.io.IOException;

public class Ingredient {
    private Food ingredient;            //The food that we will use as an ingredient
    private int quantity;               //The quantity to use of the ingredient
    private Edible.Unit quantityUnit;   //The units of the quantity of the ingredient

    public Ingredient() {
        this.ingredient = null;
        this.quantity = -1;
        this.quantityUnit = null;
    }


    public init(Food ingredient, int quantity, Edible.Unit quantityUnit) throws IOException {
        this.setIngredient(ingredient);
        this.setQuantity(quantity);
        this.setQuantityUnit(quantityUnit);
    }

    public void setIngredient(Food newIngredient) throws IOException {
        if(newIngredient != null) {
            this.ingredient = newIngredient;
        }
        else {
            throw new IOException("Invalid ingredient food");
        }
    }

    public void setQuantity(int newQuantity) throws IOException {
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

    public Food getIngredient() {
        return this.ingredient;
    }
    
    public int getQuantity() {
        return this.quantity;
    }

    public Edible.Unit getQuantityUnits() {
        return this.quantityUnit;
    }
}
