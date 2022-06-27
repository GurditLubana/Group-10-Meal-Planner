package comp3350.team10.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.ArrayList;

import comp3350.team10.objects.ListItem;

public class MealDiaryLiveData extends ViewModel {

    private MutableLiveData<Calendar> activityDate;             //The current date 
    private MutableLiveData<ArrayList<ListItem>> mealsOnDate;  //The given meals on the current date
    private MutableLiveData<Integer> goalCalories;              //The calorie goal for the current date
    private MutableLiveData<Integer> consumedCalories;          //The calories consumed for the current date
    private MutableLiveData<Integer> exerciseCalories;          //The calories burnt during exercise for the current date
    private MutableLiveData<Integer> netCalories;               //The net calorie calculation for a given date (consumed - burnt)
    private MutableLiveData<Integer> progressBar;               //The progress bar for the current date
    private MutableLiveData<Integer> progressExcess;            //The excess progress bar for the current date 
                                                                //(different than progress bar)
    public MutableLiveData<Calendar> getActivityDate() {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        return activityDate;
    }

    public MutableLiveData<ArrayList<ListItem>> getMealsOnDate() {
        if (mealsOnDate == null) {
            mealsOnDate = new MutableLiveData<ArrayList<ListItem>>(new ArrayList<ListItem>());
        }

        return mealsOnDate;
    }

    public MutableLiveData<Integer> getGoalCalories() {
        if (goalCalories == null) {
            goalCalories = new MutableLiveData<Integer>(1500);
        }

        return goalCalories;
    }

    public MutableLiveData<Integer> getConsumedCalories() {
        if (consumedCalories == null) {
            consumedCalories = new MutableLiveData<Integer>(1000);
        }

        return consumedCalories;
    }

    public MutableLiveData<Integer> getExerciselCalories() {
        if (exerciseCalories == null) {
            exerciseCalories = new MutableLiveData<Integer>(200);
        }

        return exerciseCalories;
    }

    public MutableLiveData<Integer> getNetCalories() {
        if (netCalories == null) {
            netCalories = new MutableLiveData<Integer>(69);
        }

        return netCalories;
    }

    public MutableLiveData<Integer> getProgressBar() {
        if (progressBar == null) {
            progressBar = new MutableLiveData<Integer>(0);
        }

        return progressBar;
    }

    public MutableLiveData<Integer> getProgressExcess() {
        if (progressExcess == null) {
            progressExcess = new MutableLiveData<Integer>(0);
        }

        return progressExcess;
    }
}