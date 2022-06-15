package comp3350.team10.objects;

import android.widget.ListView;

import java.lang.Math;

public class Food extends Edible {

    public Food(String name, int iconPath, int calories, ListItem.FragmentType type, Edible.Unit baseUnit, int quantity, int dbkey) {
        super(name, iconPath, type, baseUnit, quantity, dbkey);
    }

    public Food(ListItem.FragmentType type) {
        super(null, -1, type, null, -1, -1);    //used for add card
    }
}