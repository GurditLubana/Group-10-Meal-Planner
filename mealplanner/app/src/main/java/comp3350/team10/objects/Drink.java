package comp3350.team10.objects;

public class Drink {
    private int calories;
    private String name;
    private String path;
    private String[] instructions;
    private DrinkIngredient[] ingredients;

    public Drink(String name, String path, int calories, String[] instructions, DrinkIngredient[] ingredients) {
        this.name = name;
        this.path = path;
        this.calories = calories;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

    public int getCalories() {
        return this.calories;
    }

    public int getInstructions() {
        return this.instructions;
    }

    public int getIngredients() {
        return this.ingredients;
    }

    public void flexIngredients(float alcoholLvl) {
        //calculate alcohol lvls removed from each drink
        //distribute this to all replacements
    }

    public void getReplacements() {

    }
}
