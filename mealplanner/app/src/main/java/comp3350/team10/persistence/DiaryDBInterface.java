package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.Edible;

public interface DiaryDBInterface {
    public final static int GOAL_LIMIT = 9999;         //Goal limit (standalone variable as per MealDiaryLog)
    public ArrayList<Edible> getFoodList(Calendar date);
        
    public void addLog(Edible newEdible);
    public void deleteLog(Edible delEdible);
    public void updateLog(Edible modEdible);
}
