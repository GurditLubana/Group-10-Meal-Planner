package comp3350.team10.persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.team10.objects.ListItem;

public class DailyLog implements ListItem {
    private FragmentType fragmentType = null;
    private ArrayList<ListItem> log = null;
    private Integer excActual = null;
    private Integer excGoal = null;
    private Integer calGoal = null;
    private Integer date = null;

    public DailyLog(Integer date, Integer calGoal, Integer excGoal, Integer excActual, ArrayList<ListItem> log){
        this.log = log;
        this.date = date;
        this.calGoal = calGoal;
        this.excGoal = excGoal;
        this.excActual = excActual;
    }

    public ArrayList<ListItem> getFoodList(){
        return log;
    }

    public Integer getExcActual(){
        return excActual;
    }

    public Integer getExcGoal(){
        return excGoal;
    }

    public Integer getCalGoal(){
        return calGoal;
    }

    public Integer getDate(){
        return date;
    }

    public void setFoodList(ArrayList<ListItem> newLog){
        log = newLog;
    }

    public void setExcActual(Integer newExcActual){
        excActual = newExcActual;
    }

    public void setCalGoal(Integer newCalGoal){
        calGoal =newCalGoal;
    }

    public void setExcGoal(Integer newExcGoal) {
        this.excGoal = newExcGoal;
    }

    public void setFragmentType(FragmentType fragmentType){
        this.fragmentType = fragmentType;
    }

    public FragmentType getFragmentType(){
        return this.fragmentType;
    }
}
