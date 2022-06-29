package comp3350.team10.objects;

public class DrinkIngredient extends Ingredient {
    private boolean substitute;     //A flag that represents whether this ingredient can be balanced around alcohol modifications
    
    public DrinkIngredient() {
        super();

        this.substitute = false;
    }


    private void setSubtitute(boolean isSubstitute) {
        this.substitute = isSubstitute;
    }

    public boolean isSubstitute() {
        return this.substitute;
    }
}