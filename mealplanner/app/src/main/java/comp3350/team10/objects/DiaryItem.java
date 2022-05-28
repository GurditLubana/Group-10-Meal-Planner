package comp3350.team10.objects;

public class DiaryItem implements ListItem{

    private int key;
    private FragmentType fragType;
    private String name;
    private int calories;
    private Unit unit;
    private int quantity;
    private String myImage;

    public DiaryItem(){
        key = 0;
        fragType = FragmentType.diaryEntry;
        name = "some food";
        calories = 5;
        unit = Unit.g;
        quantity = 50;
        myImage = "imgPath";
    }

    public DiaryItem(int ky, FragmentType ft, String nm, int cals, Unit un, int qty, String loc){
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
}
