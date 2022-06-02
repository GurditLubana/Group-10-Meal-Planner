package comp3350.team10.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;

import comp3350.team10.objects.DiaryItem;
import comp3350.team10.objects.ListItem;

public class MealDiaryLiveData extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<Calendar> activityDate;
    private MutableLiveData<LinkedList<ListItem>> mealsOnDate;

    public MutableLiveData<Calendar> getActivityDate() {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        return activityDate;
    }

    public MutableLiveData<LinkedList<ListItem>> getMealsOnDate() {
        if(mealsOnDate == null ){
            mealsOnDate = new MutableLiveData<LinkedList<ListItem>>();
        }
        return mealsOnDate;
    }

    public void setActivityDate(Calendar newDate) {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        activityDate.setValue(newDate);
    }



    public void setMealsOnDate(LinkedList<ListItem> newData) {
        mealsOnDate.setValue(newData);
    }

    // Rest of the ViewModel...
}
