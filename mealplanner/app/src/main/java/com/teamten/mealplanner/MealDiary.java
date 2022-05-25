package com.teamten.mealplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MealDiary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
    }

    private void getData() {
        String[] data = {"one"};
        MealCustomAdapter mealCustomAdapter = new MealCustomAdapter(data);

    }
}