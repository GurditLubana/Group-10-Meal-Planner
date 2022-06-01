public class DrinkIngredient extends Ingredient {
    private String name;
    private boolean alcoholic;
    private boolean replacement;

    public DrinkIngredient(float quantity, String units, String name, boolean alcoholic, boolean replacement) {
        super(quantity, units);
        
        this.name = name;
        this.alcoholic = alcoholic;
        this.replacement = replacement;
    }


    public String getName() {
        return this.name;
    }

    public boolean isAlcoholic() {
        return this.alcoholic;
    }

    private boolean isReplacement() {
        return this.replacement;
    }
}
