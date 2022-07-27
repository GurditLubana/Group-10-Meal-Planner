package comp3350.team10.objects;

public class Edible {
    public enum Unit {cups, oz, g, serving, tbsp, tsp, ml, liter} //All possible units for a given edible

    //Edible details
    private int edibleID;                       //This edibles database key
    private String name;                        //The name
    private String description;                 //A brief description
    private double baseQuantity;                //The quantity
    private Unit baseUnit;                      //The unit of the given quantity

    //Nutritional content
    private double calories;                    //The calories for a given edible
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
    private String photo;                       //The image path for a given edible

    public Edible() {
        this.edibleID = -1;
        this.name = null;
        this.description = null;
        this.baseQuantity = -1;
        this.baseUnit = null;

        this.calories = 0;
        this.protein = 0;
        this.carbs = 0;
        this.fat = 0;

        this.isAlcoholic = false;
        this.isSpicy = false;
        this.isVegan = false;
        this.isVegetarian = false;
        this.isGlutenFree = false;

        this.isCustom = false;
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
        Validator.validStringInputatLeastZero(newDescription, "Edible class - setDescription");
        this.description = newDescription;
    }

    public void setProtein(int newProtein) throws IllegalArgumentException {
        Validator.atLeastZero(newProtein, "Edible class - setProtein");
        this.protein = newProtein;
    }

    public int getProtein() {
        return this.protein;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public void setFat(int newFat) throws IllegalArgumentException {
        Validator.atLeastZero(newFat, "Edible class - setFat");
        this.fat = newFat;
    }

    public int getFat() {
        return this.fat;
    }

    public void setCarbs(int newCarbs) throws IllegalArgumentException {
        Validator.atLeastZero(newCarbs, "Edible class - setCarbs");
        this.carbs = newCarbs;
    }

    public void setCalories(double newCalories) throws IllegalArgumentException {
        Validator.zeroAtMinimum(newCalories, "Edible class - setCalories");
        this.calories = newCalories;
    }

    public void setBaseUnit(Unit newUnit) throws IllegalArgumentException {
        if (newUnit != null) {
            this.baseUnit = newUnit;
        } else {
            throw new IllegalArgumentException("Unit cannot be null: Edible class - setBaseUnit");
        }
    }

    public void setBaseQuantity(double newQuantity) throws IllegalArgumentException {
        Validator.atLeastOne(newQuantity, "Edible class - setBaseQuantity");
        this.baseQuantity = newQuantity;
    }

    public void setDBKey(int dbkey) throws IllegalArgumentException {
        Validator.atLeastZero(dbkey, "Edible class - setDBKey");
        this.edibleID = dbkey;
    }

    public void setName(String newName) throws IllegalArgumentException {
        Validator.validStringInputatLeastOne(newName, "Edible class - setName");
        this.name = newName;
    }

    public void setPhoto(String newFilename) throws IllegalArgumentException {
        Validator.validStringInputatLeastOne(newFilename, "Edible class - setPhoto");
        this.photo = newFilename;
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

    public Edible.Unit getUnit() {
        return this.baseUnit;
    }

    public double getQuantity() {
        return this.baseQuantity;
    }

    public int getDbkey() {
        return this.edibleID;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Edible clone() throws IllegalArgumentException {
        Edible copy = new Edible();

        copy.initDetails(this.edibleID, this.name, this.description, this.baseQuantity, this.baseUnit);
        copy.initNutrition(this.calories, this.protein, this.carbs, this.fat);
        copy.initCategories(this.isAlcoholic, this.isSpicy, this.isVegan, this.isVegetarian, this.isGlutenFree);
        copy.initMetadata(this.isCustom, this.photo);

        return copy;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Edible) {
            int otherID = ((Edible) other).getDbkey();
            return (this.edibleID == otherID);
        }
        return false;
    }
}
