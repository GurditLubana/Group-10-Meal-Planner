package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.R;
import comp3350.team10.business.TrendsOps;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.SharedDB;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ActivityTrends extends AppCompatActivity implements FragToTrends{
    private RecyclerView trendsRecyclerView;      //Houses a recycle view for diary entries
    private RVATrends recyclerViewAdapter;
    private ArrayList<DataFrame> data;
    private TrendsOps opExec;
    private Toolbar toolbar;                    //app title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
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
        this.opExec = new TrendsOps(SharedDB.getSharedDB());
        try {
            this.data = this.opExec.getDataFrames(DataFrame.Span.Week);
        }
        catch(Exception e){
            System.out.println(e);
            System.exit(1);
        }
    }

    private void initRecyclerView() {
        View object = findViewById(R.id.trendsRecyclerView);

        if (this.data != null && object instanceof RecyclerView) {
            this.trendsRecyclerView = (RecyclerView) object;
            this.recyclerViewAdapter = new RVATrends(this.data);
            this.trendsRecyclerView.setAdapter(recyclerViewAdapter);
            this.trendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }


}