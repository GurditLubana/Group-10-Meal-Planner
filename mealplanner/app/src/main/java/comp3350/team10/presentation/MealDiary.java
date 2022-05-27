package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.R;
import android.os.Bundle;

import java.util.LinkedList;

public class MealDiary extends AppCompatActivity {

    private LinkedList data;
    private MealCustomAdapter mealCustomAdapter;
    private RecyclerView mealRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        getData();
    }

    private void getData() {
        //String[] data = {"one"};
        data = new LinkedList();
        mealCustomAdapter = new MealCustomAdapter(data);
        mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
        mealRecyclerView.setAdapter(mealCustomAdapter);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}