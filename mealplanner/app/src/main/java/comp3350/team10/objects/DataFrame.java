package comp3350.team10.objects;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class DataFrame {
    public enum DataType {ConsumedCalories, NetCalories, ExerciseCalories, Weight};
    public enum Span {Week, Month, ThreeMonth, SixMonth, Year, All};
    public static final int[] xAxisLimits = {-7,-28,-74,-128,-350,-700};
    private ArrayList<Double> data;
    private DataType dataType;
    private Span span;
    private int size;
    private double trendPointA;
    private double trendPointB;

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

    public int size(){
        return this.size;
    }

    public void setData(ArrayList<Double> data) throws NullPointerException{
        if( data != null ){
            if ( data.size() > 0 ) {
                this.size = data.size();
                this.data = data;
                calculateTrend();
            }
            else {
                throw new ExceptionInInitializerError("DataFrame ArrayList<Double> cannot be empty");
            }
        }
        else{
            throw new NullPointerException("DataFrame ArrayList<Double> cannot be null");
        }
    }

    public ArrayList<Double> getData(){
        return this.data;
    }

    private void calculateTrend(){
        SimpleRegression regression = new SimpleRegression(true);
        for(int i = 0; i < this.size; i++) {
            regression.addData(i-this.size, this.data.get(i));
        }
        this.trendPointB = regression.predict(0);
        this.trendPointA = regression.predict(xAxisLimits[this.span.ordinal()]);
    }

    public double getTrendPointA(){
        return this.trendPointA;
    }

    public double getTrendPointB(){
        return this.trendPointB;
    }

}
