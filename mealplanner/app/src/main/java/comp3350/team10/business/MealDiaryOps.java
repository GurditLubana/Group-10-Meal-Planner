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
    private Integer calorieConsumed;    //Represents the number of calories currently consumed
    private Integer calorieExercise;    //Represents the calories burnt through exercise
    private Integer progressExcess;     //Represents the overflow of calories on the progress bar
    private Integer progressBar;        //Represents the calories on the progress bar
    private Integer calorieGoal;        //Represents the calorie goal
    private Integer calorieNet;         //Represents the consumed calories - exercise calories burnt

    //dependency injectable constructor
    public MealDiaryOps(DataAccessStub db) {
        this.logDate = Calendar.getInstance();

        this.calorieConsumed = -1;
        this.calorieExercise = -1;
        this.progressExcess = -1;
        this.progressBar = -1;
        this.calorieGoal = -1;
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
        this.calorieExercise = currLog.getExcActual();
        this.calorieGoal = currLog.getCalGoal();
    }

    private DailyLog selectFoodLogByDate(Calendar date) { //creates a new one but it does not exist in the list so it breaks for days not already in list
        DailyLog currLog = this.db.searchFoodLogByDate(date); //search for food log

        if (currLog == null) { //If doesnt exist generate a new one
            currLog = new DailyLog();
            currLog.init(calendarToInt(date), this.opUser.getUser().getCalorieGoal(), 0, 0, emptyLog());
            db.addLog(currLog);
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

    public void setCalorieGoal(Integer newGoal) {
        if (newGoal != null && newGoal >= 0 && newGoal <= GOAL_LIMIT) {
            this.calorieGoal = newGoal;
            db.setCalorieGoal(newGoal, logDate);
            this.updateProgress();
        }
    }

    public void setCalorieExercise(Integer newExercise) {
        if (newExercise != null && newExercise >= 0 && newExercise <= GOAL_LIMIT) {
            this.calorieExercise = newExercise;
            db.setExerciseActual(newExercise, logDate);
            this.updateProgress();
        }
    }

    public void updateList(ArrayList<Edible> newList) {
        if (newList != null) {
            this.db.deleteLog(currLog);
            this.currLog.setFoodList(newList);
            this.db.addLog(currLog);
            this.updateProgress();
        }
    }

    public Calendar getListDate() {
        return this.logDate;
    }

    public Integer getCalorieGoal() {
        return this.calorieGoal;
    }

    public Integer getCalorieConsumed() {
        return this.calorieConsumed;
    }

    public Integer getCalorieExercise() {
        return this.calorieExercise;
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
        return this.currLog.getFoodList();
    }

    private void updateProgress() {
        this.sumCalories();
        this.netCalories();
        this.calcProgress();
    }

    private ArrayList<Edible> emptyLog() {
        ArrayList<Edible> result = new ArrayList<Edible>();
        Food addLog = new Food();
        addLog.init("uielement", 0, 0, ListItem.FragmentType.diaryAdd, Edible.Unit.cups, 1, 0);

        result.add(addLog);

        return result;
    }

    private void calcProgress() {
        if (this.calorieNet > 0) {
            this.progressExcess = 0;
            this.progressBar = (this.calorieGoal - this.calorieNet) * MAX_PROGRESS / this.calorieGoal;
        } else {
            this.progressBar = MAX_PROGRESS;
            this.progressExcess = -this.calorieNet * MAX_PROGRESS / this.calorieGoal;

            if (this.progressExcess > MAX_EXCESS) {
                this.progressExcess = MAX_EXCESS;
            }
        }
        if (this.progressBar < 0) {
            this.progressBar = 0;
        }
    }

    private void sumCalories() {
        if (this.currLog != null) {
            this.calorieConsumed = 0;

            for (int i = 0; i < this.currLog.getFoodList().size(); i++) {
                this.calorieConsumed += this.currLog.getFoodList().get(i).getCalories();
            }
        }
    }

    private void netCalories() {
        this.calorieNet = this.calorieGoal - (this.calorieConsumed - this.calorieExercise);
    }

    public void addByKey(int dbkey) {
        Edible tempEdible = db.findEdibleByKey(dbkey);

        if (tempEdible != null) {
            tempEdible.setFragmentType(ListItem.FragmentType.diaryEntry);
            this.currLog.getFoodList().add(this.currLog.getFoodList().size() - 1, tempEdible);
        }

        this.updateProgress();
    }
}