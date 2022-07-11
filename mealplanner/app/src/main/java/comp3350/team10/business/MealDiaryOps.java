package comp3350.team10.business;

import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.LogDBInterface;
import comp3350.team10.persistence.SharedDB;

import java.util.Calendar;
import java.util.NoSuchElementException;

public class MealDiaryOps {
    private final static Integer INCREMENT = 1;         //Directional arrow increments

    //Database variables
    private DailyLog currLog;           //The food in the planner for the given day
    private Calendar logDate;           //The date the planner is set to
    private LogDBInterface db;          //Accesses the database

    //Progress bar variables
    private UserDataOps opUser;         //Business logic for handling the app's user

    public MealDiaryOps() throws NullPointerException {
        this.db = SharedDB.getLogDB();
        this.logDate = Calendar.getInstance();
        this.logDate.set(Calendar.MONTH, 9);
        this.logDate.set(Calendar.DAY_OF_MONTH, 10);

        if (db != null) {
            this.opUser = new UserDataOps();
            this.dateChangedUpdateList();
        } else {
            throw new NullPointerException("MealDiaryOps requires an initialized database in SharedDB");
        }
    }

    public void nextDate() throws IllegalArgumentException {
        Calendar newDate = (Calendar) this.logDate.clone();
        newDate.add(Calendar.DAY_OF_YEAR, INCREMENT);
        try {
            this.setListDate(newDate);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void prevDate() throws IllegalArgumentException {
        Calendar newDate = (Calendar) this.logDate.clone();
        newDate.add(Calendar.DAY_OF_YEAR, -INCREMENT);
        try {
            this.setListDate(newDate);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void setCalorieGoal(DailyLog currLog, double newCalorieGoal) {
        this.db.setLogCalorieGoal(opUser.getUser().getUserID(), newCalorieGoal, currLog.getDate());
    }

    public void setListDate(Calendar newDate) throws IllegalArgumentException {
        int diff = logDate.get(Calendar.YEAR) - newDate.get(Calendar.YEAR);

        if (diff <= Constant.DATE_LIMIT && diff >= -Constant.DATE_LIMIT) {
            this.logDate = newDate;
            this.dateChangedUpdateList();
        } else {
            throw new IllegalArgumentException("MealDiaryOps requires dates within " + Constant.DATE_LIMIT + " years of the current date");
        }
    }

    private void dateChangedUpdateList() {
        this.currLog = this.db.searchFoodLogByDate(opUser.getUser().getUserID(), this.logDate );
    }

    public DailyLog getCurrLog() {
        return this.currLog;
    }

    public void logChangedUpdateDB() {
        this.db.replaceLog(opUser.getUser().getUserID(), this.currLog );
    }

    public void addByKey(int dbkey, boolean isCustom) throws NoSuchElementException {
        EdibleLog newItem = db.findEdibleByKey(dbkey, isCustom);
        Edible foundEdible = null;

        if(newItem == null){
            throw new NoSuchElementException("MealDiaryOps addByKey the supplied dbkey does not match any db entry " + dbkey);
        }
            
        try {
            foundEdible = new EdibleLog(newItem).init(newItem.getQuantity(), newItem.getUnit());
            this.currLog.addEdibleToLog(foundEdible); //this needs to change to a db call
            this.logChangedUpdateDB();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}