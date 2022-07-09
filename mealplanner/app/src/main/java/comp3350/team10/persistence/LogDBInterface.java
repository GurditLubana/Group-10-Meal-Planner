package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

public interface LogDBInterface {
    public DailyLog searchFoodLogByDate(Calendar date, int userID);
    public EdibleLog findEdibleByKey(int dbkey, boolean isCustom);
    public void addLog(DailyLog newLog, int userID);
    public void deleteLog(DailyLog delLog, int userID);
    public void setExerciseActual(double newExercise, DailyLog currLog, int userID);
    public void setLogCalorieGoal(int userID, double goal, Calendar date);
    public void setLogExerciseGoal(int userID, double goal, Calendar date);
    public ArrayList<Double> getDataFrame(DataFrame.DataType type, int days);
}
