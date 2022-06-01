public abstract class Ingredient {
    private float quantity;
    private String units;
    
    public Ingredient(float quantity, String units) {
        this.quantity = quantity;
        this.units = units;
    }

    public void changeQty(float newQty) {
        this.quantity = newQty;
    }

    public float getQty() {
        return this.quantity;
    } 

    public void changeUnits(String newUnits) {
        this.units = newUnits;
    }

    public String getUnits() {
        return this.units;
    }
}
