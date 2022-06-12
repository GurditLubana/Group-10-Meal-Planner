package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.UnitConverter;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.SharedDB;

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

import java.util.LinkedList;
import java.util.Calendar;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {
    private static enum EDIBLES_TYPES {FOOD, MEAL, DRINK}
    private RVAMealDiary recyclerViewAdapter;   //Houses the logic for a recycle view with diary entries
    private MealDiaryLiveData mealDiaryData;    //Enables persistant data
    private RecyclerView mealRecyclerView;      //Houses a recycle view for diary entries
    private MealDiaryOps opExec;                //Buisness logic for MealDiary
    private Toolbar toolbar;                    //Progress bar

    private LinkedList<Edible> data;            //The data for the diary entries
    private int savedItemPosi;                  //Saves the position of an item for temporary removal
    private Edible savedItem;                   //Saves the item for temporary removal


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal_diary);
        SharedDB.start("EaTen");
        this.initToolbar();
        opExec = new MealDiaryOps(SharedDB.getSharedDB());
        this.initLiveData();
        this.initRecyclerView();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MealPlanner");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(0);
    }

    private void initLiveData() {
        mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class);
        updateLiveData();
    }

    private void initRecyclerView() {
        if(data != null) {
            recyclerViewAdapter = new RVAMealDiary(data);
            mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
            mealRecyclerView.setAdapter(recyclerViewAdapter);
            mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void showContextUI(int posi) {
        if(posi != savedItemPosi && savedItem != null) {
            data.remove(savedItemPosi);
            data.add(savedItemPosi, savedItem);
        }

        if(posi >= 0) {
            if (data.get(posi).getFragmentType() == ListItem.FragmentType.diaryEntry) {
                savedItem = data.remove(posi);
                savedItemPosi = posi;
                data.add(posi, new Edible(ListItem.FragmentType.diaryModify));
            } 
            else {
                data.remove(posi);
                data.add(posi, savedItem);
                savedItem = null;
            }
        }

        recyclerViewAdapter.notifyItemRemoved(posi);
        recyclerViewAdapter.notifyItemRangeChanged(posi, data.size());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectDate() {
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();
        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        datePicker.addOnPositiveButtonClickListener(
            new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.setTimeInMillis((Long) selection);
                    selectedDate.add(Calendar.DAY_OF_YEAR, 1);
                    opExec.setListDate(selectedDate);
                    updateLiveData();
                }
            }
        );
    }

    @Override
    public void prevDate() {
        opExec.prevDate();
        updateLiveData();
    }

    @Override
    public void nextDate() {
        opExec.nextDate();
        updateLiveData();
    }

    @Override
    public void editItem(int pos) { //launch dialog fragment
        new FragmentMealDiaryEdit().show(getSupportFragmentManager(), FragmentMealDiaryEdit.TAG);
    }

    @Override
    public void showGoalEntryDialog() { //launch dialog fragment
        new FragmentGoalEntry().show(getSupportFragmentManager(), FragmentGoalEntry.TAG);
    }

    @Override
    public void showExerciseEntryDialog() { //launch dialog fragment
        new FragmentExerciseEntry().show(getSupportFragmentManager(), FragmentExerciseEntry.TAG);
    }

    @Override
    public void removeItem(int pos) {
        if(data.size() > 0) {
            data.remove(pos);
            savedItem = null;
            recyclerViewAdapter.notifyItemRemoved(pos);
            recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
            recyclerViewAdapter.notifyDataSetChanged();
            opExec.updateList(data);
            this.updateLiveData();
        }
    }

    @Override
    public void addEntry(int pos) { //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);

        intent.putExtra("DBKEY", data.get(pos).getDbkey());
        pickMeal.launch(intent);
    }

    public void updateLiveData() {
        if (mealDiaryData != null && opExec != null) {
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
        return String.valueOf(savedItem.getQuantity());
    }

    @Override
    public ListItem.Unit getEntryUnit(){
        return savedItem.getBaseUnit();
    }

    @Override
    public void setEntryQty(Integer amount, String unit){
        Edible selectedItem = null;
        UnitConverter converter = null;

        if(savedItem instanceof Edible) {
            selectedItem = (Edible) savedItem;
            converter = new UnitConverter(selectedItem.getBaseUnit(), selectedItem.getQuantity(), selectedItem.getCalories());
            selectedItem.setQuantity(amount);
            selectedItem.setBaseUnit(ListItem.Unit.valueOf(unit));
            selectedItem.setCalories(converter.getCalories(selectedItem.getBaseUnit(), selectedItem.getQuantity()));
        }

        this.showContextUI(-1);
        opExec.updateList(data);
        this.updateLiveData();
    }

    @Override
    public String getExerciseCalories(){
        return opExec.getCalorieExercise().toString();
    }

    @Override
    public void setExerciseCalories(Integer value){
        opExec.setCalorieExercise(value);
        this.updateLiveData();
    }

    @Override
    public String getGoalCalories(){
        return opExec.getCalorieGoal().toString();
    }

    @Override
    public void setGoalCalories(Integer value){
        opExec.setCalorieGoal(value);
        this.updateLiveData();
    }

    // GetContent creates an ActivityResultLauncher<String> to allow you to pass
    // in the mime type you'd like to allow the user to select
    ActivityResultLauncher<Intent> pickMeal = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data;
                    int dbkey;

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        data = result.getData();
                        dbkey = data.getExtras().getInt("DBKEY");
                        
                        System.out.println("We got back: " + dbkey);
                        opExec.addByKey(dbkey);
                        this.updateLiveData();
                    }
                }
            });
}