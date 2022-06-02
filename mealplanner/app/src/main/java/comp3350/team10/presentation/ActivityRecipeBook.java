package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.*;

import java.util.LinkedList;

public class ActivityRecipeBook extends AppCompatActivity implements FragToParent {
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mealRecyclerView;
    private LinkedList<ListItem> data;
    private RecipeBookOps opExec = new RecipeBookOps();
    private MealDiaryOps diaryOpExec;
    private ListItem saved;
    private int savedPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setActionBar(toolbar);
        toolbar.setTitle("MealPlanner");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(0);
        setTabListeners();
        init();
    }

    private void setTabListeners(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //tab.getPosition() tab 0 = food, 1 = meal, 2 = drink
                data = opExec.getData(tab.getPosition());
                recyclerViewAdapter.changeData(data);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                System.out.println("unselected");
                System.out.println("selected: " + tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {    //this can probably be removed?
                System.out.println("reselected");
                System.out.println("selected: " + tab);
            }
        });
    }

    private void init(){
        MealDiaryLiveData mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class); //????
        diaryOpExec = new MealDiaryOps(mealDiaryData);  //this is why its not working its cause there is 2 instances
        System.out.println("are we crashing here?");
        data = opExec.getData(0);    //gets recipe data from db
        System.out.println("after");
        recyclerViewAdapter = new RecyclerViewAdapter(data);
        mealRecyclerView = (RecyclerView)findViewById(R.id.recipeRecyclerView);
        mealRecyclerView.setAdapter(recyclerViewAdapter);
        mealRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public void showContextUI(int pos) { //do a thing here to take an object as well?
        if (pos != savedPos && saved != null) {
            data.remove(savedPos);
            data.add(savedPos, saved);
        }
        if (data.get(pos).getFragmentType() == ListItem.FragmentType.recipe) {
            saved = data.remove(pos);
            savedPos = pos;
            data.add(pos, new DiaryItem(ListItem.FragmentType.cardSelection, null, null, 0));
        } else {
            data.remove(pos);
            data.add(pos, saved);
            saved = null;
        }
        //mealRecyclerView.removeViewAt(pos);
        recyclerViewAdapter.notifyItemRemoved(pos);
        recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void navClick(){

    }
    public void hideContextUI(Fragment fragment){

    }
    public void setData(View view){

    }
    public void selectDate(){

    }
    public void prevDate(){

    }
    public void nextDate(){

    }
    public void setGoal(){

    }

    public void addDiaryItem(DiaryItem item) {
        System.out.println("calling the addDiary thing");
        diaryOpExec.addToDiary(item);
    }
}
