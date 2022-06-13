package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.UnitConverter;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.datepicker.*;

import java.util.LinkedList;
import java.util.Calendar;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {
    private static enum EDIBLES_TYPES {FOOD, MEAL, DRINK}
    private RVAMealDiary recyclerViewAdapter;   //Houses the logic for a recycle view with diary entries
    private MealDiaryLiveData mealDiaryData;    //Enables persistent data
    private RecyclerView mealRecyclerView;      //Houses a recycle view for diary entries
    private MealDiaryOps opExec;                //Business logic for MealDiary
    private Toolbar toolbar;                    //Progress bar

    private LinkedList<Edible> data;            //The data for the diary entries
    private int savedItemPosition;              //Saves the position of an item for temporary removal
    private Edible savedItem;                   //Saves the item for temporary removal


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal_diary);
        SharedDB.start("EaTen");
        this.initToolbar();
        this.opExec = new MealDiaryOps(SharedDB.getSharedDB());
        this.initLiveData();
        this.initRecyclerView();
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("MealPlanner");
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setElevation(0);
    }

    private void initLiveData() {
        this.mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class);
        this.updateLiveData();
    }

    private void initRecyclerView() {
        if(this.data != null) {
            this.recyclerViewAdapter = new RVAMealDiary(this.data);
            this.mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
            this.mealRecyclerView.setAdapter(this.recyclerViewAdapter);
            this.mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void showContextUI(int position) {
        if(position != this.savedItemPosition && this.savedItem != null) {
            this.data.remove(this.savedItemPosition);
            this.data.add(this.savedItemPosition, this.savedItem);
        }

        if(position >= 0) {
            if (this.data.get(position).getFragmentType() == ListItem.FragmentType.diaryEntry) {
                this.savedItem = this.data.remove(position);
                this.savedItemPosition = position;
                this.data.add(position, new Food("ui", 0, 0, ListItem.FragmentType.diaryModify, ListItem.Unit.g, 0, 0));
            }
            else {
                this.data.remove(position);
                this.data.add(position, this.savedItem);
                this.savedItem = null;
            }
        }

        this.recyclerViewAdapter.notifyItemRemoved(position);
        this.recyclerViewAdapter.notifyItemRangeChanged(position, data.size());
        this.recyclerViewAdapter.notifyDataSetChanged();
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
        this.opExec.prevDate();
        this.updateLiveData();
    }

    @Override
    public void nextDate() {
        this.opExec.nextDate();
        this.updateLiveData();
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
        if(this.data.size() > 0) {
            this.data.remove(pos);
            this.savedItem = null;
            this.recyclerViewAdapter.notifyItemRemoved(pos);
            this.recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
            this.recyclerViewAdapter.notifyDataSetChanged();
            this.opExec.updateList(data);
            this.updateLiveData();
        }
    }

    @Override
    public void addEntry(int pos) { //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);

        intent.putExtra("DBKEY", this.data.get(pos).getDbkey());
        this.pickMeal.launch(intent);
    }

    public void updateLiveData() {
        if (this.mealDiaryData != null && this.opExec != null) {
            this.data = this.opExec.getList();
            this.mealDiaryData.getActivityDate().setValue(this.opExec.getListDate());
            this.mealDiaryData.getGoalCalories().setValue(this.opExec.getCalorieGoal());
            this.mealDiaryData.getConsumedCalories().setValue(this.opExec.getCalorieConsumed());
            this.mealDiaryData.getExerciselCalories().setValue(this.opExec.getCalorieExercise());
            this.mealDiaryData.getNetCalories().setValue(this.opExec.getCalorieNet());
            this.mealDiaryData.getProgressBar().setValue(this.opExec.getProgressBar());
            this.mealDiaryData.getProgressExcess().setValue(this.opExec.getProgressExcess());
        }

        if (this.recyclerViewAdapter != null) {
            this.recyclerViewAdapter.changeData(this.data);
            this.recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public String getEntryQty(){
        return String.valueOf(this.savedItem.getQuantity());
    }

    @Override
    public ListItem.Unit getEntryUnit(){
        return this.savedItem.getBaseUnit();
    }

    @Override
    public void setEntryQty(Integer amount, String unit){
        Edible selectedItem = null;
        UnitConverter converter = null;

        if(savedItem instanceof Edible) {
            selectedItem = (Edible) this.savedItem;
            converter = new UnitConverter(selectedItem.getBaseUnit(), selectedItem.getQuantity(), selectedItem.getCalories());
            selectedItem.setQuantity(amount);
            selectedItem.setBaseUnit(ListItem.Unit.valueOf(unit));
            selectedItem.setCalories(converter.getCalories(selectedItem.getBaseUnit(), selectedItem.getQuantity()));
        }

        this.showContextUI(-1);
        this.opExec.updateList(data);
        this.updateLiveData();
    }

    @Override
    public String getExerciseCalories(){
        return opExec.getCalorieExercise().toString();
    }

    @Override
    public void setExerciseCalories(Integer value){
        this.opExec.setCalorieExercise(value);
        this.updateLiveData();
    }

    @Override
    public String getGoalCalories(){
        return opExec.getCalorieGoal().toString();
    }

    @Override
    public void setGoalCalories(Integer value){
        this.opExec.setCalorieGoal(value);
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
                        updateLiveData();
                    }
                }
            });
}
