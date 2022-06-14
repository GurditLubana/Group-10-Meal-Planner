package comp3350.team10.objects;

public class Meal extends PreparedItem {
    private String strIngredients;   //The ingredients in the meal
    private Food[] foodIngredients;

    public Meal(String name, int iconPath, int calories, String ingredients, String instructions, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, calories, instructions, type, baseUnit, quantity, dbkey);
        this.strIngredients = ingredients;
    }

    public Meal(String name, int iconPath, int calories, Food[] ingredients, String[] instructions, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, calories, instructions.toString(), type, baseUnit, quantity, dbkey);
        this.foodIngredients = ingredients;
    }

    public String getIngredients() {
        return strIngredients;
    }

    public void setIngredients(String newIngredients) {
        this.strIngredients = newIngredients;
    }
}