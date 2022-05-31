package comp3350.team10.objects;
import java.lang.Math;

public class Macros {
    private static int MIN_MACRO = 1;
    private static int MACRO_RANGE = 15;
    private int carbs;
    private int protein;
    private int fat;

    public Macros() {
        this.carbs = (int)(Math.random() * MACRO_RANGE) + MIN_MACRO;
        this.protein = (int)(Math.random() * MACRO_RANGE) + MIN_MACRO;
        this.fat = (int)(Math.random() * MACRO_RANGE) + MIN_MACRO;
    }

    
    public int getCarbs() {
        return this.carbs;
    }

    public int getProtein() {
        return this.getProtein();
    }

    public int getFat() {
        return this.getFat();
    }
}
