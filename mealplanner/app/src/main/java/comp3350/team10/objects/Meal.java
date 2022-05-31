package comp3350.team10.objects;

public class Meal {
    private String name;
    private String path;
    private MealIngredients[] ingredients;
    private String[] instructions;

    public Meal(String name, String path, MealIngredients[] ingredients, String[] instructions) {
        this.name = name;
        this.path = path;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}