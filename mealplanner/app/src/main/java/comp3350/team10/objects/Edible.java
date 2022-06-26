package comp3350.team10.objects;

import android.widget.ImageView;

import java.io.IOException;

import android.widget.ImageView;

public abstract class Edible implements ListItem {
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter}; //All possible units for a given edible

    //Details
    private int edibleID;                       //This edibles database key
    private String name;                        //The name
    private String description;                 //A brief description
    private int quantity;                       //The quantity
    private Unit quantityUnit;                  //The unit of the given quantity

    //Nutritional value
    private int calories;                       //The calories for a given edible
    private int protein;                        //The protein value
    private int carbs;                          //the carb value
    private int fat;                            //The fat value

    //Metadata
    private ImageView photo;                    //The image path for a given edible
    private ListItem.FragmentType fragmentType; //How it should appear on recycler views                        

    public Edible() {
        this.edibleID = -1;
        this.name = null;
        this.description = null;
        this.quantity = -1;
        this.quantityUnit = null;

        this.calories = 0;
        this.protein = 0;
        this.carbs = 0;
        this.fat = 0;

        this.photo = null;
        this.fragmentType = null;
    }

    public void init(int id, String name, String desc, int qty, Unit unit, ImageView photo, ListItem.FragmentType view) throws IOException {
        this.setDBKey(id);
        this.setName(name);
        this.setDescription(desc);
        this.setQuantity(qty);
        this.setQuantityUnit(unit);

        this.setPhoto(photo);
        this.setFragmentType(view);
    }

    public void setQuantityUnit(Unit unit) {

    }

    public void setDescription(String description) {

    }

    public void modifyCalories(int amount) throws IOException {
        if(this.calories + amount >= Constant.ENTRY_MIN_VALUE && this.calories + amount <= Constant.ENTRY_MAX_VALUE) {
            this.calories += amount;
        }
        else {
            throw new IOException("Invalid incremental calorie change");
        }
    }

    public void setFragmentType(ListItem.FragmentType newFragmentType) throws IOException {
        if(newFragmentType != null) {
            this.fragmentType = newFragmentType;
        }
        else {
            throw new IOException("Invalid fragment type");
        }
    }

    public void setCalories(int newCalories) throws IOException {
        if(newCalories <= Constant.ENTRY_MAX_VALUE && newCalories >= Constant.ENTRY_MIN_VALUE) {
            this.calories = newCalories;
        }
        else {
            throw new IOException("Invalid calorie value");
        }
    }

    public void setUnit(Unit newUnit) throws IOException {
        if(newUnit != null) {
            this.quantityUnit = newUnit;
        }
        else {
            throw new IOException("Invalid unit");
        }
    }

    public void setQuantity(int newQuantity) throws IOException {
        if (newQuantity <= Constant.ENTRY_MAX_VALUE && newQuantity > Constant.ENTRY_MIN_VALUE) {
            this.quantity = newQuantity;
        }
        else {
            throw new IOException("Invalid quantity");
        }
    }

    private void setDBKey(int dbkey) throws IOException {
        if(dbkey >= 0) {
            this.edibleID = dbkey;
        }
        else {
            throw new IOException("Invalid DB key");
        }
    }

    public void setName(String name) throws IOException{
        if(name != null && !name.equals("")) {
            this.name = name;
        }
        else {
            throw new IOException("Invalid name");
        }
    }

    public void setPhoto(ImageView newPhoto) throws IOException {
        if(newPhoto != null) {
            this.photo = newPhoto;
        }
        else {
            throw new IOException("Invalid photo");
        }
    }

    public int getCalories() {
        return this.calories;
    }

    public String getName() {
        return this.name;
    }

    public ImageView getPhoto() {
        return this.photo;
    }

    public ListItem.FragmentType getFragmentType() {
        return this.fragmentType;
    }

    public Unit getUnit() {
        return this.quantityUnit;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getDbkey() {
        return this.edibleID;
    }
}