package comp3350.team10.objects;

public abstract class Edible implements ListItem{
    private int calories;
    private String name;
    private int iconPath;
    private FragmentType fragmentType;
    private Unit baseUnit;
    private int quantity;
    private int dbkey;

    public Edible(String name, int iconPath, FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        this.calories = 0;
        this.name = name;
        this.iconPath = iconPath;
        this.fragmentType = type;
        this.baseUnit = baseUnit;
        this.quantity = quantity;
        this.dbkey = dbkey;
        //System.out.println("After edible constructor");
    }


    public void modifyCalories(int amount) {
        this.calories += amount;
    }

    public Integer getCalories() {
        return new Integer(this.calories);
    }

    public String getName() {
        return this.name;
    }

    public int getIconPath() {
        return this.iconPath;
    }

    public FragmentType getFragmentType(){
        return this.fragmentType;
    }

    public Unit getBaseUnit(){
        return this.baseUnit;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getDbkey() {
        return dbkey;
    }

    public void setFragmentType(FragmentType fragmentType){
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
