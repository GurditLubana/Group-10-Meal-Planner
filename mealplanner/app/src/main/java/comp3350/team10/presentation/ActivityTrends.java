package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.R;
import comp3350.team10.business.TrendsOps;
import comp3350.team10.objects.*;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ActivityTrends extends AppCompatActivity {
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
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("MealPlanner");
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setElevation(0);
    }

    private void initData(){
        this.data = this.opExec.getDataFrame();
    }

    private void initRecyclerView() {
        View object = findViewById(R.id.recipeRecyclerView);

        if (this.data != null && object instanceof RecyclerView) {
            this.trendsRecyclerView = (RecyclerView) object;
            this.recyclerViewAdapter = new RVATrends(data);
            this.trendsRecyclerView.setAdapter(recyclerViewAdapter);
            this.trendsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }


}