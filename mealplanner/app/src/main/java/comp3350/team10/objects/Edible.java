package comp3350.team10.objects;

public abstract class Edible {
    private ListItem.FragmentType fragmentType; //How it should appear on recycler views
    private Unit baseUnit;                      //The unit of a given edible
    private int quantity;                       //The quantity of a given edible
    private int calories;                       //The calories for a given edible
    private int iconPath;                       //The image path for a given edible
    private String name;                        //The name for a given variable
    private int dbkey;                          //This edibles database key

    public Edible(String name, int iconPath, ListItem.FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        this.name = name;
        this.calories = 0;
        this.dbkey = dbkey;
        this.iconPath = iconPath;
        this.fragmentType = type;
        this.baseUnit = baseUnit;
        this.quantity = quantity;
    }


    public void modifyCalories(int amount) {
        this.calories += amount;
    }

    public Integer getCalories() {
        return Integer.parseInt(this.calories);
    }

    public String getName() {
        return this.name;
    }

    public int getIconPath() {
        return this.iconPath;
    }

    public ListItem.FragmentType getFragmentType(){
        return this.fragmentType;
    }

    public Unit getBaseUnit(){
        return this.baseUnit;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getDbkey() {
        return this.dbkey;
    }

    public void setFragmentType(ListItem.FragmentType fragmentType){
        this.fragmentType = fragmentType;
    }

    public void setCalories(Integer newCalories){
        this.calories = newCalories;
    }

    public void setBaseUnit(Unit baseUnit) {
        this.baseUnit = baseUnit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDbkey(int dbkey) {
        this.dbkey = dbkey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIconPath(int iconPath) {
        this.iconPath = iconPath;
    }
}
