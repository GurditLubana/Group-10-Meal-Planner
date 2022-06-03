package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;

public class MealDiaryOps {
    private Calendar listDate;
    private DataAccessStub db;
    private LinkedList<ListItem> todayFoodList;
    private Integer calorieGoal;
    private Integer calorieConsumed;
    private Integer calorieExercise;
    private Integer calorieNet;
    private Integer progressBar;
    private Integer progressExcess;
    private boolean dataReady;

    public MealDiaryOps() {
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

    private void pullDBdata() {
        ArrayList<ListItem> dbFetch = db.getFoodLog(listDate);
        todayFoodList = new LinkedList<ListItem>();
        todayFoodList.addAll(dbFetch);
        calorieGoal = db.getCalorieGoal();
    }

    public void addToDiary(Edible item) {
        db.addRecipeToLog(item);
    }

    public void nextDate() {
        dataReady = false;
        listDate.add(Calendar.DAY_OF_YEAR, 1);
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public void prevDate() {
        dataReady = false;
        listDate.add(Calendar.DAY_OF_YEAR, -1);
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public void setListDate(Calendar newDate) {
        dataReady = false;
        listDate = newDate;
        pullDBdata();
        updateProgress();
        dataReady = true;
    }

    public Calendar getListDate() {
        return listDate;
    }

    public boolean isDataReady() {
        return dataReady;
    }

    public void setGoal(int newGoal) {
        if (newGoal >= 0 && newGoal <= 9999) {
            calorieGoal = newGoal;
        }
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

    public void updateList(LinkedList<ListItem> newList) {
        if (newList != null) {
            todayFoodList = newList;
            updateProgress();
            //push to db after
        }
    }

    public void setCalorieGoal(Integer newGoal) {
        if (calorieGoal == null) {
            calorieGoal = new Integer(-1);
        }
        if (newGoal != null && newGoal >= 0 && newGoal <= 9999) {
            calorieGoal = newGoal;
        }
    }

    public void setCalorieExercise(Integer newExercise) {
        if (calorieExercise == null) {
            calorieExercise = new Integer(-1);
        }
        if (newExercise != null && newExercise >= 0 && newExercise <= 9999) {
            calorieExercise = newExercise;
        }
    }

    private void updateProgress() {
        sumCalories();
        netCalories();
        calcProgress();
    }

    private void calcProgress() {
        if (calorieNet > 0) {
            progressExcess = 0;
            progressBar = (calorieGoal - calorieNet) * 100 / calorieGoal;
        } else {
            progressBar = 100;
            progressExcess = -calorieNet * 100 / calorieGoal;
            if(progressExcess > 25){
                progressExcess = 25;
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
