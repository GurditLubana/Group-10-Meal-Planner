package comp3350.team10.objects;

import java.io.IOException;

public class DrinkIngredient extends Ingredient {
    private boolean substitute;     //A flag that represents whether this ingredient can be balanced around alcohol modifications
    
    public DrinkIngredient() {
        super();

        this.substitute = false;
    }


    public DrinkIngredient(Food ingredient, int quantity, Edible.Unit quantityUnit, boolean isSubstitute) throws IOException {
        super.init(ingredient, quantity, quantityUnit);
        this.setSubtitute(isSubstitute);
    }

    private void setSubtitute(boolean isSubstitute) {
        this.substitute = isSubstitute;
    }

    public boolean isSubstitute() {
        return this.substitute;
    }
}
