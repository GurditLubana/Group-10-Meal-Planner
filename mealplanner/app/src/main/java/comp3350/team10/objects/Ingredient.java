package comp3350.team10.objects;

public abstract class Ingredient {
    private double quantity;
    private String units;
    
    public Ingredient(double quantity, String units) {
        this.quantity = quantity;
        this.units = units;
    }

    public void changeQty(double newQty) {
        this.quantity = newQty;
    }

    public double getQty() {
        return this.quantity;
    } 

    public void changeUnits(String newUnits) {
        this.units = newUnits;
    }

    public String getUnits() {
        return this.units;
    }
}
