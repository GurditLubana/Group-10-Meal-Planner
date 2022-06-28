package comp3350.team10.objects;

import android.widget.ImageView;
import java.io.IOException;

public abstract class Edible implements ListItem {
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter}; //All possible units for a given edible

    //Edible details
    private int edibleID;                       //This edibles database key
    private String name;                        //The name
    private String description;                 //A brief description
    private int baseQuantity;                   //The quantity
    private Unit baseUnit;                      //The unit of the given quantity

    //Nutritional content
    private int calories;                       //The calories for a given edible
    private int protein;                        //The protein value
    private int carbs;                          //the carb value
    private int fat;                            //The fat value

    //Filter flags
    private boolean isAlcoholic;                //Flag representing whether the Edible contains alcohol or not
    private boolean isSpicy;                    //Flat representing whether the Edible is spicy or not
    private boolean isVegan;                    //Flag that represents whether this Edible is vegan or not
    private boolean isVegetarian;               //Flag that represents whether this Edible is vegetarian or not
    private boolean isGlutenFree;               //Flag that represents whether this Edible is glutenFree or not
    private boolean isCustom;                   //Flag that represents whether this Edible is custom or not

    //Metadata
    private byte[] photoBytes;                  //The image path for a given edible
    private ListItem.FragmentType fragmentType; //How it should appear on recycler views                        

    public Edible() {
        this.edibleID = -1;
        this.name = null;
        this.description = null;
        this.baseQuantity = -1;
        this.baseUnit = null;

        this.calories = 0;  //these need to be 0 (add card was messing with this)
        this.protein = 0;
        this.carbs = 0;
        this.fat = 0;

        this.isAlcoholic = false;
        this.isSpicy = false;
        this.isVegan = false;
        this.isVegetarian = false;
        this.isGlutenFree = false;

        this.isCustom = false;
        this.photoBytes = null;
        this.fragmentType = FragmentType.recipe;
    }

    
    public Edible initDetails(int id, String name, String description, int quantity, Unit unit) throws IOException {
        this.setDBKey(id);
        this.setName(name);
        this.setDescription(description);
        this.setBaseQuantity(quantity);
        this.setBaseUnit(unit);

        return this;
    }
    
    public Edible initNutrition(int calories, int protein, int carbs, int fat) throws IOException {
        this.setCalories(calories);
        this.setProtein(protein);
        this.setCarbs(carbs);
        this.setFat(fat);

        return this;
    }

    public Edible initCategories(boolean alcoholic, boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree) {
        this.setAlcoholic(alcoholic);
        this.setSpicy(spicy);
        this.setVegan(vegan);
        this.setVegetarian(vegetarian);
        this.setGlutenFree(glutenFree);

        return this;
    }

    public Edible initMetadata(boolean custom, byte[] photo, ListItem.FragmentType view) throws IOException {
        this.setCustom(custom);
        this.setPhotoBytes(photo);
        this.setFragmentType(view);

        return this;
    }

    
    public void setCustom(boolean newCustom) {
        this.isCustom = newCustom;
    }

    public boolean getIsCustom() {
        return this.isCustom;
    }

    public void setAlcoholic(boolean newAlcoholic) {
        this.isAlcoholic = newAlcoholic;
    }

    public void setSpicy(boolean newSpicy) {
        this.isSpicy = newSpicy;
    }

    public void setVegan(boolean newVegan) {
        this.isVegan = newVegan;
    }

    public void setVegetarian(boolean newVegetarian) {
        this.isVegetarian = newVegetarian;
    }

    public void setGlutenFree(boolean newGutenFree) {
        this.isGlutenFree = newGutenFree;
    }

    public boolean getIsAlcoholic() {
        return this.isAlcoholic;
    }

    public boolean getIsSpicy() {
        return this.isSpicy;
    }

    public boolean getIsVegan() {
        return this.isVegan;
    }

    public boolean getIsVegetarian() {
        return this.isVegetarian;
    }

    public boolean getIsGlutenFree() {
        return this.isGlutenFree;
    }

    public void setDescription(String newDescription) throws IOException {
        if(newDescription != null) {
            this.description = newDescription;
        }
        else {
            throw new IOException("Invalid description");
        }
    }

    public void setProtein(int newProtein) throws IOException {
        if(newProtein <= Constant.ENTRY_MAX_VALUE && newProtein >= Constant.ENTRY_MIN_VALUE) {
            this.protein = newProtein;
        }
        else {
            throw new IOException("Invalid protein value");
        }
    }

    public int getProtein() {
        return this.protein;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public void setFat(int newFat) throws IOException {
        if(newFat <= Constant.ENTRY_MAX_VALUE && newFat >= Constant.ENTRY_MIN_VALUE) {
            this.fat = newFat;
        }
        else {
            throw new IOException("Invalid fat value");
        }
    }

    public int getFat() {
        return this.fat;
    }

    public void setCarbs(int newCarbs) throws IOException {
        if(newCarbs <= Constant.ENTRY_MAX_VALUE && newCarbs >= Constant.ENTRY_MIN_VALUE) {
            this.carbs = newCarbs;
        }
        else {
            throw new IOException("Invalid carb value");
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

    public void setBaseUnit(Unit newUnit) throws IOException {
        if(newUnit != null) {
            this.baseUnit = newUnit;
        }
        else {
            throw new IOException("Invalid unit");
        }
    }

    public void setBaseQuantity(int newQuantity) throws IOException {
        if (newQuantity <= Constant.ENTRY_MAX_VALUE && newQuantity > Constant.ENTRY_MIN_VALUE) {
            this.baseQuantity = newQuantity;
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

    public void setPhotoBytes(byte[] newPhoto) throws IOException {
        if(newPhoto != null && newPhoto.length != 0) {
            this.photoBytes = newPhoto;
        }
        else {
            throw new IOException("Invalid photo");
        }
    }

    public String getDesciprtion() {
        return this.description;
    }
    
    public int getCalories() {
        return this.calories;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getPhotoBytes() {
        return this.photoBytes;
    }

    public ListItem.FragmentType getFragmentType() {
        return this.fragmentType;
    }

    public Unit getUnit() {
        return this.baseUnit;
    }

    public int getQuantity() {
        return this.baseQuantity;
    }

    public int getDbkey() {
        return this.edibleID;
    }
}