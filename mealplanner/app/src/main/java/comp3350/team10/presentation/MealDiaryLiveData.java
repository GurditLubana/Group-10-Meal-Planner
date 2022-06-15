package comp3350.team10.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;

import comp3350.team10.objects.ListItem;

public class MealDiaryLiveData extends ViewModel {

    private MutableLiveData<Calendar> activityDate;
    private MutableLiveData<LinkedList<ListItem>> mealsOnDate;
    private MutableLiveData<Integer> goalCalories;
    private MutableLiveData<Integer> consumedCalories;
    private MutableLiveData<Integer> exerciselCalories;
    private MutableLiveData<Integer> netCalories;
    private MutableLiveData<Integer> progressBar;
    private MutableLiveData<Integer> progressExcess;

    public MutableLiveData<Calendar> getActivityDate() {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        return activityDate;
    }

    public MutableLiveData<LinkedList<ListItem>> getMealsOnDate() {
        if(mealsOnDate == null ){
            mealsOnDate = new MutableLiveData<LinkedList<ListItem>>(new LinkedList<ListItem>());
        }

        return mealsOnDate;
    }

    public MutableLiveData<Integer> getGoalCalories() {
        if(goalCalories == null){
            goalCalories = new MutableLiveData<Integer>(1500);
        }

        return goalCalories;
    }

    public MutableLiveData<Integer> getConsumedCalories() {
        if(consumedCalories == null){
            consumedCalories = new MutableLiveData<Integer>(1000);
        }

        return consumedCalories;
    }

    public MutableLiveData<Integer> getExerciselCalories() {
        if(exerciselCalories == null){
            exerciselCalories = new MutableLiveData<Integer>(200);
        }

        return exerciselCalories;
    }

    public MutableLiveData<Integer> getNetCalories() {
        if(netCalories == null){
            netCalories = new MutableLiveData<Integer>(69);
        }

        return netCalories;
    }

    public MutableLiveData<Integer> getProgressBar(){
        if(progressBar == null){
            progressBar = new MutableLiveData<Integer>(0);
        }

        return progressBar;
    }

    public MutableLiveData<Integer> getProgressExcess(){
        if(progressExcess == null){
            progressExcess = new MutableLiveData<Integer>(0);
        }
        
        return progressExcess;

    }

}
