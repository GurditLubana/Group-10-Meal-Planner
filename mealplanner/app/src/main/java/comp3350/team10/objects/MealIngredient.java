package comp3350.team10.objects;

public class MealIngredient extends Ingredient{
    private Food food;

    public MealIngredient(double quantity, String units, Food food) {
        super(quantity, units);
        
        this.food = food;
    }


    public Food getFood() {
        return this.food;
    }
}
