package comp3350.team10.objects;

import android.widget.ListView;

import java.lang.Math;

public class Food extends Edible {
    public Food() {
        super();
    }

    public Food(ListItem.FragmentType type) {
        super(type);    //used for add card
    }


    public boolean init(String name, int iconPath, int calories, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        return super.init(name, iconPath, type, baseUnit, quantity, dbkey);
    }
}