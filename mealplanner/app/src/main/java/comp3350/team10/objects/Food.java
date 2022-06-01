package comp3350.team10.objects;
import java.lang.Math;

public class Food extends Edible {
    private static int CALORIE_RANGE = 300;
    private static int MIN_CALORIES = 25;
	private Macros macros;

    public Food(String name, String path, int calories) {
        super(name, path);
        
        super.modifyCalories(calories);
        this.macros = new Macros();
    }

    public Food(String name, String path) {
        super(name, path);
        
        int randomCalories = (int)(Math.random() * CALORIE_RANGE) + MIN_CALORIES;
        super.modifyCalories(randomCalories);
        this.macros = new Macros();
    }

    public Macros getMacros() {
        return this.macros;
    }
}
