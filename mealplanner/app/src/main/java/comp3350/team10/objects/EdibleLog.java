package comp3350.team10.objects;

import java.io.IOException;

public class EdibleLog extends Edible {
    private int quantity;
    private Edible.Unit unit;
    private int calories;

    public EdibleLog(Edible edible) {
        super();

        try {
            this.initDetails(edible.getDbkey(), edible.getName(), edible.getDesciprtion(), edible.getQuantity(), edible.getUnit());
            this.initNutrition(edible.getCalories(), edible.getProtein(), edible.getCarbs(), edible.getFat());
            this.initCategories(edible.getIsAlcoholic(), edible.getIsSpicy(), edible.getIsVegan(), edible.getIsVegetarian(), edible.getIsGlutenFree());
            this.initMetadata(true, edible.getPhotoBytes(), edible.getFragmentType());
            this.init(edible.getQuantity(), edible.getUnit());
            this.setCalories(edible.getCalories()); //logic here should be calculated and added inside init based on baseQuantity and baseUnit
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
    //need to create a relative calorie function

    public EdibleLog init(int quantity, Edible.Unit unit) throws IOException{
        this.setQuantity(quantity);
        this.setUnit(unit);

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

    public void setCalories(int newCalories) throws IOException {
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
