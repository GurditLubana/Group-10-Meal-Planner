package comp3350.team10.objects;

public class Meal extends PreparedItem {
    private MealIngredient[] ingredients;   //The ingredients in the meal

    public Meal(String name, int iconPath, int calories, MealIngredient[] ingredients, String[] instructions, FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, calories, instructions, type, baseUnit, quantity, dbkey);
        this.ingredients = ingredients;
    }
    

    public MealIngredient[] getIngredients() {
        return ingredients;
    }

    public void changeIngredients(MealIngredient[] newIngredients) {
        this.ingredients = newIngredients;
    }
}