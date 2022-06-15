package comp3350.team10.objects;


public abstract class Edible implements ListItem {
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter};
    private ListItem.FragmentType fragmentType; //How it should appear on recycler views
    private Unit baseUnit;                      //The unit of a given edible
    private int quantity;                       //The quantity of a given edible
    private int calories;                       //The calories for a given edible
    private int iconPath;                       //The image path for a given edible
    private String name;                        //The name for a given variable
    private int dbkey;                          //This edibles database key

    public Edible() {
        this.name = null;
        this.calories = 0;
        this.dbkey = -1;
        this.iconPath = -1;
        this.fragmentType = null;
        this.baseUnit = null;
        this.quantity = -1;
    }

    public Edible(ListItem.FragmentType type) {
        this.fragmentType = type;
        this.name = null;
        this.calories = 0;
        this.dbkey = -1;
        this.iconPath = -1;
        this.baseUnit = null;
        this.quantity = -1;
    }


    public boolean init(String name, int iconPath, int calories, ListItem.FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        return this.setName(name) && this.setIconPath(iconPath) && this.setCalories(calories) && this.setFragmentType(type) &&
            this.setBaseUnit(baseUnit) && this.setQuantity(quantity) && this.setDbkey(dbkey);
    }

    public boolean modifyCalories(int amount) {
        boolean results = false;

        if(this.calories + amount >= Constant.ENTRY_MIN_VALUE && this.calories + amount <= Constant.ENTRY_MAX_VALUE) {
            this.calories += amount;
            results = true;
        }
        
        return results;
    }

    public int getCalories() {
        return this.calories;
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

    public boolean setFragmentType(ListItem.FragmentType fragmentType) {
        boolean results = false;

        if(fragmentType != null) {
            this.fragmentType = fragmentType;
            results = true;
        }
        
        return results;
    }

    public boolean setCalories(Integer newCalories) {
        boolean results = false;

        if(newCalories <= Constant.ENTRY_MAX_VALUE && newCalories >= Constant.ENTRY_MIN_VALUE) {
            this.calories = newCalories;
            results = true;
        }

        return results;
    }

    public boolean setBaseUnit(Unit baseUnit) {
        boolean results = false;
        
        if(baseUnit != null) {
            this.baseUnit = baseUnit;
            results = true;
        }

        return results;
    }

    public boolean setQuantity(int quantity) {
        boolean results = false;
        
        if(quantity <= Constant.ENTRY_MAX_VALUE && quantity > Constant.ENTRY_MIN_VALUE) {
            this.quantity = quantity;
            results = true;
        }

        return results;
    }

    public boolean setDbkey(int dbkey) {
        boolean results = false;

        if(dbkey >= 0) {
            this.dbkey = dbkey;
            results = true;
        }
        
        return results;
    }

    public boolean setName(String name) {
        boolean results = false;

        if(name != null && !name.equals("")) {
            this.name = name;
            results = true;
        }

        return results;
    }

    public boolean setIconPath(int iconPath) {
        boolean results = false;

        if(iconPath >= Constant.ENTRY_MIN_VALUE) {
            this.iconPath = iconPath;
            results = true;
        }
        
        return results;
    }
}
