package comp3350.team10.business;

import comp3350.team10.objects.Edible;

public class UnitConverter {
    // cups, oz, g, serving, tbsp, tsp, ml, liter
    private final static double[] factor = {224.0, 28.0, 1.0, 250.0, 14.0, 5.0, 1.0, 1000.0};
    private Double calsPerGram = 0.0;   //The ratio of calories per unit

    public UnitConverter() {

    }

    public double convert(Edible.Unit prevUnit, double prevQuantity, double prevCalories, Edible.Unit newUnit, double newQuantity) throws IllegalArgumentException {
        if (prevQuantity <= 0.0 || newQuantity <= 0) {
            throw new IllegalArgumentException("UnitConverter convert quantity must be > 0");
        }
        if (prevCalories < 0) {
            throw new IllegalArgumentException("UnitConverter convert base calories must be >= 0");
        }
        this.normalizeToPerGram(prevUnit, prevQuantity, prevCalories);
        return getNewCalories(newUnit, newQuantity);
    }

    private void normalizeToPerGram(Edible.Unit prevUnit, double prevQuantity, double prevCalories) {
        this.calsPerGram = (prevCalories / prevQuantity) * (1.0 / factor[prevUnit.ordinal()]);
    }

    private double getNewCalories(Edible.Unit newUnit, double newQuantity) {
        double result = 0;

        result = newQuantity * this.calsPerGram * factor[newUnit.ordinal()];

        return result;
    }
}