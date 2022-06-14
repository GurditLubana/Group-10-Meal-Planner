package comp3350.team10.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;

public class DailyLog { //Sort of like the user's logs from the database
    private ListItem.FragmentType fragmentType; //How it should appear on recycler views
    private ArrayList<Edible> log;              //The edibles present in the given log
    private Integer excActual;                  //The calories burnt while exercising
    private Integer excGoal;                    //The original calorie goal for exercising
    private Integer calGoal;                    //Calculates the difference between the goal and actual exercise calories
    private Integer date;                       //The date

    public DailyLog(Integer date, Integer calGoal, Integer excGoal, Integer excActual, ArrayList<Edible> log) {
        this.log = log;
        this.date = date;
        this.calGoal = calGoal;
        this.excGoal = excGoal;
        this.fragmentType = null;
        this.excActual = excActual;
    }

    public DailyLog() {
        this.log = null;
        this.date = null;
        this.calGoal = null;
        this.excGoal = null;
        this.fragmentType = null;
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
        if (newLog != null && newLog.size() > 0) {
            this.log = newLog;
            result = true;
        }
        return result;
    }

    public boolean setExcActual(Integer newExcActual) {
        boolean result = false;
        if (newExcActual != null && newExcActual >= 0 && newExcActual <= 9999) {
            this.excActual = newExcActual;
            result = true;
        }
        return result;
    }

    public boolean setCalGoal(Integer newCalGoal) {
        boolean result = false;
        if (newCalGoal != null && newCalGoal >= 0 && newCalGoal <= 9999) {
            this.calGoal = newCalGoal;
            result = true;
        }
        return result;
    }

    public boolean setExcGoal(Integer newExcGoal) {
        boolean result = false;
        if (newExcGoal != null && newExcGoal >= 0 && newExcGoal <= 9999) {
            this.excGoal = newExcGoal;
            result = true;
        }
        return result;
    }

    public boolean setFragmentType(ListItem.FragmentType fragmentType) {
        boolean result = false;
        if (fragmentType != null) {
            this.fragmentType = fragmentType;
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

    public ListItem.FragmentType getFragmentType() {
        return this.fragmentType;
    }
}