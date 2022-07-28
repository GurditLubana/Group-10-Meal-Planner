package comp3350.team10.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.objects.Edible;

public class MealDiaryLiveData extends ViewModel {

    private MutableLiveData<Calendar> activityDate;             //The current date 
    private MutableLiveData<ArrayList<Edible>> mealsOnDate;  //The given meals on the current date
    private MutableLiveData<Double> goalCalories;              //The calorie goal for the current date
    private MutableLiveData<Double> consumedCalories;          //The calories consumed for the current date
    private MutableLiveData<Double> exerciseCalories;          //The calories burnt during exercise for the current date
    private MutableLiveData<Double> netCalories;               //The net calorie calculation for a given date (consumed - burnt)
    private MutableLiveData<Double> progressBar;               //The progress bar for the current date
    private MutableLiveData<Double> progressExcess;            //The excess progress bar for the current date

    //(different than progress bar)
    public MutableLiveData<Calendar> getActivityDate() {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        return activityDate;
    }

    public MutableLiveData<ArrayList<Edible>> getMealsOnDate() {
        if (mealsOnDate == null) {
            mealsOnDate = new MutableLiveData<ArrayList<Edible>>(new ArrayList<Edible>());
        }

        return mealsOnDate;
    }

    public MutableLiveData<Double> getGoalCalories() {
        if (goalCalories == null) {
            goalCalories = new MutableLiveData<Double>(1500.0);
        }

        return goalCalories;
    }

    public MutableLiveData<Double> getConsumedCalories() {
        if (consumedCalories == null) {
            consumedCalories = new MutableLiveData<Double>(1000.0);
        }

        return consumedCalories;
    }

    public MutableLiveData<Double> getExerciselCalories() {
        if (exerciseCalories == null) {
            exerciseCalories = new MutableLiveData<Double>(200.0);
        }

        return exerciseCalories;
    }

    public MutableLiveData<Double> getNetCalories() {
        if (netCalories == null) {
            netCalories = new MutableLiveData<Double>(69.0);
        }

        return netCalories;
    }

    public MutableLiveData<Double> getProgressBar() {
        if (progressBar == null) {
            progressBar = new MutableLiveData<Double>(0.0);
        }

        return progressBar;
    }

    public MutableLiveData<Double> getProgressExcess() {
        if (progressExcess == null) {
            progressExcess = new MutableLiveData<Double>(0.0);
        }

        return progressExcess;
    }
}