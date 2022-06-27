package comp3350.team10.business;

import androidx.annotation.NonNull;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.ArrayList;

public class MealDiaryOps { //this needs to select the correct fragment
    private final static Integer MAX_PROGRESS = 100;    //Scales the progress bar (percentage)
    private final static Integer GOAL_LIMIT = 9999;     //The highest number of calories a user should aim for
    private final static Integer MAX_EXCESS = 100;      //How many calories a user can exceed their goal by
    private final static Integer DATE_LIMIT = 2;        //Limits the quantity of data saved by years
    private final static Integer INCREMENT = 1;         //Directional arrow increments

    //Database variables
    private DailyLog currLog;           //The food in the planner for the given day
    private Calendar logDate;           //The date the planner is set to
    private DataAccessStub db;          //Accesses the database

    //Progress bar variables
    private UserDataOps opUser;         //Buisness logic for handling the app's user
    private Integer progressExcess;     //Represents the overflow of calories on the progress bar
    private Integer progressBar;        //Represents the calories on the progress bar
    private int calorieNet;             //Represents the consumed calories - exercise calories burnt

    //dependency injectable constructor
    public MealDiaryOps(DataAccessStub db) {
        this.logDate = Calendar.getInstance();

        this.progressExcess = -1;
        this.progressBar = -1;
        this.calorieNet = -1;

        if (db != null) {
            this.db = db;
            this.opUser = new UserDataOps(db);
            this.pullDBdata();
            this.updateProgress();
        }    
    }


    private Integer calendarToInt(Calendar date) {
        return Integer.parseInt(String.valueOf(date.get(Calendar.YEAR)) + String.valueOf(date.get(Calendar.DAY_OF_YEAR)));
    }

    private void pullDBdata() {
        this.currLog = this.selectFoodLogByDate(logDate);
    }

    private DailyLog selectFoodLogByDate(Calendar date) { //creates a new one but it does not exist in the list so it breaks for days not already in list
        DailyLog currLog = this.db.searchFoodLogByDate(date); //search for food log

        try {
            if (currLog == null) { //If doesnt exist generate a new one
                currLog = new DailyLog();
                currLog.init(calendarToInt(date), emptyLog(), this.opUser.getUser().getCalorieGoal(), 0, 0);
                db.addLog(currLog);
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return currLog;
    }

    public void nextDate() {
        logDate.add(Calendar.DAY_OF_YEAR, INCREMENT);
        this.dateChangedUpdateList();
    }

    public void prevDate() {
        logDate.add(Calendar.DAY_OF_YEAR, -INCREMENT);
        this.dateChangedUpdateList();
    }

    public void setListDate(Calendar newDate) {
        Integer diff = logDate.get(Calendar.YEAR) - newDate.get(Calendar.YEAR);

        if (diff <= DATE_LIMIT && diff >= -DATE_LIMIT) { //if within 2 years
            this.logDate = newDate;
            this.dateChangedUpdateList();
        }
    }

    private void dateChangedUpdateList() { //update before you change
        this.pullDBdata();
        this.updateProgress();
    }

    public void setCalorieGoal(int newGoal) {
        try {
            if (newGoal >= 0 && newGoal <= GOAL_LIMIT) {
                this.currLog.setCalorieGoal(newGoal);
                db.setCalorieGoal(newGoal, logDate);
                this.updateProgress();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void setCalorieExercise(Integer newExercise) {
        try {
            if (newExercise != null && newExercise >= 0 && newExercise <= GOAL_LIMIT) {
                this.currLog.setExerciseActual(newExercise);
                db.setExerciseActual(newExercise, logDate);
                this.updateProgress();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void updateList(ArrayList<Edible> newList) {
        try {
            if (newList != null) {
                this.db.deleteLog(currLog);
                this.currLog.setEdibleList(newList);
                this.db.addLog(currLog);
                this.updateProgress();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public Calendar getListDate() {
        return this.logDate;
    }

    public int getCalorieGoal() {
        return this.currLog.getCalorieGoal();
    }

    public Integer getCalorieNet() {
        return this.calorieNet;
    }

    public Integer getProgressBar() {
        return this.progressBar;
    }

    public Integer getProgressExcess() {
        return this.progressExcess;
    }

    public ArrayList<Edible> getList() {
        return this.currLog.getEdibleList();
    }

    private void updateProgress() {
        this.netCalories();
        this.calcProgress();
    }

    private ArrayList<Edible> emptyLog() {
        ArrayList<Edible> empty = new ArrayList<Edible>();
        Food addLog = new Food();

        try {
            addLog.setFragmentType(ListItem.FragmentType.diaryAdd);
            empty.add(addLog);
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return empty;
    }

    private void calcProgress() {
        if (this.calorieNet > 0) {
            this.progressExcess = 0;
            this.progressBar = (this.currLog.getCalorieGoal() - this.calorieNet) * MAX_PROGRESS / this.currLog.getCalorieGoal();
        } else {
            this.progressBar = MAX_PROGRESS;
            this.progressExcess = -this.calorieNet * MAX_PROGRESS / this.currLog.getCalorieGoal();

            if (this.progressExcess > MAX_EXCESS) {
                this.progressExcess = MAX_EXCESS;
            }
        }
        if (this.progressBar < 0) {
            this.progressBar = 0;
        }
    }

    private void netCalories() {
        this.calorieNet = this.currLog.getCalorieGoal() - this.currLog.getExerciseActual();
    }

    public void addByKey(int dbkey) {
        Edible tempEdible = db.findEdibleByKey(dbkey);

        try {
            if (tempEdible != null) {
                tempEdible.setFragmentType(ListItem.FragmentType.diaryEntry);
                this.currLog.getEdibleList().add(this.currLog.getEdibleList().size() - 1, tempEdible);
                this.updateProgress();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}