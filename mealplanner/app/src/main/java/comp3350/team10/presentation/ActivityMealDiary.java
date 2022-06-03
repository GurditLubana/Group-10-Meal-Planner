package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.datepicker.*;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.objects.*;

import java.util.LinkedList;
import java.util.Calendar;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mealRecyclerView;
    private LinkedList<ListItem> data;
    private ListItem savedItem;
    private int savedItemPosition;
    private MealDiaryLiveData mealDiaryData;
    private MealDiaryOps opExec;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        initToolbar();
        opExec = new MealDiaryOps();
        initLiveData();
        initRecyclerView();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MealPlanner");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(0);
    }

    private void initLiveData() {
        mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class);
        if (opExec.isDataReady()) {
            updateLiveData();
        }
    }

    private void initRecyclerView() {
        if (data != null) {
            recyclerViewAdapter = new RecyclerViewAdapter(data);
            mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
            mealRecyclerView.setAdapter(recyclerViewAdapter);
            mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            //throw new Exception("Meal Diary Linked list empty");
        }
    }

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
        recyclerViewAdapter.notifyItemRemoved(pos);
        recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectDate() {

        MaterialDatePicker datePicker;

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
                        opExec.setListDate(selectedDate);
                        while (!opExec.isDataReady()) {
                        }
                        updateLiveData();
                    }
                });
    }

    @Override
    public void prevDate() {
        opExec.prevDate();
        while (!opExec.isDataReady()) {
        }
        updateLiveData();
    }

    ;

    @Override
    public void nextDate() {
        opExec.nextDate();
        while (!opExec.isDataReady()) {
        }
        updateLiveData();
    }

    ;

    @Override
    public void showGoalEntryDialog() {
        //launch goal input dialog
        //get data then send to opExec maybe do validation here or in dialog
        new FragmentMealDiaryEdit().show(
                getSupportFragmentManager(), FragmentMealDiaryEdit.TAG
        );

    }

    @Override
    public void showExerciseEntryDialog() {
        //launch exercise input dialog
        //get data then send to opExec maybe do validation here or in dialog
        while (!opExec.isDataReady()) {
        }
        updateLiveData();
    }

    @Override
    public void removeItem(int pos) {
        if (data.size() > 0) {
            data.remove(pos);
            savedItem = null;
            recyclerViewAdapter.notifyItemRemoved(pos);
            recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
            recyclerViewAdapter.notifyDataSetChanged();
            opExec.updateList(data);
            updateLiveData();
            //send remove command to ops
        }
    }

    @Override
    public void editItem(int pos) {
        //launch dialog
        //send data to ops
    }

    @Override
    public void addEntry(int pos) {
        //launch recipebook
        //send data to ops
        Intent intent = new Intent(this, ActivityRecipeBook.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void updateLiveData() {
        if (mealDiaryData != null && opExec != null && opExec.isDataReady()) {
            data = opExec.getList();
            mealDiaryData.getActivityDate().setValue(opExec.getListDate());
            mealDiaryData.getGoalCalories().setValue(opExec.getCalorieGoal());
            mealDiaryData.getConsumedCalories().setValue(opExec.getCalorieConsumed());
            mealDiaryData.getExerciselCalories().setValue(opExec.getCalorieExercise());
            mealDiaryData.getNetCalories().setValue(opExec.getCalorieNet());
            mealDiaryData.getProgressBar().setValue(opExec.getProgressBar());
            mealDiaryData.getProgressExcess().setValue(opExec.getProgressExcess());
        }
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.changeData(data);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

}
