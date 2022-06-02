package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;
import comp3350.team10.presentation.MealDiaryLiveData;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
//import java.time.LocalDate;

public class MealDiaryOps {
    private MealDiaryLiveData mealDiaryLiveData;
    private Calendar dataDate;
    private DataAccessStub db;
    private LinkedList<ListItem> todayFoodList;
    private int calorieGoal;
    private boolean dataReady;

    public MealDiaryOps(MealDiaryLiveData mealDiaryLiveData){
        this.mealDiaryLiveData = mealDiaryLiveData;
        this.dataDate = Calendar.getInstance();
        init();
    }

    public void init(){
        db = new DataAccessStub();
        db.open("someDB");
        ArrayList<ListItem> dbFetch = db.getToday();
        todayFoodList = new LinkedList<ListItem>();
        todayFoodList.addAll(dbFetch);

        mealDiaryLiveData.getActivityDate().setValue(dataDate);
        mealDiaryLiveData.getMealsOnDate().setValue(todayFoodList);
    }

    // public LinkedList getData(){
    //     LinkedList<DiaryItem> myList = new LinkedList<DiaryItem>();
    //     myList.addAll(db.getRecipe());

    //     return myList;
    // }

    public void addToDiary(DiaryItem item) {
        db.addRecipeToLog(item);
        init();
    }

    public void nextDate(){
        dataDate.add(Calendar.DAY_OF_YEAR, 1);
    }

    public void prevDate(){
        dataDate.add(Calendar.DAY_OF_YEAR, -1);
    }

    public void setDataDate(Calendar newDate){
        dataDate = newDate;
        mealDiaryLiveData.setActivityDate(newDate);
    }

    public void setGoal(int newGoal){
        if(newGoal >= 0 && newGoal <= 9999)
        {
            calorieGoal = newGoal;
        }
    }

    public LinkedList<ListItem> getList() {
        return todayFoodList;
    }

    private void updateProgress(){
        // recalculate progress
        // push to live data
        //
    }
}
