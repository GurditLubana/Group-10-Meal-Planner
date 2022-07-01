package comp3350.team10.business;

import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;
import comp3350.team10.persistence.DBSelector;
import comp3350.team10.persistence.SharedDB;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MealDiaryOps {
    private final static Integer INCREMENT = 1;         //Directional arrow increments

    //Database variables
    private DailyLog currLog;           //The food in the planner for the given day
    private Calendar logDate;           //The date the planner is set to
    private DBSelector db;          //Accesses the database

    //Progress bar variables
    private UserDataOps opUser;         //Business logic for handling the app's user

    public MealDiaryOps() throws NullPointerException {
        SharedDB.start();
        this.db = SharedDB.getSharedDB();
        this.logDate = Calendar.getInstance();

        if (db != null) {
            this.db = db;
            this.opUser = new UserDataOps(db);
            this.dateChangedUpdateList();
        } else {
            throw new NullPointerException("MealDiaryOps requires an initialized database in SharedDB");
        }
    }

    public void nextDate() throws IllegalArgumentException {
        Calendar newDate = calendarDeepCopy(this.logDate);
        newDate.add(Calendar.DAY_OF_YEAR, INCREMENT);
        try {
            this.setListDate(newDate);
        } catch (Exception e) {
            throw e;
        }
    }

    public void prevDate() throws IllegalArgumentException {
        Calendar newDate = calendarDeepCopy(this.logDate);
        newDate.add(Calendar.DAY_OF_YEAR, -INCREMENT);
        try {
            this.setListDate(newDate);
        } catch (Exception e) {
            throw e;
        }
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

    private Calendar calendarDeepCopy(Calendar date){
        Calendar copy = Calendar.getInstance();
        copy.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
        return copy;
    }

    private void dateChangedUpdateList() {
        this.logChangedUpdateDB();
        this.currLog = this.db.searchFoodLogByDate(this.logDate);
    }

    public DailyLog getCurrLog() {
        return this.currLog;
    }

    public void logChangedUpdateDB(){
        //TODO: persistence method push current dailylog to db
    }

    public void addByKey(int dbkey) throws NoSuchElementException {
        EdibleLog foundEdible = null;
        try {
            foundEdible = new EdibleLog(db.findEdibleByKey(dbkey));
        }
        catch (Exception e){
            System.out.println(e);
            throw new NoSuchElementException("MealDiaryOps addByKey the supplied dbkey does not match any db entry");
        }
            try {
                this.currLog.addEdibleToLog(foundEdible);
            } catch (Exception e) {
                System.out.println(e);
            }
    }
}