package comp3350.team10.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;
import comp3350.team10.objects.Constant;

public class DailyLog {                 //The user's logs from the database
    private ArrayList<Edible> log;              //The edibles present in the given log
    private Integer excActual;                  //The calories burnt while exercising
    private Integer excGoal;                    //The original calorie goal for exercising
    private Integer calGoal;                    //Calculates the difference between the goal and actual exercise calories
    private Integer date;                       //The date

    public DailyLog() {
        this.log = null;
        this.date = null;
        this.calGoal = null;
        this.excGoal = null;
        this.excActual = null;
    }


    public boolean init(Integer date, Integer calGoal, Integer excGoal, Integer excActual, ArrayList<Edible> log) {
        boolean result = false;

        if (date != null && date >= 0
                && setCalGoal(calGoal)
                && setExcGoal(excGoal)
                && setExcActual(excActual)
                && setFoodList(log)) {

            this.date = date;
            result = true;
        }

        return result;
    }

    public boolean setFoodList(ArrayList<Edible> newLog) {
        boolean result = false;

        if (newLog != null && newLog.size() > 0 && !newLog.contains(null)) {
            this.log = newLog;
            result = true;
        }

        return result;
    }

    public boolean setExcActual(Integer newExcActual) {
        boolean result = false;

        if (newExcActual != null && newExcActual >= Constant.ENTRY_MIN_VALUE && newExcActual <= Constant.ENTRY_MAX_VALUE) {
            this.excActual = newExcActual;
            result = true;
        }

        return result;
    }

    public boolean setCalGoal(Integer newCalGoal) {
        boolean result = false;

        if (newCalGoal != null && newCalGoal >= Constant.ENTRY_MIN_VALUE && newCalGoal <= Constant.ENTRY_MAX_VALUE) {
            this.calGoal = newCalGoal;
            result = true;
        }

        return result;
    }

    public boolean setExcGoal(Integer newExcGoal) {
        boolean result = false;

        if (newExcGoal != null && newExcGoal >= Constant.ENTRY_MIN_VALUE && newExcGoal <= Constant.ENTRY_MAX_VALUE) {
            this.excGoal = newExcGoal;
            result = true;
        }

        return result;
    }

    public ArrayList<Edible> getFoodList() {
        return this.log;
    }

    public Integer getExcActual() {
        return this.excActual;
    }

    public Integer getExcGoal() {
        return this.excGoal;
    }

    public Integer getCalGoal() {
        return this.calGoal;
    }

    public Integer getDate() {
        return this.date;
    }
}