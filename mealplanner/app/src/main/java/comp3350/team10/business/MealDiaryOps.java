package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;

public class MealDiaryOps {
    private LinkedList<ListItem> todayFoodList = new LinkedList<ListItem>();
    private Integer calorieConsumed = -1;
    private Integer calorieExercise = -1;
    private Integer progressExcess = -1;
    private Integer progressBar = -1;
    private Integer calorieGoal = -1;
    private Integer calorieNet = -1;
    private DataAccessStub db = new DataAccessStub();
    private Calendar listDate = Calendar.getInstance();;
    private boolean dataReady = false;

    public MealDiaryOps() {
        db.open("someDB");
        pullDBdata();
        updateProgress();
        dataReady = true;
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

    public void setCalorieGoal(Integer newGoal) {
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
