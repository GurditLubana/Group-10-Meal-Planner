package comp3350.team10.objects;

public class DrinkIngredient {
    private String name;
    private float quantity;
    private String unit;
    private boolean isAlcoholic;
    private boolean isReplacement; 

    public DrinkIngredient(String name, float quantity, String unit, boolean alcoholic, boolean replacement) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.alcoholic = alcoholic;
        this.replacement = replacement;
    }


    public void removeIngredient() {

    }

    public void modIngredientQty() {

    }
    
    public void incrQty(float amount) {
        this.quantity += amount;
    }

    public void decrQty(float amount) {
        this.quantity -= amount;
    }

    public String getName() {
        return this.name;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public String getUnit() {
        return this.unit;
    }

    public boolean isAlcoholic() {
        return this.alcoholic;
    }

    public boolean isReplacement() {
        return this.replacement;
    }
}
