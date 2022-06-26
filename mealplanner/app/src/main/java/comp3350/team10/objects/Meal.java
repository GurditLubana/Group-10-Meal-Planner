package comp3350.team10.objects;

public class Meal extends PreparedItem {
    private Ingredient[] ingredients;   //The ingredients in the meal\
    private boolean vegan;
    private boolean vegetarian;
    private boolean glutenFree;
    private boolean spicy;
    private boolean breakfast;
    private boolean lunch;
    private boolean supper;

    public Meal() {
        super();

        this.strIngredients = null;
    }


    public boolean init(String name, int iconPath, int calories, String ingredients, String instructions, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        return super.init(name, iconPath, calories, instructions, type, baseUnit, quantity, dbkey) && this.setIngredients(ingredients);
    }

    public String getIngredients() {
        return strIngredients;
    }

    public boolean setIngredients(String newIngredients) {
        boolean results = false;

        if (newIngredients != null) {
            this.strIngredients = newIngredients;
            results = true;
        }

        return results;
    }
}