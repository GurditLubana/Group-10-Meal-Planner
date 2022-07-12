package comp3350.team10.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.application.Main;
import comp3350.team10.business.TrendsOps;
import comp3350.team10.objects.DataFrame;

public class ActivityTrends extends AppCompatActivity {
    private RecyclerView trendsRecyclerView;      //Houses a recycle view for diary entries
    private RVATrends recyclerViewAdapter;        //recyclerview
    private ArrayList<DataFrame> data;            //Trend data
    private TrendsOps opExec;                     //Trends logic class
    private Toolbar toolbar;                      //app title
    private int currTab;                          //The tab that is currently displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        this.initToolbar();
        this.initData();
        this.initRecyclerView();
        this.setTabListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Main.saveDB();
//        Main.shutDown();
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("MealPlanner");
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setElevation(0);
    }

    private void initData() {
        this.opExec = new TrendsOps();
        try {
            this.data = this.opExec.getDataFrames(DataFrame.Span.Week);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    private void initRecyclerView() {
        View object = findViewById(R.id.mealRecyclerView);

        if (this.data != null && object instanceof RecyclerView) {
            this.trendsRecyclerView = (RecyclerView) object;
            this.recyclerViewAdapter = new RVATrends(this.data);
            this.trendsRecyclerView.setAdapter(recyclerViewAdapter);
            this.trendsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }


    private void setTabListeners() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currTab = tab.getPosition();
                if (currTab == 0) {
                    data = opExec.getDataFrames(DataFrame.Span.Week);
                } else if (currTab == 1) {
                    data = opExec.getDataFrames(DataFrame.Span.Month);
                } else if (currTab == 2) {
                    data = opExec.getDataFrames(DataFrame.Span.ThreeMonth);
                } else if (currTab == 3) {
                    data = opExec.getDataFrames(DataFrame.Span.SixMonth);
                } else if (currTab == 4) {
                    data = opExec.getDataFrames(DataFrame.Span.Year);
                } else {
                    data = opExec.getDataFrames(DataFrame.Span.All);
                }

                recyclerViewAdapter.changeDataSet(data);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                currTab = tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


}