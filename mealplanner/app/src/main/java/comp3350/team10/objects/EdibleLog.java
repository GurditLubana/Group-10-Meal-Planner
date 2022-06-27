package comp3350.team10.objects;

import java.io.IOException;

public class EdibleLog {
    private Edible edibleEntry;
    private int quantity;
    private Edible.Unit unit;

    public EdibleLog() {

    }


    public void init(Edible edibleEntry, int quantity, Edible.Unit unit) throws IOException{
        this.setEdibleEntry(edibleEntry);
        this.setQuantity(quantity);
        this.setUnit(unit);
    }

    public void setEdibleEntry(Edible newEdible) throws IOException {
        if(newEdible != null && newEdible.getCalories() != -1 && newEdible.getProtein() != -1 && newEdible.getCarbs() != -1 
                && newEdible.getFat() != -1) {
            this.edibleEntry = newEdible;
        }
        else {
            throw new IOException("Invalid log edible");
        }
    }

    public void setQuantity(int newQuantity) throws IOException {
        if(newQuantity > 0 && newQuantity <= Constant.ENTRY_MAX_VALUE) {
            this.quantity = newQuantity;
        }
        else {
            throw new IOException("Invalid log quantity");
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

    public Edible getEdibleEntry() {
        return this.edibleEntry;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Edible.Unit getUnit() {
        return this.unit;
    }
}
