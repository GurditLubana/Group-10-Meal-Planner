package comp3350.team10.objects;

public class Meal extends PreparedItem {
    private String strIngredients;   //The ingredients in the meal

    public Meal() {
        super();

        this.strIngredients = null;
    }


    public boolean init(String name, int iconPath, int calories, String ingredients, String instructions, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        return super.init(name, iconPath, calories, instructions, type, baseUnit, quantity, dbkey) &&
                this.setIngredients(ingredients);
    }

    public String getIngredients() {
        return strIngredients;
    }

    public boolean setIngredients(String newIngredients) {
        boolean results = false;

        if (newIngredients == null || (newIngredients.length() > 0)) {
            this.strIngredients = newIngredients;
            results = true;
        }

        return results;
    }
}