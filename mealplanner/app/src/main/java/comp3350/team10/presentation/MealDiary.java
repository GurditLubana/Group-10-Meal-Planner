package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import comp3350.team10.R;
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