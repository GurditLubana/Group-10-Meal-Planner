package comp3350.team10.objects;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Date;
import java.util.LinkedList;

public class MealDiaryLiveData extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<Date> activityDate;
    private MutableLiveData<LinkedList<DiaryItem>> mealsOnDate;

    public MutableLiveData<Date> getActivityDate() {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Date>();
        }
        return activityDate;
    }

    public void setActivityDate(Date newDate) {
        if (activityDate == null) {
            activityDate = new MutableLiveData<Date>();
            activityDate.setValue(newDate);
        }
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
