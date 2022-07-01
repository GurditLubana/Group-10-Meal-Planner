package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.UnitConverter;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

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
    private DailyLog currLog;
    private Toolbar toolbar;                    //app title
    private Edible addButton;
    private Edible modifyLog;
    private final String DIARYADDCARD = "diaryAdd";
    private final String DIARYMODIFYCARD = "diaryModify";

    private ArrayList<Edible> data;            //The data for the diary entries
    private int savedItemPosition;              //Saves the position of an item for temporary removal
    private Edible savedItem;                   //Saves the item for temporary removal
    private EntryMode mode;                     //This tracks the type of input dialog when launched


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal_diary);
        this.initToolbar();
        this.opExec = new MealDiaryOps();
        this.initUICardObjects();
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

    private void initUICardObjects() {

        this.savedItemPosition = -1;
        this.addButton = new Edible();
        this.modifyLog = new Edible();
        try {
            this.addButton.setName(DIARYADDCARD);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
        try {
            this.modifyLog.setName(DIARYMODIFYCARD);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
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
        int otherPosition = -1;
        if (position >= 0 && position != this.savedItemPosition ) {
            if(this.savedItem == null) {
                saveItem(position);
            } else {
                otherPosition = this.savedItemPosition;
                swapSaved(position);
                this.recyclerViewAdapter.notifyItemChanged(otherPosition);
            }
            this.data.remove(position);
            this.data.add(position, modifyLog);
        } else {
            restoreSaved();
        }
        this.recyclerViewAdapter.notifyItemChanged(position);
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    private void restoreSaved() {
        if (savedItem != null) {
            this.data.remove(this.savedItemPosition);
            this.data.add(this.savedItemPosition, this.savedItem);
            this.savedItemPosition = -1;
            this.savedItem = null;
        }
    }

    private void saveItem(int position) {
            this.savedItemPosition = position;
            this.savedItem = this.data.get(position);
    }

    private void swapSaved(int position) {
        Edible temp = this.data.get(position);
        restoreSaved();
        this.savedItemPosition = position;
        this.savedItem = temp;
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
            this.currLog.setEdibleList(data);
            this.opExec.logChangedUpdateDB();
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
            this.currLog = this.opExec.getCurrLog();
            this.data = this.currLog.getEdibleList();

            if (!this.data.contains(this.addButton)) {
                this.data.add(this.addButton);
            }

            this.mealDiaryData.getActivityDate().setValue(this.currLog.getDate());
            this.mealDiaryData.getGoalCalories().setValue(this.currLog.getCalorieGoal());
            this.mealDiaryData.getConsumedCalories().setValue(this.currLog.getEdibleCalories());
            this.mealDiaryData.getExerciselCalories().setValue(this.currLog.getExerciseActual());
            this.mealDiaryData.getNetCalories().setValue(this.currLog.getCalorieNet());
            this.mealDiaryData.getProgressBar().setValue(this.currLog.getProgressBar());
            this.mealDiaryData.getProgressExcess().setValue(this.currLog.getProgressExcess());
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
    public void setEntryQty(Double amount, String unit) {
        EdibleLog selectedItem = null;
        UnitConverter converter = null;

        try {
            if (this.savedItem instanceof EdibleLog) {
                selectedItem = (EdibleLog) this.savedItem;
                //converter = new UnitConverter(selectedItem.getUnit(), selectedItem.getQuantity(), selectedItem.getCalories());
                selectedItem.setQuantity(amount);
                selectedItem.setUnit(Edible.Unit.valueOf(unit));
                selectedItem.setCalories();

                this.showContextUI(-1);
                this.currLog.setEdibleList(data);
                this.opExec.logChangedUpdateDB();
                this.updateLiveData();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String getExerciseCalories() {
        return "" + opExec.getCurrLog().getExerciseActual();
    }

    @Override
    public void setExerciseCalories(Double value) {
        try {
            this.currLog.setExerciseActual(value);
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }
        this.updateLiveData();
    }

    @Override
    public String getGoalCalories() {
        return "" + this.currLog.getCalorieGoal();
    }

    @Override
    public void setGoalCalories(Double value) { //only on current day
        try {
        this.currLog.setCalorieGoal(value);
        }
        catch (Exception e){
            System.out.println(e);
            System.exit(1);
        }
        this.updateLiveData();
    }

    public EntryMode getEntryMode() {
        return this.mode;
    }
}
