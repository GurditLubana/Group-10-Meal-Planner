package comp3350.team10.objects;

import java.util.ArrayList;

public class DataFrame {
    public enum DataType {ConsumedCalories, NetCalories, ExerciseCalories, Weight};
    public enum Span {Week, Month, ThreeMonth, SixMonth, Year, All};
    private ArrayList<Double> data;
    private DataType dataType;
    private Span span;

    public DataFrame(DataType dataType, Span span) throws NullPointerException{
        if( dataType != null ){
            if(span != null){
                this.dataType = dataType;
                this.span = span;
            }
            else{
                throw new NullPointerException("DataFrame Span cannot be null");
            }
        }
        else{
            throw new NullPointerException("DataFrame DataType cannot be null");
        }
    }

    public Span getSpan(){
        return this.span;
    }

    public DataType getDataType(){
        return this.dataType;
    }

    public void setData(ArrayList<Double> data) throws NullPointerException{
        if( data != null ){

        }
        else{
            throw new NullPointerException("DataFrame ArrayList<Double> cannot be null");
        }
    }
}
