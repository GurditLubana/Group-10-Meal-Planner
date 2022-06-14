package comp3350.team10.objects;

import java.lang.Math;

public class Macros {
    private final static int MACRO_RANGE = 15;    //The highest possible quantity for a single macro
    private final static int MIN_MACRO = 1;       //The lowest possible quantity for a single macro
    
    //macros
    private int protein;    //The amount of protein   
    private int carbs;      //The amount of carbs
    private int fat;        //The amount of fat

    public Macros() {   //generate random amounts of macros
        this.protein = (int)(Math.random() * MACRO_RANGE) + MIN_MACRO;
        this.carbs = (int)(Math.random() * MACRO_RANGE) + MIN_MACRO;
        this.fat = (int)(Math.random() * MACRO_RANGE) + MIN_MACRO;
    }

    
    public int getCarbs() {
        return this.carbs;
    }

    public int getProtein() {
        return this.protein;
    }

    public int getFat() {
        return this.fat;
    }
}
