package comp3350.team10.business;

import comp3350.team10.objects.ListItem;

public class UnitConverter {
    private ListItem.Unit prevUnit = ListItem.Unit.serving;
    private ListItem.Unit newUnit = ListItem.Unit.serving; //cups, oz, g, serving, tbsp, tsp, ml, liter
    private Double factorGPerCup = 224.0;
    private Double factorGPerOz = 28.0;
    private Double factorGPerServing = 250.0;
    private Double factorGPerTbsp = 14.0;
    private Double factorGPerTsp = 5.0;
    private Double factorGPerMl = 1.0;
    private Double factorGPerLiter = 1000.0;
    private Double prevQuantity = -1.0;
    private Double prevCalories = -1.0;
    private Double newQuantity = -1.0;
    private Double newCalories = -1.0;
    private Double calsPerUnit = 0.0;
    private Double newFactor = 0.0;

    public UnitConverter(ListItem.Unit prevUnit, Integer prevQuantity, Integer prevCalories){
        this.prevUnit = prevUnit;
        this.prevQuantity = prevQuantity.doubleValue();
        this.prevCalories = prevCalories.doubleValue();
    }

    public Integer getCalories(ListItem.Unit newUnit, Integer newQuantity){

        this.newQuantity = newQuantity.doubleValue();
        calculateCaloriesPerUnit();
        if(prevUnit != ListItem.Unit.g){
           convertCaloriesPerUnitToCaloriesPerGram();
        }

        if( newUnit == ListItem.Unit.cups ){
            gTocup();
        }
        else if( newUnit == ListItem.Unit.oz ){
            gTooz();
        }
        else if( newUnit == ListItem.Unit.serving ){
            gToserving();
        }
        else if( newUnit == ListItem.Unit.tbsp ){
            gTotbsp();
        }
        else if( newUnit == ListItem.Unit.tsp ){
            gTotsp();
        }
        else if( newUnit == ListItem.Unit.ml ){
            gToml();
        }
        else if( prevUnit == ListItem.Unit.liter){
            gToliter();
        }
        else {
            gTog();
        }

        return newCalories.intValue();
    }

    private void convertCaloriesPerUnitToCaloriesPerGram(){
        if( prevUnit == ListItem.Unit.cups ){
            calsPerUnit = 1/factorGPerCup * calsPerUnit;
        }
        else if( prevUnit == ListItem.Unit.oz ){
            calsPerUnit = 1/factorGPerOz * calsPerUnit;
        }
        else if( prevUnit == ListItem.Unit.serving ){
            calsPerUnit = 1/factorGPerServing * calsPerUnit;
        }
        else if( prevUnit == ListItem.Unit.tbsp ){
            calsPerUnit = 1/factorGPerTbsp * calsPerUnit;
        }
        else if( prevUnit == ListItem.Unit.tsp ){
            calsPerUnit = 1/factorGPerTsp * calsPerUnit;
        }
        else if( prevUnit == ListItem.Unit.ml ){
            calsPerUnit = 1/factorGPerMl * calsPerUnit;
        }
        else if( prevUnit == ListItem.Unit.liter){
            calsPerUnit = 1/factorGPerLiter * calsPerUnit;
        }
        else {
            //already in calories per gram
        }
        prevUnit = ListItem.Unit.g;
        prevQuantity = 1.0;
    }

    private void calculateCaloriesPerUnit(){
        calsPerUnit = prevCalories/prevQuantity;
    }

    private void gTog(){
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
