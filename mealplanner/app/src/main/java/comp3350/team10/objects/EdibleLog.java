package comp3350.team10.objects;

public class EdibleLog extends Edible {
    private final UnitConverter converter;    //Used to calculate the actual calories based on the edibles base factors

    private final Unit baseUnit;              //The base unit of an edible
    private final double baseCalories;        //The base calories of a edible
    private final double baseQuantity;        //The base quantity of a edible

    private double quantity;            //The actual quantity of the edible consumed
    private Edible.Unit unit;           //The actual unit of the edible consumed
    private double calories;            //The actual calculated calories of the edible consumed

    public EdibleLog(Edible edible) throws IllegalArgumentException {
        super();

        this.converter = new UnitConverter();
        this.baseUnit = edible.getUnit();
        this.baseQuantity = edible.getQuantity();
        this.baseCalories = edible.getCalories();

        this.initDetails(edible.getDbkey(), edible.getName(), edible.getDescription(), edible.getQuantity(), edible.getUnit());
        this.initNutrition(edible.getCalories(), edible.getProtein(), edible.getCarbs(), edible.getFat());
        this.initCategories(edible.getIsAlcoholic(), edible.getIsSpicy(), edible.getIsVegan(), edible.getIsVegetarian(), edible.getIsGlutenFree());
        this.initMetadata(edible.getIsCustom(), edible.getPhoto());

        try {
            this.init(edible.getQuantity(), edible.getUnit());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Overflow occurred when calculating calories: EdibleLog - constructor");
        }
    }


    public EdibleLog init(double quantity, Edible.Unit unit) throws Exception {
        this.setQuantity(quantity);
        this.setUnit(unit);
        this.setCalories();

        return this;
    }

    public void setQuantity(double newQuantity) throws IllegalArgumentException {
        Validator.atLeastOne(newQuantity, "EdibleLog - setQuantity");
        this.quantity = newQuantity;
    }

    public void setCalories() throws Exception {
        this.calories = this.converter.convert(this.baseUnit, this.baseQuantity, this.baseCalories, this.unit, this.quantity);
    }

    public void setUnit(Edible.Unit newUnit) throws IllegalArgumentException {
        if (newUnit != null) {
            this.unit = newUnit;
        } else {
            throw new IllegalArgumentException("Unit cannot be null: EdibleLog - setUnit");
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

    public EdibleLog clone() throws IllegalArgumentException {
        EdibleLog copy = new EdibleLog(this);
        copy.initDetails(this.getDbkey(), this.getName(), this.getDescription(), super.getQuantity(), super.getUnit());

        return copy;
    }
}