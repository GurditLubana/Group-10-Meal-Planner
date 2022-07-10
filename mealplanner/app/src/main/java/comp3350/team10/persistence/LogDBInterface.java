package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

public interface LogDBInterface {
    public DailyLog searchFoodLogByDate(int userID, Calendar date);
    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom);
    public void replaceLog(int userID, DailyLog newLog);
    public void setExerciseActual(int userID, double newValue, Calendar date);
    public void setLogCalorieGoal(int userID, double newValue, Calendar date);
    public void setLogExerciseGoal(int userID, double newValue, Calendar date);
    public ArrayList<Double> getDataFrame(DataFrame.DataType type, int days);
}
