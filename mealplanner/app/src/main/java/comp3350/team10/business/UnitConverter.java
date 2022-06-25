package comp3350.team10.business;

import comp3350.team10.objects.*;

public class UnitConverter {
    private final static Double factorGPerCup = 224.0;      //Ratio between g and cups
    private final static Double factorGPerOz = 28.0;        //Ratio between g and oz
    private final static Double factorGPerServing = 250.0;  //Ratio between g and serving
    private final static Double factorGPerTbsp = 14.0;      //Ratio between g and tbsp
    private final static Double factorGPerTsp = 5.0;        //Ratio between g and tsp
    private final static Double factorGPerMl = 1.0;         //Ratio between g and ml
    private final static Double factorGPerLiter = 1000.0;   //Ratio between g and liter

    private Edible.Unit prevUnit = Edible.Unit.serving; //The previous unit (cups, oz, g, serving, tbsp, tsp, ml, liter)
    private Edible.Unit newUnit = Edible.Unit.serving; //The current unit (cups, oz, g, serving, tbsp, tsp, ml, liter)
    private Double prevQuantity = -1.0; //Previous quantity
    private Double prevCalories = -1.0; //Previous calorie amount for the given quantity
    private Double newQuantity = -1.0;  //new quantity
    private Double newCalories = -1.0;  //New calorie amount for the given quantity
    private Double calsPerUnit = 0.0;   //The ratio of calories per unit

    public UnitConverter(Edible.Unit prevUnit, Integer prevQuantity, Integer prevCalories) {
        this.prevUnit = prevUnit;
        this.prevQuantity = prevQuantity.doubleValue();
        this.prevCalories = prevCalories.doubleValue();
    }

    public Integer getCalories(Edible.Unit newUnit, Integer newQuantity) {

        this.newQuantity = newQuantity.doubleValue();
        calculateCaloriesPerUnit();
        if (prevUnit != Edible.Unit.g) {
            convertCaloriesPerUnitToCaloriesPerGram();
        }

        if (newUnit == Edible.Unit.cups) {
            gTocup();
        } else if (newUnit == Edible.Unit.oz) {
            gTooz();
        } else if (newUnit == Edible.Unit.serving) {
            gToserving();
        } else if (newUnit == Edible.Unit.tbsp) {
            gTotbsp();
        } else if (newUnit == Edible.Unit.tsp) {
            gTotsp();
        } else if (newUnit == Edible.Unit.ml) {
            gToml();
        } else if (prevUnit == Edible.Unit.liter) {
            gToliter();
        } else {
            gTog();
        }

        return newCalories.intValue();
    }

    private void convertCaloriesPerUnitToCaloriesPerGram() {
        if (prevUnit == Edible.Unit.cups) {
            calsPerUnit = 1 / factorGPerCup * calsPerUnit;
        } else if (prevUnit == Edible.Unit.oz) {
            calsPerUnit = 1 / factorGPerOz * calsPerUnit;
        } else if (prevUnit == Edible.Unit.serving) {
            calsPerUnit = 1 / factorGPerServing * calsPerUnit;
        } else if (prevUnit == Edible.Unit.tbsp) {
            calsPerUnit = 1 / factorGPerTbsp * calsPerUnit;
        } else if (prevUnit == Edible.Unit.tsp) {
            calsPerUnit = 1 / factorGPerTsp * calsPerUnit;
        } else if (prevUnit == Edible.Unit.ml) {
            calsPerUnit = 1 / factorGPerMl * calsPerUnit;
        } else if (prevUnit == Edible.Unit.liter) {
            calsPerUnit = 1 / factorGPerLiter * calsPerUnit;
        }
        
        prevUnit = Edible.Unit.g;
        prevQuantity = 1.0;
    }

    private void calculateCaloriesPerUnit() {

        if (prevQuantity != 0) {
            calsPerUnit = prevCalories / prevQuantity;
        } else {
            calsPerUnit = 0.0;
        }

    }

    private void gTog() {
        newCalories = newQuantity * calsPerUnit;
    }

    private void gTocup() {
        newCalories = newQuantity * factorGPerCup * calsPerUnit;

    }

    private void gTooz() {
        newCalories = newQuantity * factorGPerOz * calsPerUnit;

    }

    private void gToserving() {
        newCalories = newQuantity * factorGPerServing * calsPerUnit;

    }

    private void gTotbsp() {
        newCalories = newQuantity * factorGPerTbsp * calsPerUnit;

    }

    private void gTotsp() {
        newCalories = newQuantity * factorGPerTsp * calsPerUnit;

    }

    private void gToml() {
        newCalories = newQuantity * factorGPerMl * calsPerUnit;

    }

    private void gToliter() {
        newCalories = newQuantity * factorGPerLiter * calsPerUnit;

    }
}