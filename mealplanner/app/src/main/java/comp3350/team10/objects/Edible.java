package comp3350.team10.objects;

import android.widget.ImageView;
import java.io.IOException;

public class Edible {
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter}; //All possible units for a given edible
    private final int MIN_BYTE_LENGTH_PER_PHOTO = 35;

    //Edible details
    private int edibleID;                       //This edibles database key
    private String name;                        //The name
    private String description;                 //A brief description
    private double baseQuantity;                   //The quantity
    private Unit baseUnit;                      //The unit of the given quantity

    //Nutritional content
    private double calories;                       //The calories for a given edible
    private int protein;                        //The protein value
    private int carbs;                          //the carb value
    private int fat;                            //The fat value

    //Filter flags
    private boolean isAlcoholic;                //Flag representing whether the Edible contains alcohol or not
    private boolean isSpicy;                    //Flat representing whether the Edible is spicy or not
    private boolean isVegan;                    //Flag that represents whether this Edible is vegan or not
    private boolean isVegetarian;               //Flag that represents whether this Edible is vegetarian or not
    private boolean isGlutenFree;               //Flag that represents whether this Edible is glutenFree or not

    //Metadata
    private boolean isCustom;                   //Flag that represents whether this Edible is custom or not
    private byte[] photoBytes;                  //The image path for a given edible
    private String photo;

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
        this.photo = null;
    }

    
    public Edible initDetails(int id, String name, String description, double quantity, Unit unit) throws IllegalArgumentException {
        this.setDBKey(id);
        this.setName(name);
        this.setDescription(description);
        this.setBaseQuantity(quantity);
        this.setBaseUnit(unit);

        return this;
    }
    
    public Edible initNutrition(double calories, int protein, int carbs, int fat) throws IllegalArgumentException {
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

    public Edible initMetadata(boolean custom, byte[] photo) throws IOException { //TODO deprecate
        this.setCustom(custom);
        this.setPhotoBytes(photo);

        return this;
    }

    public Edible initMetadata(boolean custom, String photo) throws IllegalArgumentException {
        this.setCustom(custom);
        this.setPhoto(photo);

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

    public void setDescription(String newDescription) throws IllegalArgumentException {
        if(newDescription != null) {
            this.description = newDescription;
        }
        else {
            throw new IllegalArgumentException("Invalid description");
        }
    }

    public void setProtein(int newProtein) throws IllegalArgumentException {
        if(newProtein <= Constant.ENTRY_MAX_VALUE && newProtein >= Constant.ENTRY_MIN_VALUE) {
            this.protein = newProtein;
        }
        else {
            throw new IllegalArgumentException("Invalid protein value");
        }
    }

    public int getProtein() {
        return this.protein;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public void setFat(int newFat) throws IllegalArgumentException {
        if(newFat <= Constant.ENTRY_MAX_VALUE && newFat >= Constant.ENTRY_MIN_VALUE) {
            this.fat = newFat;
        }
        else {
            throw new IllegalArgumentException("Invalid fat value");
        }
    }

    public int getFat() {
        return this.fat;
    }

    public void setCarbs(int newCarbs) throws IllegalArgumentException {
        if(newCarbs <= Constant.ENTRY_MAX_VALUE && newCarbs >= Constant.ENTRY_MIN_VALUE) {
            this.carbs = newCarbs;
        }
        else {
            throw new IllegalArgumentException("Invalid carb value");
        }
    }

    public void setCalories(double newCalories) throws IllegalArgumentException {
        if(newCalories <= Constant.ENTRY_MAX_VALUE && newCalories >= Constant.ENTRY_MIN_VALUE) {
            this.calories = newCalories;
        }
        else {
            throw new IllegalArgumentException("Invalid calorie value");
        }
    }

    public void setBaseUnit(Unit newUnit) throws IllegalArgumentException {
        if(newUnit != null) {
            this.baseUnit = newUnit;
        }
        else {
            throw new IllegalArgumentException("Invalid unit");
        }
    }

    public void setBaseQuantity(double newQuantity) throws IllegalArgumentException {
        if (newQuantity <= Constant.ENTRY_MAX_VALUE && newQuantity > Constant.ENTRY_MIN_VALUE) {

            this.baseQuantity = newQuantity;
        }
        else {
            throw new IllegalArgumentException("Invalid quantity");
        }
    }

    private void setDBKey(int dbkey) throws IllegalArgumentException {
        if(dbkey >= 0) {
            this.edibleID = dbkey;
        }
        else {
            throw new IllegalArgumentException("Invalid DB key");
        }
    }

    public void setName(String name) throws IllegalArgumentException {
        if(name != null && !name.equals("")) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public void setPhotoBytes(byte[] newPhoto) throws IllegalArgumentException {
        if(newPhoto == null || newPhoto.length >= MIN_BYTE_LENGTH_PER_PHOTO) {
            this.photoBytes = newPhoto;
        }
        else {
            throw new IllegalArgumentException("Invalid photo");
        }
    }

    public void setPhoto(String filename) throws IllegalArgumentException {
        if(filename != null && !filename.equals("")) {
            this.photo = filename;
        }
        else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public String getDescription() {
        return this.description;
    }
    
    public double getCalories() {
        return this.calories;
    }

    public String getName() {
        return this.name;
    }

    public byte[] getPhotoBytes() {
        return this.photoBytes;
    }

    public String getPhoto()
    {
        return this.photo;
    }

    public Edible.Unit getUnit() {
        return this.baseUnit;
    }

    public double getQuantity() {
        return this.baseQuantity;
    }

    public int getDbkey() {
        return this.edibleID;
    }
}