package comp3350.team10.objects;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class DataFrame {
    public enum DataType {ConsumedCalories, NetCalories, ExerciseCalories, Weight}

    public enum Span {Week, Month, ThreeMonth, SixMonth, Year, All}

    public static final int[] numDays = {7, 28, 84, 168, 336, 672};
    private SimpleRegression regression;
    private ArrayList<Double> data;
    private double trendPointA;
    private double trendPointB;
    private DataType dataType;
    private Span span;
    private int size;
    private double average = 0.0;
    private double currVal = 0.0;
    private double maxVal = 0.0;
    private double minVal = 999999999.0;
    private double progress = 0.0;

    public DataFrame(DataType dataType, Span span) throws NullPointerException {
        if (dataType != null) {
            if (span != null) {
                this.dataType = dataType;
                this.span = span;
                this.data = new ArrayList<Double>();
            } else {
                throw new NullPointerException("DataFrame Span cannot be null");
            }
        } else {
            throw new NullPointerException("DataFrame DataType cannot be null");
        }
    }


    public int size() {
        return this.size;
    }

    public void setData(ArrayList<Double> data) throws NullPointerException {
        if (data != null) {
            this.size = data.size();
            this.data = data;
            this.regression = new SimpleRegression(true);
            if (this.data.size() > 1) {
                for (int i = 0; i < this.size; i++) {
                    if (this.data.get(i) != null) {
                        this.currVal = this.data.get(i);
                        this.regression.addData(i - this.size, this.currVal);
                        this.average += this.currVal;
                        if (this.currVal > this.maxVal) {
                            this.maxVal = this.currVal;
                        }
                        if (this.currVal < this.minVal) {
                            this.minVal = this.currVal;
                        }
                    }
                }
                this.average = this.average / this.size();
                this.progress = this.average / this.maxVal;
            }
            calculateTrend();
        } else {
            throw new NullPointerException("DataFrame ArrayList<Double> cannot be null");
        }
    }

    public ArrayList<Double> getData() {
        return (ArrayList<Double>) this.data.clone();
    }

    private void calculateTrend() {
        if (this.data.size() > 1) {
            this.trendPointB = regression.predict(0);
            this.trendPointA = regression.predict(-numDays[this.span.ordinal()]);
        } else {

            this.trendPointB = 0;
            this.trendPointA = 0;
        }
    }

    public Span getSpan() {
        return this.span;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public double getTrendPointA() {
        return this.trendPointA;
    }

    public double getTrendPointB() {
        return this.trendPointB;
    }

    public double getAverage() {
        return this.average;
    }

    public double getMaxVal() {
        return maxVal;
    }

    public double getMinVal() {
        return minVal;
    }

    public double getProgress() {
        return progress;
    }
}