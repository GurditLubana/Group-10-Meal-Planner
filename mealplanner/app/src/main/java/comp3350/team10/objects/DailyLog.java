package comp3350.team10.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;

public class DailyLog { //Sort of like the user's logs from the database
    private ListItem.FragmentType fragmentType; //How it should appear on recycler views
    private ArrayList<Edible> log;              //The edibles present in the given log
    private Integer excActual;                  //The calories burnt while exercising
    private Integer excGoal;                    //The original calorie goal for exercising
    private Integer calGoal;                    //Calculates the difference between the goal and actual exercise calories
    private Integer date;                       //The date

    public DailyLog(Integer date, Integer calGoal, Integer excGoal, Integer excActual, ArrayList<Edible> log) {
        this.log = log;
        this.date = date;
        this.calGoal = calGoal;
        this.excGoal = excGoal;
        this.fragmentType = null;
        this.excActual = excActual;
    }


    public ArrayList<Edible> getFoodList(){
        return this.log;
    }

    public Integer getExcActual(){
        return this.excActual;
    }

    public Integer getExcGoal(){
        return this.excGoal;
    }

    public Integer getCalGoal(){
        return this.calGoal;
    }

    public Integer getDate(){
        return this.date;
    }

    public void setFoodList(ArrayList<Edible> newLog){
        this.log = newLog;
    }

    public void setExcActual(Integer newExcActual){
        this.excActual = newExcActual;
    }

    public void setCalGoal(Integer newCalGoal){
        this.calGoal =newCalGoal;
    }

    public void setExcGoal(Integer newExcGoal) {
        this.excGoal = newExcGoal;
    }

    public void setFragmentType(ListItem.FragmentType fragmentType){
        this.fragmentType = fragmentType;
    }

    public ListItem.FragmentType getFragmentType(){
        return this.fragmentType;
    }
}