package comp3350.team10.objects;
import java.lang.Math;

public class Food {
    private static int CALORIE_RANGE = 300;
    private static int MIN_CALORIES = 25;
    private String name;
	private String path;
	private int calories;
	private Macros macros;

    public Food(String name, String path, int calories) {
        this(name, path);
        this.calories = calories;
    }

    public Food(String name, String path) {
        this.name = name;
        this.path = path;
        this.macros = new Macros();
        this.calories = (int)(Math.random() * CALORIE_RANGE) + MIN_CALORIES;
    }
}
