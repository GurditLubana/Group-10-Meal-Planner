package comp3350.team10.objects;

public class Meal extends PreparedItem {
    private MealIngredient[] ingredients;

    public Meal(String name, String path, int calories, String[] instructions, MealIngredient[] ingredients) {
        super(name, path, calories, instructions);
        this.ingredients = ingredients;
    }

    public MealIngredient[] getIngredients() {
        return ingredients;
    }

    public void changeIngredients(MealIngredient[] newIngredients) {
        this.ingredients = newIngredients;
    }
}