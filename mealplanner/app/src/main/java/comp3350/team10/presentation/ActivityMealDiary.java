package comp3350.team10.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import comp3350.team10.R;
import comp3350.team10.application.Main;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.EdibleLog;

public class ActivityMealDiary extends AppCompatActivity implements FragToMealDiary {
    private ActivityResultLauncher<Intent> pickMeal; // call back listener when recipebook activity is launched for meal selection

    private RVAMealDiary recyclerViewAdapter;   //Houses the logic for a recycle view with diary entries
    private MealDiaryLiveData mealDiaryData;    //Enables persistent data
    private RecyclerView mealRecyclerView;      //Houses a recycle view for diary entries
    private MealDiaryOps opExec;                //Business logic for MealDiary
    private DailyLog currLog;
    private Toolbar toolbar;                    //app title
    private Edible addButton;   //need a more consistant way to do this

    private ArrayList<Edible> data;             //The data for the diary entries


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        this.addButton = new Edible();
        this.addButton.setName(Constant.DIARY_ADD_CARD);

        this.initToolbar();
        this.copyDatabaseToDevice();
        this.copyImagesToDevice();
        Main.startUp();
        this.opExec = new MealDiaryOps();
        this.initLiveData();
        this.initRecyclerView();
        this.createActivityCallbackListener();
    }


    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitleTextColor(Color.parseColor(Constant.TITLE_COLOR));
        this.toolbar.setTitle(Constant.TITLE_CONTENT);
        this.toolbar.setElevation(0);
    }

    private void initLiveData() {
        this.mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class);
        this.updateLiveData();
    }

    private void initRecyclerView() {
        View view = findViewById(R.id.trendsRecyclerView);

        if (this.data != null && view instanceof RecyclerView) {
            this.recyclerViewAdapter = new RVAMealDiary(this.data);
            this.mealRecyclerView = (RecyclerView) view;
            this.mealRecyclerView.setAdapter(this.recyclerViewAdapter);
            this.mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void createActivityCallbackListener() {
        this.pickMeal = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    public void onActivityResult(ActivityResult result) {
                        Intent data;
                        boolean isCustom;
                        int dbkey;

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            data = result.getData();
                            dbkey = data.getExtras().getInt("DBKEY"); //rva recipe book
                            isCustom = data.getExtras().getBoolean("IsCustom");

                            opExec.addByKey(dbkey, isCustom);
                            updateLiveData();
                        }
                    }
                });
    }

    private void copyFilesToDevice(String path) {
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(path, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();
        String[] assetNames;

        try {
            assetNames = assetManager.list(path);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = path + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void copyImagesToDevice() {
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(Main.getImagesPathName(), Context.MODE_PRIVATE);

        try {
            copyFilesToDevice(Main.getImagesPathName());
            Main.setImagesPathName(dataDirectory.toString() + "/" + Main.getImagesPathName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void copyDatabaseToDevice() {
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(Main.getDBName(), Context.MODE_PRIVATE);

        try {
            copyFilesToDevice(Main.getDBName());
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBName());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();
        String[] components;
        String copyPath;
        char[] buffer;
        File outFile;
        int count;

        for (String asset : assets) {
            components = asset.split("/");
            copyPath = directory.toString() + "/" + components[components.length - 1];
            buffer = new char[1024];
            outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


    protected void onDestroy() {
        super.onDestroy();
        Main.saveDB();
        Main.shutDown();
    }


    public void selectDate() {
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");

        datePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {

                    public void onPositiveButtonClick(Object selection) {
                        recyclerViewAdapter.restoreSaved();
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.setTimeInMillis((Long) selection);
                        selectedDate.add(Calendar.DAY_OF_YEAR, 1);
                        opExec.setListDate(selectedDate);
                        updateLiveData();
                    }
                }
        );
    }


    public void prevDate() {
        this.recyclerViewAdapter.restoreSaved();
        this.opExec.prevDate();
        this.updateLiveData();
    }


    public void nextDate() {
        this.recyclerViewAdapter.restoreSaved();
        this.opExec.nextDate();
        this.updateLiveData();
    }

    private void loadEditorView(String intentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentModEntryDialogs editorView = new FragmentModEntryDialogs();
        Bundle args = new Bundle();

        args.putString(Constant.DIALOG_TYPE, intentTag);
        editorView.setArguments(args);
        editorView.show(fragmentManager, FragmentModEntryDialogs.TAG);
    }


    public void editItem() { //launch dialog fragment
        this.loadEditorView(EntryMode.EDIT_QTY.toString());
    }


    public void showGoalEntryDialog() { //launch dialog fragment
        this.loadEditorView(EntryMode.GOAL_CALORIE.toString());
    }


    public void showExerciseEntryDialog() { //launch dialog fragment
        this.loadEditorView(EntryMode.ACTUAL_EXERCISE.toString());
    }


    public void removeItem(int pos) {
        if (pos >= 0 && pos < this.data.size()) {
            this.recyclerViewAdapter.removeItem(pos);
            this.currLog.removeItem(pos);
            this.opExec.logChangedUpdateDB();
            this.updateLiveData();
        }
    }


    public void addEntry(int pos) { //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);
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
            this.recyclerViewAdapter.changeData(this.data);
            this.recyclerViewAdapter.notifyDataSetChanged();
        }
    }


    public String getEntryQty() {
        return String.valueOf(this.recyclerViewAdapter.getSavedItem().getQuantity());
    }


    public Edible.Unit getEntryUnit() {
        return this.recyclerViewAdapter.getSavedItem().getUnit();
    }


    public void setEntryQty(Double amount, String unit) {
        int savedPosition = this.recyclerViewAdapter.getSavedItemPosition();
        Edible savedItem = this.recyclerViewAdapter.getSavedItem();
        EdibleLog selectedItem;

        try {
            if (savedItem instanceof EdibleLog) {
                selectedItem = (EdibleLog) savedItem;
                selectedItem.setQuantity(amount);
                selectedItem.setUnit(Edible.Unit.valueOf(unit));
                selectedItem.setCalories();
                this.currLog.removeItem(savedPosition);
                this.currLog.addEdibleToLog(savedPosition, selectedItem);
                this.recyclerViewAdapter.showContextUI(-1, null);
                this.opExec.logChangedUpdateDB();
                this.updateLiveData();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public String getExerciseCalories() {
        return "" + opExec.getCurrLog().getExerciseActual();
    }


    public void setExerciseCalories(Double value) {
        try {
            this.currLog.setExerciseActual(value);
        } catch (Exception e) {
            System.out.println(e);

        }
        this.updateLiveData();
    }


    public String getGoalCalories() {
        return "" + this.currLog.getCalorieGoal();
    }


    public void setGoalCalories(Double value) {
        try {
            this.currLog.setCalorieGoal(value);
            this.opExec.logChangedUpdateDB();
        } catch (Exception e) {
            System.out.println(e);
        }

        this.updateLiveData();
    }
}