package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.R;
import android.os.Bundle;

public class MealDiary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        getData();
    }

    private void getData() {
        String[] data = {"one"};
        MealCustomAdapter mealCustomAdapter = new MealCustomAdapter(data);
        RecyclerView rv = (RecyclerView) findViewById(R.id.mealRecyclerView);
        rv.setAdapter(mealCustomAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}