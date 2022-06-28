package comp3350.team10.objects;

public class DataFrame {
    public enum chartType {PIE, BAR, LINE, SCATTER};
    private chartType myType = null;

    public DataFrame(){
        this.myType = chartType.LINE;
    }

    public chartType getChartType(){
        return myType;
    }
}
