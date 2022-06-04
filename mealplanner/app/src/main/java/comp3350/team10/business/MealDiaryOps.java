package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;

import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;

public class MealDiaryOps {
    private Integer MAX_PROGRESS = 100;
    private Integer GOAL_LIMIT = 9999;
    private Integer MAX_EXCESS = 25;
    private Integer DATE_LIMIT = 2;
    private Integer INCREMENT = 1;
    private Integer DEFAULT = -1;

    private LinkedList<ListItem> todayFoodList = new LinkedList<ListItem>();
    private Calendar listDate = Calendar.getInstance();
    private DataAccessStub db = new DataAccessStub();
    private Integer calorieConsumed = DEFAULT;
    private Integer calorieExercise = DEFAULT;
    private Integer progressExcess = DEFAULT;
    private Integer progressBar = DEFAULT;
    private Integer calorieGoal = DEFAULT;
    private Integer calorieNet = DEFAULT;
    private boolean dataReady = false;

    public MealDiaryOps() {
        db.open("someDB");
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public MealDiaryOps(DataAccessStub db) { //dependency injectable constructor
        if(db != null) {
            this.db = db;
            this.db.open("someDB");
            pullDBdata();
            updateProgress();
            dataReady = true;
        }
    }

    private void pullDBdata() {
        ArrayList<ListItem> dbFetch = db.getFoodLog(listDate);
        todayFoodList.addAll(dbFetch);
        calorieGoal = db.getCalorieGoal();
        calorieExercise = 0; //fetch from db
    }

    public void addToDiary(Edible item) {
        db.addRecipeToLog(item);
    }

    public void nextDate() {
        dataReady = false;
        listDate.add(Calendar.DAY_OF_YEAR, INCREMENT);
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public void prevDate() {
        dataReady = false;
        listDate.add(Calendar.DAY_OF_YEAR, -INCREMENT);
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public void setListDate(Calendar newDate) {
        int diff = listDate.get(Calendar.YEAR) - newDate.get(Calendar.YEAR);
        if(diff <= DATE_LIMIT && diff >= -DATE_LIMIT) {
            dataReady = false;
            listDate = newDate;
            pullDBdata();
            updateProgress();
            dataReady = true;
        }
    }

    public void setCalorieGoal(Integer newGoal) {
        if (newGoal != null && newGoal >= 0 && newGoal <= GOAL_LIMIT) {
            calorieGoal = newGoal;
            //push to db
        }
    }

    public void setCalorieExercise(Integer newExercise) {
        if (newExercise != null && newExercise >= 0 && newExercise <= GOAL_LIMIT) {
            calorieExercise = newExercise;
            //push to db
        }
    }

    public void updateList(LinkedList<ListItem> newList) {
        if (newList != null) {
            todayFoodList = newList;
            updateProgress();
            //push to db after
        }
    }

    public Calendar getListDate() {
        return listDate;
    }

    public boolean isDataReady() {
        return dataReady;
    }

    public Integer getCalorieGoal() {
        return calorieGoal;
    }

    public Integer getCalorieConsumed() {
        return calorieConsumed;
    }

    public Integer getCalorieExercise() {
        return calorieExercise;
    }

    public Integer getCalorieNet() {
        return calorieNet;
    }

    public Integer getProgressBar(){
        return progressBar;
    }

    public Integer getProgressExcess(){
        return progressExcess;
    }

    public LinkedList<ListItem> getList() {
        return todayFoodList;
    }

    private void updateProgress() {
        sumCalories();
        netCalories();
        calcProgress();
    }

    private void calcProgress() {
        if (calorieNet > 0) {
            progressExcess = 0;
            progressBar = (calorieGoal - calorieNet) * MAX_PROGRESS / calorieGoal;
        } else {
            progressBar = MAX_PROGRESS;
            progressExcess = -calorieNet * MAX_PROGRESS / calorieGoal;
            if(progressExcess > MAX_EXCESS){
                progressExcess = MAX_EXCESS;
            }
        }
    }

    private void sumCalories() {
        if (todayFoodList != null) {
            calorieConsumed = 0;
            for (int i = 0; i < todayFoodList.size(); i++) {
                calorieConsumed += ((Edible) todayFoodList.get(i)).getCalories();
            }
        }
    }

    private void netCalories() {
        calorieNet = calorieGoal - (calorieConsumed - calorieExercise);
    }

    public void addByKey(int dbkey){
        Boolean found = false;
        LinkedList currList = null;
        Edible tempEdible = null;
        ListItem newItem = null;

        for(int i =0; i <3 && !found; i++){
            currList = db.getRecipe(i);

            for(int x = 0; x < currList.size() && !found; x++){
                if(((Edible) ((RecipeBookItem) currList.get(x)).getItem()).getDbkey() == dbkey){
                    found = true;
                    tempEdible = (Edible) ((RecipeBookItem) currList.get(x)).getItem();
                    tempEdible.setFragmentType(ListItem.FragmentType.diaryEntry);
                    newItem = tempEdible;
                }
            }
        }
        if(newItem != null){
            todayFoodList.add(todayFoodList.size()-1, newItem);
        }
        updateProgress();
    }
}
