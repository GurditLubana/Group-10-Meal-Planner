package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.datepicker.*;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.objects.*;

import java.util.LinkedList;
import java.util.Calendar;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {

    private LinkedList<ListItem> data;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mealRecyclerView;
    private ListItem savedItem;
    private int savedItemPosition;
    private MaterialDatePicker datePicker;
    private MealDiaryLiveData mealDiaryData;
    private MealDiaryOps opExec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setActionBar(toolbar);
        toolbar.setTitle("MealPlanner");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(0);
        mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class);
        opExec = new MealDiaryOps(mealDiaryData);
        data = mealDiaryData.getMealsOnDate().getValue();
        initRecyclerView();
    }

    private void initRecyclerView() {

        recyclerViewAdapter = new RecyclerViewAdapter(data);
        mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
        mealRecyclerView.setAdapter(recyclerViewAdapter);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //@Override
    public void showContextUI(int pos) {
        System.out.println("clicked " + " " + pos);
        if (pos != savedItemPosition && savedItem != null) {
            data.remove(savedItemPosition);
            data.add(savedItemPosition, savedItem);
        }
        if (data.get(pos).getFragmentType() == ListItem.FragmentType.diaryEntry) {
            savedItem = data.remove(pos);
            savedItemPosition = pos;
            data.add(pos, new DiaryItem(ListItem.FragmentType.diaryModify, null, null, 0));
        } else {
            data.remove(pos);
            data.add(pos, savedItem);
            savedItem = null;
        }
        //mealRecyclerView.removeViewAt(pos);
        recyclerViewAdapter.notifyItemRemoved(pos);
        recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectDate(){
        System.out.println("test");
        datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        datePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.setTimeInMillis((Long) selection);
                        selectedDate.add(Calendar.DAY_OF_YEAR, 1);
                        opExec.setDataDate(selectedDate);
                    }
                });

    }

    @Override
    public void prevDate(){
        opExec.prevDate();
    };
    @Override
    public void nextDate(){
        opExec.nextDate();
    };
    @Override
    public void setGoal(){
        //launch goal input dialog
        //get data then send to opExec maybe do validation here or in dialog
    }

}
