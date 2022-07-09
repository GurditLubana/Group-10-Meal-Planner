package comp3350.team10.objects;

import java.io.IOException;

import comp3350.team10.business.UnitConverter;

public class EdibleLog extends Edible {
    //private UnitConverter baseConverter;  originally wanted to use a single converter but variables need to be reset so this breaks life
    private Unit baseUnit;
    private double baseCalories;
    private double baseQuantity;

    private double quantity;
    private Edible.Unit unit;
    private double calories;

    public EdibleLog(Edible edible) throws IllegalArgumentException, Exception {
        super();

        this.baseUnit = edible.getUnit();
        this.baseQuantity = edible.getQuantity();
        this.baseCalories = edible.getCalories();

        try {
            this.initDetails(edible.getDbkey(), edible.getName(), edible.getDescription(), edible.getQuantity(), edible.getUnit());
            this.initNutrition(edible.getCalories(), edible.getProtein(), edible.getCarbs(), edible.getFat());
            this.initCategories(edible.getIsAlcoholic(), edible.getIsSpicy(), edible.getIsVegan(), edible.getIsVegetarian(), edible.getIsGlutenFree());
            this.initMetadata(edible.getIsCustom(), edible.getPhoto());
            this.init(edible.getQuantity(), edible.getUnit());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("EdibleLog init failed " + e);
        } catch (Exception e) {
            throw new Exception("EdibleLog init setCalories failed " + e);
        }
    }

    public EdibleLog init(double quantity, Edible.Unit unit) throws IllegalArgumentException, Exception {
        this.setQuantity(quantity);
        this.setUnit(unit);
        this.setCalories();

        return this;
    }

    public void setQuantity(double newQuantity) throws IllegalArgumentException {
        if (newQuantity > 0 && newQuantity <= Constant.ENTRY_MAX_VALUE) {
            this.quantity = newQuantity;
        } else {
            throw new IllegalArgumentException("Invalid log quantity");
        }
    }

    public void setCalories() throws Exception { //cannot call super because these are shadowed and is not supported in java
        UnitConverter converter = new UnitConverter();
        double newCalories = 0;
        try {
            newCalories = converter.convert(this.baseUnit, this.baseQuantity, this.baseCalories, this.unit, this.quantity);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }

        this.calories = newCalories;
    }

    public void setUnit(Edible.Unit newUnit) throws IllegalArgumentException {
        if (newUnit != null) {
            this.unit = newUnit;
        } else {
            throw new IllegalArgumentException("Invalid log unit");
        }
    }

    public double getQuantity() {
        return this.quantity;
    }

    public Edible.Unit getUnit() {
        return this.unit;
    }

    public double getCalories() {
        return this.calories;
    }
}
