package comp3350.team10.business;

import java.util.Calendar;
import java.util.LinkedList;

import comp3350.team10.objects.Edible;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.DataAccessStub;

public class DailyProgressOps {

    private LinkedList<Integer> currentProgress; //The food in the planner for the given day
    private Calendar listDate;
    private DBSelector db;

    public DailyProgressOps(DBSelector db) {
        this.listDate = Calendar.getInstance();

        if (db != null) {
            this.db = db;
            this.pullDBdata();
        }
    }

    private void pullDBdata() {
        //this.currentProgress = new LinkedList<Integer>(db.getFoodList(listDate));
        //this.calorieExercise = db.getExerciseActual();
        //this.calorieGoal = db.getCalorieGoal();
    }



}
