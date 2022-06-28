package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.R;
import comp3350.team10.objects.*;

import android.graphics.Color;
import android.os.Bundle;

public class ActivityTrends extends AppCompatActivity {
    private RecyclerView trendsRecyclerView;      //Houses a recycle view for diary entries
    private Toolbar toolbar;                    //app title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        this.initToolbar();

    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("MealPlanner");
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setElevation(0);
    }


}