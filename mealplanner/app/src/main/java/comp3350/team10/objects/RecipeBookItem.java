package comp3350.team10.objects;
import android.content.res.Resources;

import comp3350.team10.R;

public class RecipeBookItem implements ListItem{
    private FragmentType fragType;  //How it should appear on recycler views
    private Edible item;            //The item's class reference (drink, meal or food)
    private int key;                //database key
    private Unit unit;

    private int image;  //this is getting deleted in the future???

    public RecipeBookItem(Edible item, int image, int key) { //this needs to be refactored
        this.fragType = ListItem.FragmentType.recipe;
        this.image = image;
        this.item = item;
        this.key = key;
        this.unit = Unit.serving;
    }

    @Override
    public FragmentType getFragmentType() {
        return fragType;
    }

    public int getImage(){
        return image;
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
