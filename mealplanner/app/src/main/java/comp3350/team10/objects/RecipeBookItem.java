package comp3350.team10.objects;
import android.content.res.Resources;

import comp3350.team10.R;

public class RecipeBookItem implements ListItem{
    private FragmentType fragType;  //How it should appear on recycler views
    private Edible item;            //The item's class reference (drink, meal or food) 
    private int key;                //database key

    private int image;  //this is getting deleted in the future???

    public RecipeBookItem(Edible item, int image, int key) { //this needs to be refactored
        this.fragType = ListItem.FragmentType.recipe;
        this.image = image;
        this.item = item;
        this.key = key;
    }

    @Override
    public FragmentType getFragmentType() {
        return fragType;
    }

    public int getImage(){
        return image;
    }
}
