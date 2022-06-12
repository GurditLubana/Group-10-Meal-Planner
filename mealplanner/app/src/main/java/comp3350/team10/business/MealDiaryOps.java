package comp3350.team10.business;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.DataAccessStub;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.LinkedList;

public class MealDiaryOps {
    private final static Integer MAX_PROGRESS = 100;    //Scales the progress bar (percentage)
    private final static Integer GOAL_LIMIT = 9999;     //The highest number of calories a user should aim for
    private final static Integer MAX_EXCESS = 100;      //How many calories a user can exceed their goal by
    private final static Integer DATE_LIMIT = 2;        //Limits the quantity of data saved by years
    private final static Integer INCREMENT = 1;         //Directional arrow increments

    //Database variables
    private LinkedList<Edible> todayFoodList; //The food in the planner for the given day
    private Calendar listDate;                  //The date the planner is set to
    private DataAccessStub db;                  //Accesses the database

    //Progress bar variables
    private Integer calorieConsumed;            //Represents the number of calories currently consumed
    private Integer calorieExercise;            //Represents the calories burnt through exercise
    private Integer progressExcess;             //Represents the overflow of calories on the progress bar
    private Integer progressBar;                //Represents the calories on the progress bar
    private Integer calorieGoal;                //Represents the calorie goal
    private Integer calorieNet;                 //Represents the consumed calories - exercise calories burnt
    
    public MealDiaryOps() {
        this.todayFoodList = new LinkedList<Edible>();
        this.listDate = Calendar.getInstance();
        this.db = new DataAccessStub();

        this.calorieConsumed = -1;
        this.calorieExercise = -1;
        this.progressExcess = -1;
        this.progressBar = -1;
        this.calorieGoal = -1;
        this.calorieNet = -1;
        
        this.pullDBdata();
        this.updateProgress();
    }

    //dependency injectable constructor
    public MealDiaryOps(DataAccessStub db) { 
        this.listDate = Calendar.getInstance();
        
        this.calorieConsumed = -1;
        this.calorieExercise = -1;
        this.progressExcess = -1;
        this.progressBar = -1;
        this.calorieGoal = -1;
        this.calorieNet = -1;
        
        if(db != null) {
            this.db = db;
            this.pullDBdata();
            this.updateProgress();
        }
    }


    private void pullDBdata() {
        this.todayFoodList = new LinkedList<Edible>(db.getFoodList(listDate));
        this.calorieExercise = db.getExerciseActual();
        this.calorieGoal = db.getCalorieGoal();
    }

    public void nextDate() {
        listDate.add(Calendar.DAY_OF_YEAR, INCREMENT);
        this.dateChangedUpdateList();
    }

    public void prevDate() {
        listDate.add(Calendar.DAY_OF_YEAR, -INCREMENT);
        this.dateChangedUpdateList();
    }

    public void setListDate(Calendar newDate) {
        Integer diff = listDate.get(Calendar.YEAR) - newDate.get(Calendar.YEAR);
        
        if(diff <= DATE_LIMIT && diff >= -DATE_LIMIT) { //if within 2 years
            this.listDate = newDate;
            this.dateChangedUpdateList();
        }
    }

    private void dateChangedUpdateList(){
        db.updateSelectedFoodLogFoodList(new ArrayList<Edible>(this.todayFoodList));
        
        this.pullDBdata();
        this.updateProgress();
    }

    public void setCalorieGoal(Integer newGoal) {
        if(newGoal != null && newGoal >= 0 && newGoal <= GOAL_LIMIT) {
            this.calorieGoal = newGoal;
            db.setCalorieGoal(newGoal);
            this.updateProgress();
        }
    }

    public void setCalorieExercise(Integer newExercise) {
        if(newExercise != null && newExercise >= 0 && newExercise <= GOAL_LIMIT) {
            this.calorieExercise = newExercise;
            db.setExerciseActual(newExercise);
            this.updateProgress();
        }
    }

    public void updateList(LinkedList<Edible> newList) {
        if(newList != null) {
            this.todayFoodList = newList;
            db.updateSelectedFoodLogFoodList(new ArrayList<Edible>(newList));
            this.updateProgress();
        }
    }

    public Calendar getListDate() {
        return this.listDate;
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

    public Integer getProgressBar(){
        return this.progressBar;
    }

    public Integer getProgressExcess(){
        return this.progressExcess;
    }

    public LinkedList<Edible> getList() {
        return this.todayFoodList;
    }

    private void updateProgress() {
        this.sumCalories();
        this.netCalories();
        this.calcProgress();
    }

    private void calcProgress() {
        if(this.calorieNet > 0) {
            this.progressExcess = 0;
            this.progressBar = (this.calorieGoal - this.calorieNet) * MAX_PROGRESS / this.calorieGoal;
        } 
        else {
            this.progressBar = MAX_PROGRESS;
            this.progressExcess = -this.calorieNet * MAX_PROGRESS / this.calorieGoal;
            
            if(this.progressExcess > MAX_EXCESS){
                this.progressExcess = MAX_EXCESS;
            }
        }
    }

    private void sumCalories() {
        if(this.todayFoodList != null) {
            this.calorieConsumed = 0;

            for(int i = 0; i < this.todayFoodList.size(); i++) {
                if(this.todayFoodList.get(i) instanceof Edible) {
                    this.calorieConsumed += ((Edible)this.todayFoodList.get(i)).getCalories();
                }
            }
        }
    }

    private void netCalories() {
        this.calorieNet = this.calorieGoal - (this.calorieConsumed - this.calorieExercise);
    }

    public void addByKey(int dbkey){
        Edible tempEdible = db.findEdibleByKey(dbkey);
        Edible newItem = null;

        if(tempEdible != null){
            tempEdible.setFragmentType(ListItem.FragmentType.diaryEntry);
            this.todayFoodList.add(this.todayFoodList.size() - 1, tempEdible);
        }

        this.updateProgress();
    }
}