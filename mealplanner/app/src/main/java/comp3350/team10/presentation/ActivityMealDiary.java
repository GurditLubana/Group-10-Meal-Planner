package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.UnitConverter;
import comp3350.team10.objects.*;
import comp3350.team10.objects.ListItem;
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
import android.view.View;

import com.google.android.material.datepicker.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {
    private ActivityResultLauncher<Intent> pickMeal; // call back listener when recipebook activity is launched for meal selection
    private RVAMealDiary recyclerViewAdapter;   //Houses the logic for a recycle view with diary entries
    private MealDiaryLiveData mealDiaryData;    //Enables persistent data
    private RecyclerView mealRecyclerView;      //Houses a recycle view for diary entries
    private MealDiaryOps opExec;                //Business logic for MealDiary
    private Toolbar toolbar;                    //app title
    private EdibleLog addButton;

    private ArrayList<Edible> data;            //The data for the diary entries
    private int savedItemPosition;              //Saves the position of an item for temporary removal
    private EdibleLog savedItem;                   //Saves the item for temporary removal
    private EntryMode mode;                     //This tracks the type of input dialog when launched


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Can be used to reset db
        //this.deleteDatabase("HSqlDB");
        //System.out.println("reseting db");
        //System.exit(1);

        this.addButton = new EdibleLog(ListItem.FragmentType.diaryAdd);
        setContentView(R.layout.activity_meal_diary);
        SharedDB.start(this);
        this.initToolbar();
        this.opExec = new MealDiaryOps(SharedDB.getSharedDB());
        this.initLiveData();
        this.initRecyclerView();
        this.createActivityCallbackListener();
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
        if (this.data != null) {
            this.recyclerViewAdapter = new RVAMealDiary(this.data);
            this.mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
            this.mealRecyclerView.setAdapter(this.recyclerViewAdapter);
            this.mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void createActivityCallbackListener() {
        this.pickMeal = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data;
                        int dbkey;

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            data = result.getData();
                            dbkey = data.getExtras().getInt("DBKEY");

                            opExec.addByKey(dbkey);
                            updateLiveData();
                        }
                    }
                });
    }

    public void showContextUI(int position) {
        EdibleLog modifyLog = new EdibleLog(ListItem.FragmentType.diaryModify);

        if (position != this.savedItemPosition && this.savedItem != null) {
            this.data.remove(this.savedItemPosition);
            this.data.add(this.savedItemPosition, this.savedItem);
        }

        if (position >= 0) {
            if (this.data.get(position).getFragmentType() == ListItem.FragmentType.diaryEntry && this.data.get(position) instanceof EdibleLog) {
                this.savedItem = (EdibleLog)this.data.remove(position);
                this.savedItemPosition = position;
                this.data.add(position, modifyLog);
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
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        datePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        restoreSaved();
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.setTimeInMillis((Long) selection);
                        selectedDate.add(Calendar.DAY_OF_YEAR, 1);
                        opExec.setListDate(selectedDate);
                        updateLiveData();
                    }
                }
        );
    }

    public void restoreSaved(){
        if(savedItem != null){
            this.data.remove(this.savedItemPosition);
            this.data.add(this.savedItemPosition, this.savedItem);
            this.savedItemPosition = -1;
            this.savedItem = null;
        }

        updateLiveData();
    }

    @Override
    public void prevDate() {
        this.restoreSaved();
        this.opExec.prevDate();
        this.updateLiveData();
    }

    @Override
    public void nextDate() {
        this.restoreSaved();
        this.opExec.nextDate();
        this.updateLiveData();
    }

    @Override
    public void editItem() { //launch dialog fragment
        new FragmentDiaryDialogs().show(getSupportFragmentManager(), FragmentDiaryDialogs.TAG);
        this.mode = EntryMode.EDIT_QTY;
    }

    @Override
    public void showGoalEntryDialog() { //launch dialog fragment
        new FragmentDiaryDialogs().show(getSupportFragmentManager(), FragmentDiaryDialogs.TAG);
        this.mode = EntryMode.GOAL_CALORIE;
    }

    @Override
    public void showExerciseEntryDialog() { //launch dialog fragment
        new FragmentDiaryDialogs().show(getSupportFragmentManager(), FragmentDiaryDialogs.TAG);
        this.mode = EntryMode.ACTUAL_EXERCISE;
    }

    @Override
    public void removeItem(int pos) {
        if (this.data.size() > 0) {
            this.data.remove(pos);
            this.savedItem = null;
            this.recyclerViewAdapter.notifyItemRemoved(pos);
            this.recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
            this.recyclerViewAdapter.notifyDataSetChanged();
            this.opExec.updateList(this.data);
            this.updateLiveData();
        }
    }

    @Override
    public void addEntry(int pos) { //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);

        if(this.data.get(pos) instanceof EdibleLog) {
            intent.putExtra("DBKEY", ((EdibleLog)(this.data.get(pos))).getDbkey());
            this.pickMeal.launch(intent);
        }
    }

    public void updateLiveData() {
        if (this.mealDiaryData != null && this.opExec != null) {
            this.data = this.opExec.getList();
            
            if(!this.data.contains(this.addButton)) {
                this.data.add(this.addButton);
            }

            this.mealDiaryData.getActivityDate().setValue(this.opExec.getListDate());
            this.mealDiaryData.getGoalCalories().setValue(this.opExec.getCalorieGoal());
            this.mealDiaryData.getConsumedCalories().setValue(this.opExec.getCurrLog().getEdibleCalories());
            this.mealDiaryData.getExerciselCalories().setValue(this.opExec.getCurrLog().getExerciseActual());
            this.mealDiaryData.getNetCalories().setValue(this.opExec.getCalorieNet());
            this.mealDiaryData.getProgressBar().setValue(this.opExec.getProgressBar());
            this.mealDiaryData.getProgressExcess().setValue(this.opExec.getProgressExcess());
        }

        if (this.recyclerViewAdapter != null) {
            //tempList = convertToListItem(this.data);
            this.recyclerViewAdapter.changeData(this.data);
            this.recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public String getEntryQty() {
        return String.valueOf(this.savedItem.getQuantity());
    }

    @Override
    public Edible.Unit getEntryUnit() {
        return this.savedItem.getUnit();
    }

    @Override
    public void setEntryQty(Integer amount, String unit) {
        EdibleLog selectedItem = null;
        UnitConverter converter = null;

        try {
            selectedItem = this.savedItem;
            //converter = new UnitConverter(selectedItem.getUnit(), selectedItem.getQuantity(), selectedItem.getCalories());
            selectedItem.setQuantity(amount);
            selectedItem.setUnit(Edible.Unit.valueOf(unit));
            selectedItem.setCalories();

            this.showContextUI(-1);
            this.opExec.updateList(data);
            this.updateLiveData();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String getExerciseCalories() {
        return "" + opExec.getCurrLog().getExerciseActual();
    }

    @Override
    public void setExerciseCalories(Integer value) {
        this.opExec.setCalorieExercise(value);
        this.updateLiveData();
    }

    @Override
    public String getGoalCalories() {
        return "" + opExec.getCalorieGoal();
    }

    @Override
    public void setGoalCalories(Integer value) { //only on current day
        this.opExec.setCalorieGoal(value);
        this.updateLiveData();
    }

    public EntryMode getEntryMode() {
        return this.mode;
    }
}
