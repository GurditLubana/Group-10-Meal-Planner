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

    public Edible(String name, int iconPath, ListItem.FragmentType type, Unit baseUnit, int quantity, int dbkey) {
        this.name = name;
        this.calories = 0;
        this.dbkey = -1;
        this.iconPath = -1;
        this.fragmentType = null;
        this.baseUnit = null;
        this.quantity = -1;
    }

    public Edible(ListItem.FragmentType type) {
        this.fragmentType = type;
    }


    public boolean init(String name, int iconPath, ListItem.FragmentType type, ListItem.Unit baseUnit, int quantity, int dbkey) {
        return this.setName(name) && this.setIconPath(iconPath) && this.setFragmentType(type) && 
            this.setBaseUnit(baseUnit) && this.setQuantity(quantity) && this.setDbkey(dbkey);
    }

    public boolean modifyCalories(int amount) {
        boolean valid = amount > 0 && this.calories + amount <= LIMIT;

        if(valid) {
            this.calories += amount;
        }
        
        return valid;
    }

    public Integer getCalories() {
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
        boolean valid = validFragEnum(fragmentType);

        if(valid) {
            this.fragmentType = fragmentType;
        }
        
        return valid;
    }

    public boolean setCalories(Integer newCalories) {
        boolean valid = newCalories <= LIMIT && newCalories > 0;

        if(valid) {
            this.calories = newCalories;
        }

        return valid;
    }

    public boolean setBaseUnit(ListItem.Unit baseUnit) {
        boolean valid = validUnitEnum(baseUnit);
        
        if(valid) {
            this.baseUnit = baseUnit;
        }

        return valid;
    }

    public boolean setQuantity(int quantity) {
        boolean valid = quanity <= LIMIT && quantity > 0;
        
        if(valid) {
            this.quantity = quantity;
        }

        return valid;
    }

    public boolean setDbkey(int dbkey) {
        boolean valid = dbkey > 0;;

        if(valid) {
            this.dbkey = dbkey;
        }
        
        return valid;
    }

    public boolean setName(String name) {
        boolean valid = !name.equals("");

        if(valid) {
            this.name = name;
        }

        return false;
    }

    public boolean setIconPath(int iconPath) {
        boolean valid = iconPath > 0;;

        if(valid) {
            this.iconPath = iconPath;
        }
        
        return valid;
    }
}
