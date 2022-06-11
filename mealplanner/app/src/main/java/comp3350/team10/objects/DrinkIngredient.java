package comp3350.team10.objects;

public class DrinkIngredient extends Ingredient {
    private String name;
    private boolean alcoholic;
    private boolean replacement;

    public DrinkIngredient(String name, double quantity, String units,  boolean replacement, boolean alcoholic) {
        super(quantity, units);
        
        this.name = name;
        this.replacement = replacement;
        this.alcoholic = alcoholic;
    }

    public DrinkIngredient(String name, double quantity, String units) {
        super(quantity, units);

        this.name = name;
        this.replacement = false;
        this.alcoholic = false;
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
