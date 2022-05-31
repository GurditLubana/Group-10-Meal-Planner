package comp3350.team10.objects;
import android.content.res.Resources;

import comp3350.team10.R;

public class RecipeBookItem implements ListItem{

    private int key;
    private FragmentType fragType;
    private String name;
    private int calories;
    private Unit unit;
    private int quantity;
    private int myImage;

    public RecipeBookItem(){

    }
    public RecipeBookItem(int ky, FragmentType ft, String nm, int cals, Unit un, int qty, int loc){
        key = ky;
        fragType = ft;
        name = nm;
        calories = cals;
        unit = un;
        quantity = qty;
        myImage = loc;
    }
    @Override
    public FragmentType getFragmentType() {
        return fragType;
    }

    public int getImage(){
        return myImage;
    }
}
