package comp3350.team10.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;
import comp3350.team10.objects.Constant;

public class DailyLog {
    private int date;                          //The date of this particular log    
    private ArrayList<Edible> edibleLog;       //The edibles present in the given log for a given date
    private int calorieGoal;                   //The calorie goal for a given date 
    private int userID;
    private int exerciseGoal;                  //The calorie goal for exercising for a given date
    private int exerciseActual;                //The calories burnt while exercising for a given date
    private int edibleCalories;                //The calorie total for a list of edibles for a given date

    public DailyLog() {
        this.date = -1;
        this.edibleLog = null;
        this.calorieGoal = -1;
        this.exerciseGoal = -1;
        this.exerciseActual = 0;
        this.edibleCalories = 0;
    }


    public DailyLog init(int date, ArrayList<Edible> log, int calGoal, int userID, int excGoal, int excActual) throws IOException {
        this.setDate(date);
        this.setEdibleList(log);
        this.serUserID(userID);
        this.setCalorieGoal(calGoal);
        this.setExerciseGoal(excGoal);
        this.setExerciseActual(excActual);

        return this;
    }

    private void setDate(int date) throws IOException {
        if(date >= 0) {
            this.date = date;
        }
        else {
            throw new IOException("Invalid date");
        }
    }

    private void setUserID(int newUserID) throws IOException{
        if(newUserID >= 0) {
            this.userID = newUserID;
        }
        else {
            throw new IOException("Invalid date");
        }
    }

    public int getUserID() {
        return this.userID;
    }

    public void setEdibleList(ArrayList<Edible> newLog) throws IOException {
        if (newLog != null && newLog.size() >= 0 && !newLog.contains(null)) {
            this.edibleLog = newLog;
            this.calculateEdibleCalories();
        }
        else {
            throw new IOException("Invalid edible log");
        }
    }

    public void setExerciseActual(Integer newExerciseActual) throws IOException {
        if (newExerciseActual != null && newExerciseActual >= Constant.ENTRY_MIN_VALUE && newExerciseActual <= Constant.ENTRY_MAX_VALUE) {
            this.exerciseActual = newExerciseActual;
        }
        else {
            throw new IOException("Invalid exercise actual value");
        }
    }

    public void setCalorieGoal(Integer newCalorieGoal) throws IOException {
        if (newCalorieGoal != null && newCalorieGoal >= Constant.ENTRY_MIN_VALUE && newCalorieGoal <= Constant.ENTRY_MAX_VALUE) {
            this.calorieGoal = newCalorieGoal;
        }
        else {
            throw new IOException("Invalid calorie goal");
        }
    }

    public void setExerciseGoal(Integer newExerciseGoal) throws IOException {
        if (newExerciseGoal != null && newExerciseGoal >= Constant.ENTRY_MIN_VALUE && newExerciseGoal <= Constant.ENTRY_MAX_VALUE) {
            this.exerciseGoal = newExerciseGoal;
        }
        else {
            throw new IOException("Invalid exercise goal");
        }
    }

    private void calculateEdibleCalories() throws IOException {
        int calculatedCalories = 0;

        for(int i = 0; i < this.edibleLog.size(); i++) {
            calculatedCalories += this.edibleLog.get(i).getCalories();
        }

        if(calculatedCalories >= 0 && calculatedCalories <= Constant.ENTRY_MAX_VALUE) {
            this.edibleCalories = calculatedCalories;
        }
        else {
            throw new IOException("invalid calorie actual value");
        }
    }

    public ArrayList<Edible> getEdibleList() {
        return this.edibleLog;
    }

    public int getExerciseActual() {
        return this.exerciseActual;
    }

    public int getExerciseGoal() {
        return this.exerciseGoal;
    }

    public int getCalorieGoal() {
        return this.calorieGoal;
    }

    public int getEdibleCalories() {
        return this.edibleCalories;
    }

    public int getDate() {
        return this.date;
    }
}