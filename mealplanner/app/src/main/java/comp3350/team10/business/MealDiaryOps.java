package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
//import java.time.LocalDate;

public class MealDiaryOps {
    private Calendar listDate;
    private DataAccessStub db;
    private LinkedList<ListItem> todayFoodList;
    private Integer calorieGoal;
    private Integer calorieConsumed;
    private Integer calorieExercise;
    private Integer calorieNet;
    private boolean dataReady;

    public MealDiaryOps(){
        dataReady = false;
        this.listDate = Calendar.getInstance();
        calorieGoal = new Integer(-1);
        calorieConsumed = new Integer(-1);
        calorieExercise = new Integer(0);
        calorieNet = new Integer(-1);
        db = new DataAccessStub();
        db.open("someDB");
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    private void pullDBdata(){
        ArrayList<ListItem> dbFetch = db.getFoodLog(listDate);
        todayFoodList = new LinkedList<ListItem>();
        todayFoodList.addAll(dbFetch);
        calorieGoal = db.getCalorieGoal();
    }

    public void addToDiary(DiaryItem item) {
        db.addRecipeToLog(item);
    }

    public void nextDate(){
        dataReady = false;
        listDate.add(Calendar.DAY_OF_YEAR, 1);
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public void prevDate(){
        dataReady = false;
        listDate.add(Calendar.DAY_OF_YEAR, -1);
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public void setListDate(Calendar newDate){
        dataReady = false;
        listDate = newDate;
        pullDBdata();
        updateProgress();
        dataReady = true;
    }


    public Calendar getListDate(){
        return listDate;
    }

    public boolean isDataReady(){
        return dataReady;
    }

    public void setGoal(int newGoal){
        if(newGoal >= 0 && newGoal <= 9999)
        {
            calorieGoal = newGoal;
        }
    }

    public Integer getCalorieGoal(){
        return calorieGoal;
    }

    public Integer getCalorieConsumed(){
        return calorieConsumed;
    }

    public Integer getCalorieExercise(){
        return calorieExercise;
    }

    public Integer getCalorieNet(){
        return calorieNet;
    }

    public LinkedList<ListItem> getList() {
        return todayFoodList;
    }

    public void updateList(LinkedList<ListItem> newList){
        if(newList != null){
            todayFoodList = newList;
            updateProgress();
            //push to db after
        }
    }

    public void setCalorieGoal(Integer newGoal){
        if(calorieGoal == null){
            calorieGoal = new Integer(-1);
        }
        if(newGoal != null && newGoal >= 0 && newGoal <=9999) {
            calorieGoal = newGoal;
        }
    }

    public void setCalorieExercise(Integer newExercise){
        if(calorieExercise == null){
            calorieExercise = new Integer(-1);
        }
        if(newExercise != null && newExercise >= 0 && newExercise <=9999) {
            calorieExercise = newExercise;
        }
    }
    private void updateProgress(){
        sumCalories();
        netCalories();
    }

    private void sumCalories(){
        if(todayFoodList != null) {
            calorieConsumed = 0;
            for (int i = 0; i < todayFoodList.size(); i++) {
                calorieConsumed += ((Food) todayFoodList.get(i)).getCalories();
            }
        }
    }
    private void netCalories(){
        calorieNet = calorieGoal - (calorieConsumed - calorieExercise);
    }
}
