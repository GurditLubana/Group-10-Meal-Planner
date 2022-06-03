package comp3350.team10.objects;
import java.lang.Math;

public class Food extends Edible{
    private static int CALORIE_RANGE = 300;
    private static int MIN_CALORIES = 25;
	private Macros macros;

    public Food(String name, int iconPath, int calories, FragmentType type, Unit baseUnit, int quantity) {
        super(name, iconPath, type, baseUnit, quantity);
        
        super.modifyCalories(calories);
        this.macros = new Macros();
    }

    public Food(String name, int iconPath) {
        super(name, iconPath, FragmentType.noType, Unit.serving, 1);
        
        int randomCalories = (int)(Math.random() * CALORIE_RANGE) + MIN_CALORIES;
        super.modifyCalories(randomCalories);
        this.macros = new Macros();
    }

    public Macros getMacros() {
        return this.macros;
    }
}
