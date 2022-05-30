package comp3350.team10.presentation;

import comp3350.team10.R;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MealPlanner");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(0);
        //getSupportActionBar().setElevation(0);
    }

    public void openMealDiary(View view){
        Intent myIntent = new Intent(MainActivity.this, ActivityMealDiary.class);
        MainActivity.this.startActivity(myIntent);
    }
}
