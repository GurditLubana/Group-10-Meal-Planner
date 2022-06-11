package comp3350.team10.objects;

import android.content.res.Resources;
import comp3350.team10.R;

public class RecipeBookItem {
    private ListItem.FragmentType fragType;  //How it should appear on recycler views
    private Edible item;            //The edible 
    private Unit unit;              //The unit of the edible
    private int key;                //This entries database key

    public RecipeBookItem(Edible item, int key) { //this needs to be refactored
        this.fragType = ListItem.FragmentType.recipe;
        this.unit = Unit.serving;
        this.item = item;
        this.key = key;
    }


    public ListItem.FragmentType getFragmentType() {
        return fragType;
    }

    public Edible getFood() {
        return this.item;
    }

    public Unit getBaseUnit(){
        return this.unit;
    }

    public int getQuantity(){
        return 1;
    }

    public Edible getItem() {
        return item;
    }
}