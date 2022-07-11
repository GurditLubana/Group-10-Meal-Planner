package comp3350.team10.objects;

public class DrinkIngredient extends Ingredient {
    private boolean substitute;     //A flag that represents whether this ingredient can be balanced around alcohol modifications
    
    public DrinkIngredient() {
        super();

        this.substitute = false;
    }

    public void setSubstitute(boolean isSubstitute) {
        this.substitute = isSubstitute;
    }

    public boolean getIsSubstitute() {
        return this.substitute;
    }
}