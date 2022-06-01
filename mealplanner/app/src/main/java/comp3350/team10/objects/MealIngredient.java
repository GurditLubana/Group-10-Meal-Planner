public class MealIngredient extends Ingredient{
    private Food foods;

    public MealIngredient(float quantity, String units, Food food) {
        super(quantity, units);
        
        this.food = food;
    }


    public Food getFood() {
        return this.food;
    }
}
