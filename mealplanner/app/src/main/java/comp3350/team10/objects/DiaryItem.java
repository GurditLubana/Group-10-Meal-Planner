package comp3350.team10.objects; //most likely getting removed

import java.util.Date;
import java.util.Calendar;

public class DiaryItem {
    private ListItem.FragmentType fragType; //How it should appear on recycler views
    private Edible item;                    //The Edible
    private int key;                        //This entries database key


    public DiaryItem(Edible item, int key) {
        this.fragType = FragmentType.diaryEntry;
        this.item = item;
        this.key = key;
    }

    public DiaryItem(ListItem.FragmentType type, Edible item, int key) {
        this.fragType = type;
        this.item = item;
        this.key = key;
    }

    public DiaryItem() {
        this.fragType = ListItem.FragmentType.diaryEntry;
        //this.item = new Food("some food", "); //defaults Josef had <3 so I dont mess with your stuff
        this.date = calendar.getTime(); //give todays date
        this.key = 0;
    }

    public ListItem.FragmentType getFragmentType() {
        return this.fragType;
    }

    public Edible getItem() {
        return this.item;
    }

    public Date getDate() {
        return this.date;
    }

    public int getKey() {
        return this.key;
    }

    public Unit getBaseUnit(){
        return null;
    }

    public int getQuantity(){
        return 0;
    }
}
