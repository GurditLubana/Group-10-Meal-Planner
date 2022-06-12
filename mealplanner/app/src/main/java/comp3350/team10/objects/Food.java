package comp3350.team10.objects;

import android.widget.ListView;

import java.lang.Math;

public class Food extends Edible {
    private static int CALORIE_RANGE = 300; //The highest number of calories a single food may have
    private static int MIN_CALORIES = 25;   //The lowest number of calories a single food item may have
	
    private Macros macros;                  //The macros for a the given food

    public Food(String name, int iconPath, int calories, ListItem.FragmentType type, ListItem.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, type, baseUnit, quantity, dbkey);
        
        super.modifyCalories(calories);
        this.macros = new Macros();
    }

    public Food(String name, int iconPath) {
        super(name, iconPath, ListItem.FragmentType.noType, ListItem.Unit.serving, 1, 0);
        
        int randomCalories = (int)(Math.random() * CALORIE_RANGE) + MIN_CALORIES;   //Generates random calories
        super.modifyCalories(randomCalories);
        this.macros = new Macros();
    }

    
    public Macros getMacros() {
        return this.macros;
    }
}