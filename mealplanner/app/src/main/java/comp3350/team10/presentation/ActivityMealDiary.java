package comp3350.team10.presentation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.datepicker.*;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.SharedDB;

import java.util.LinkedList;
import java.util.Calendar;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {

    private RVAMealDiary recyclerViewAdapter;
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
        SharedDB.start("EaTen");
        opExec = new MealDiaryOps(SharedDB.getSharedDB());
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
            recyclerViewAdapter = new RVAMealDiary(data);
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
        if(pos >= 0) {
            if (data.get(pos).getFragmentType() == ListItem.FragmentType.diaryEntry) {
                savedItem = data.remove(pos);
                savedItemPosition = pos;
                data.add(pos, new DiaryItem(ListItem.FragmentType.diaryModify, null, null, 0));
            } else {
                data.remove(pos);
                data.add(pos, savedItem);
                savedItem = null;
            }
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

    @Override
    public void nextDate() {
        opExec.nextDate();
        while (!opExec.isDataReady()) {
        }
        updateLiveData();
    }

    @Override
    public void editItem(int pos) {
        //launch dialog fragment
        new FragmentMealDiaryEdit().show(
                getSupportFragmentManager(), FragmentMealDiaryEdit.TAG
        );
    }

    @Override
    public void showGoalEntryDialog() {
        //launch dialog fragment
        new FragmentGoalEntry().show(
                getSupportFragmentManager(), FragmentGoalEntry.TAG
        );
    }

    @Override
    public void showExerciseEntryDialog() {
        //launch dialog fragment
        new FragmentExerciseEntry().show(
                getSupportFragmentManager(), FragmentExerciseEntry.TAG
        );
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
        }
    }

    @Override
    public void addEntry(int pos) {
        //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);
        //use this if need to pass information to activity being launched
        //intent.putExtra("DBKEY", 0);
        pickMeal.launch(intent);
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

    @Override
    public String getEntryQty(){
        return String.valueOf(((Edible) savedItem).getQuantity());
    }

    @Override
    public ListItem.Unit getEntryUnit(){
        return ((Edible) savedItem).getBaseUnit();
    }

    @Override
    public void setEntryQty(Integer amount, String unit){
        //TODO call the unit converter first
        ((Edible) savedItem).setQuantity(amount);
        ((Edible) savedItem).setBaseUnit(ListItem.Unit.valueOf(unit));
        showContextUI(-1);
        opExec.updateList(data);
        updateLiveData();
    }

    @Override
    public String getExerciseCalories(){
        return opExec.getCalorieExercise().toString();
    }

    @Override
    public void setExerciseCalories(Integer value){
        opExec.setCalorieExercise(value);
        updateLiveData();
    }

    @Override
    public String getGoalCalories(){
        return opExec.getCalorieGoal().toString();
    }

    @Override
    public void setGoalCalories(Integer value){
        opExec.setCalorieGoal(value);
        updateLiveData();
    }

    // GetContent creates an ActivityResultLauncher<String> to allow you to pass
    // in the mime type you'd like to allow the user to select
    ActivityResultLauncher<Intent> pickMeal = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        int dbkey = data.getExtras().getInt("DBKEY");
                        System.out.println("We got back: " + dbkey);
                        opExec.addByKey(dbkey);
                        updateLiveData();
                    }
                }
            });


}
