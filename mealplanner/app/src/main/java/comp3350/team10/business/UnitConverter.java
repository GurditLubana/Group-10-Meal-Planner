package comp3350.team10.business;

import comp3350.team10.objects.ListItem;

public class UnitConverter {
    private ListItem.Unit prevUnit = ListItem.Unit.serving;
    private ListItem.Unit newUnit = ListItem.Unit.serving;
    private Integer prevQuantity = -1;
    private Integer prevCalories = -1;
    private Integer newQuantity = -1;
    private Integer newCalories = -1;

    public UnitConverter(ListItem.Unit prevUnit, Integer prevQuantity, Integer prevCalories){
        this.prevUnit = prevUnit;
        this.prevQuantity = prevQuantity;
        this.prevCalories = prevCalories;
    }

    public Integer getCalories(ListItem.Unit newUnit, Integer newQuantity){
        Integer result = -1;


        return result;
    }
}
