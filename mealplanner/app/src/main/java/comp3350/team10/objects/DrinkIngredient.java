package comp3350.team10.objects;

public class DrinkIngredient extends Ingredient {
    private String name;            //Name of the ingredient
    private boolean alcoholic;      //Whether the ingredient contains alcohol or not
    private boolean replacement;    //Whether the ingredient is a suitable alcohol replacement

    public DrinkIngredient(String name, double quantity, String units,  boolean replacement, boolean alcoholic) {
        super(quantity, units);
        
        this.name = name;
        this.alcoholic = alcoholic;
        this.replacement = replacement;
    }

    public DrinkIngredient(String name, double quantity, String units) {
        super(quantity, units);
        
        this.name = name;
        this.alcoholic = false;
        this.replacement = false;
    }


    public String getName() {
        return this.name;
    }

    public boolean isAlcoholic() {
        return this.alcoholic;
    }

    public boolean isReplacement() {
        return this.replacement;
    }
}
