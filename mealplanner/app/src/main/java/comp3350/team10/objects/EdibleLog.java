package comp3350.team10.objects;

import java.io.IOException;

import comp3350.team10.business.UnitConverter;

public class EdibleLog extends Edible {
    //private UnitConverter baseConverter;  originally wanted to use a single converter but variables need to be reset so this breaks life
    private Unit baseUnit;
    private int baseCalories;
    private int baseQuantity;

    private int quantity;
    private Edible.Unit unit;
    private int calories;

    public EdibleLog(Edible edible) {
        super();

        this.baseUnit = edible.getUnit();
        this.baseQuantity = edible.getQuantity();
        this.baseCalories = edible.getCalories();

        try {
            //baseConverter = new UnitConverter(edible.getUnit(), edible.getQuantity(), edible.getCalories());
            this.initDetails(edible.getDbkey(), edible.getName(), edible.getDesciprtion(), edible.getQuantity(), edible.getUnit());
            this.initNutrition(edible.getCalories(), edible.getProtein(), edible.getCarbs(), edible.getFat());
            this.initCategories(edible.getIsAlcoholic(), edible.getIsSpicy(), edible.getIsVegan(), edible.getIsVegetarian(), edible.getIsGlutenFree());
            this.initMetadata(true, edible.getPhotoBytes(), edible.getFragmentType());
            this.init(edible.getQuantity(), edible.getUnit());
        }
        catch(Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public EdibleLog(FragmentType fragType) {
        super();

        try {
            this.setFragmentType(fragType);
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    public EdibleLog init(int quantity, Edible.Unit unit) throws IOException {
        this.setQuantity(quantity);
        this.setUnit(unit);
        this.setCalories();

        return this;
    }

    public void setQuantity(int newQuantity) throws IOException {
        if(newQuantity > 0 && newQuantity <= Constant.ENTRY_MAX_VALUE) {
            this.quantity = newQuantity;
        }
        else {
            throw new IOException("Invalid log quantity");
        }
    }

    public void setCalories() throws IOException { //cannot call super because these are shadowed and is not supported in java
        UnitConverter converter = new UnitConverter(this.baseUnit, this.baseQuantity, this.baseCalories);
        int newCalories = converter.getCalories(unit, quantity).intValue();

        if(newCalories >= 0 && newCalories <= Constant.ENTRY_MAX_VALUE) {
            this.calories = newCalories;
        }
        else {
            throw new IOException("Invalid log calories");
        }
    }

    public void setUnit(Edible.Unit newUnit) throws IOException {
        if(newUnit != null) {
            this.unit = newUnit;
        }
        else {
            throw new IOException("Invalid log unit");
        }
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Edible.Unit getUnit() {
        return this.unit;
    }

    public int getCalories() {
        return this.calories;
    }
}
