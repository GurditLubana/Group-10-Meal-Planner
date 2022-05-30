package comp3350.team10.objects;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;

public class MealDiaryLiveData extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<Calendar> activityDate;
    private MutableLiveData<LinkedList<DiaryItem>> mealsOnDate;

    public MutableLiveData<Calendar> getActivityDate() {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        return activityDate;
    }

    public void setActivityDate(Calendar newDate) {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Calendar>();
        }
        activityDate.setValue(newDate);
    }

    public MutableLiveData<LinkedList<DiaryItem>> getMealsOnDate() {
        if(mealsOnDate == null ){
            mealsOnDate = new MutableLiveData<LinkedList<DiaryItem>>();
        }
        return mealsOnDate;
    }

    public void setMealsOnDate(LinkedList<DiaryItem> newData) {
        mealsOnDate.setValue(newData);
    }

    // Rest of the ViewModel...
}
