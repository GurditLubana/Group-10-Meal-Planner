package comp3350.team10.objects;

import java.util.Calendar;
import java.util.Date;

public class DiaryItem implements ListItem {
    private static Calendar calendar = Calendar.getInstance();
    private FragmentType fragType;  //How it should appear on recycler views
    private Edible item;            //The item's class reference (drink, meal or food) 
    private Date date;              //The date the meal should appear on
    private int key;                //database key


    public DiaryItem(Edible item, Date date, int key) {
        this.fragType = FragmentType.diaryEntry;
        this.item = item;
        this.date = date;
        this.key = key;
    }

    public DiaryItem(FragmentType type, Edible item, Date date, int key) {
        this.fragType = type;
        this.item = item;
        this.date = date;
        this.key = key;
    }

    public DiaryItem() {
        this.fragType = FragmentType.diaryEntry;
        //this.item = new Food("some food", "); //defaults Josef had <3 so I dont mess with your stuff
        this.date = calendar.getTime(); //give todays date
        this.key = 0;
    }

    @Override
    public FragmentType getFragmentType() {
        return fragType;
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
