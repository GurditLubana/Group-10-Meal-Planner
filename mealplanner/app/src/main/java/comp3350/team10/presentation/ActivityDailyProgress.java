package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.business.DailyProgressOps;
import comp3350.team10.business.TrendsOps;
import comp3350.team10.objects.DailyLog;
import comp3350.team10.objects.DataFrame;

public class ActivityDailyProgress extends AppCompatActivity {
    private Toolbar toolbar;                    //app title
    private RVATrends recyclerViewAdapter1;
    private ArrayList<DataFrame> data;
    private TrendsOps progOp;
    private RecyclerView trendsRecyclerView;      //Houses a recycle view for diary entries

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_progress);
        this.initToolbar();
        this.initData();
        this.initRecyclerView();
    }
    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("MealPlanner");
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setElevation(0);
    }


    private void initData(){
        this.progOp = new TrendsOps();
        try {
            this.data = this.progOp.getDataFrames(DataFrame.Span.Week);
        }
        catch(Exception e){
            System.out.println(e);
            
        }
    }

    private void initRecyclerView() {
        View object = findViewById(R.id.progressrecyclerview);

        if (this.data != null && object instanceof RecyclerView) {
            this.trendsRecyclerView = (RecyclerView) object;
            this.recyclerViewAdapter1 = new RVATrends(this.data);
            this.trendsRecyclerView.setAdapter(recyclerViewAdapter1);
            this.trendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}