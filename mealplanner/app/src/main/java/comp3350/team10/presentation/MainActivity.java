package comp3350.team10.presentation;

//import comp3350.team10.R;
import comp3350.team10.application.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
    }

    public void openMealDiary(View view){
        Intent myIntent = new Intent(MainActivity.this, MealDiary.class);
        MainActivity.this.startActivity(myIntent);
    }
}
