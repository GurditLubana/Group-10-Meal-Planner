package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.datepicker.*;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityMealDiary extends AppCompatActivity implements FragToParent {

    private LinkedList<ListItem> data;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mealRecyclerView;
    private ListItem saved;
    private int savedPos;
    private Calendar date;
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
        getData();
    }

    private void getData() {
        DataAccessStub db = new DataAccessStub();
        db.open("someDB");
        ArrayList<DiaryItem> dbFetch = db.getToday();
        data = new LinkedList();
        data.addAll(dbFetch);
        recyclerViewAdapter = new RecyclerViewAdapter(data);
        mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
        mealRecyclerView.setAdapter(recyclerViewAdapter);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showContextUI(int pos) {
        System.out.println("clicked " + " " + pos);
        if (pos != savedPos && saved != null) {
            data.remove(savedPos);
            data.add(savedPos, saved);
        }
        if (data.get(pos).getFragmentType() == ListItem.FragmentType.diaryEntry) {
            saved = data.remove(pos);
            savedPos = pos;
            data.add(pos, new DiaryItem(0, ListItem.FragmentType.diaryModify, "null", 00, ListItem.Unit.g, 0, "noIcon"));
        } else {
            data.remove(pos);
            data.add(pos, saved);
            saved = null;
        }
        //mealRecyclerView.removeViewAt(pos);
        recyclerViewAdapter.notifyItemRemoved(pos);
        recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void navClick() {

    }

    @Override
    public void hideContextUI(Fragment fragment) {

    }

    @Override
    public void setData(View view) {

    }

    @Override
    public void selectDate(){
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

    }

    @Override
    public void nextDate(){

    }

    @Override
    public void setGoal(){

    }
}