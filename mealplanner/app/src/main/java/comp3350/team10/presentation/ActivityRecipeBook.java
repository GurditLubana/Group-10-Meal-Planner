package comp3350.team10.presentation;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import comp3350.team10.R;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.SharedDB;

import java.util.LinkedList;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {
    private RVARecipeBook recyclerViewAdapter;        // handles card layouts and card button listeners
    private RecyclerView recipeRecyclerView;         // handles showing card lists
    private LinkedList<ListItem> data;               // our list of data
    private RecipeBookOps opExec;                    // business class to handle calculations and db operations
    private ListItem saved;                          // variable to store items from the list to show context UI cards
    private int savedPos;                            // position in the list of the saved variable
    private Toolbar toolbar;                         // our app toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        initToolbar();
        initLiveData();
        initRecyclerView();
        setTabListeners();
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("MealPlanner");
        this.toolbar.setTitleTextColor(Color.WHITE);
        this.toolbar.setElevation(0);
    }

    private void initLiveData() {
        this.opExec = new RecipeBookOps(SharedDB.getSharedDB());
        data = opExec.getFoodRecipes();
    }

    private void initRecyclerView() {
        if (this.data != null) {
            this.recyclerViewAdapter = new RVARecipeBook(data);
            this.recipeRecyclerView = (RecyclerView) findViewById(R.id.recipeRecyclerView);
            this.recipeRecyclerView.setAdapter(recyclerViewAdapter);
            this.recipeRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }
    }
    private void setTabListeners(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //tab.getPosition() tab 0 = food, 1 = meal, 2 = drink
                //data = opExec.getData(tab.getPosition());
                //recyclerViewAdapter.changeData(data);
                if(tab.getPosition() == 0){
                    data = opExec.getFoodRecipes();
                }
                else if(tab.getPosition() == 1){
                    data = opExec.getMealRecipes();
                }
                else{
                    data = opExec.getDrinkRecipes();
                }
                updateRVA();
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

    @Override
    public void showContextUI(int pos) { //do a thing here to take an object as well?
        if (pos != this.savedPos && this.saved != null) {
            this.data.remove(this.savedPos);
            this.data.add(this.savedPos, this.saved);
        }
        if (this.data.get(pos).getFragmentType() != ListItem.FragmentType.cardSelection) {
            this.saved = this.data.remove(pos);
            this.savedPos = pos;
            this.data.add(pos, new Food("",0,0,ListItem.FragmentType.cardSelection, null, 0, 0));
        } else {
            this.data.remove(pos);
            this.data.add(pos, this.saved);
            this.saved = null;
        }
        //mealRecyclerView.removeViewAt(pos);
        this.recyclerViewAdapter.notifyItemRemoved(pos);
        this.recyclerViewAdapter.notifyItemRangeChanged(pos, data.size());
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToMealDiary(int pos){
        Intent intent = new Intent();
        int dbkey = -1;
        if(saved != null) {
            dbkey =  ((Edible) saved).getDbkey();
        }
        intent.putExtra("DBKEY", dbkey); //
        setResult(RESULT_OK, intent);
        finish();
    };

    private void updateRVA(){
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.changeData(data);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addDrink() {  //change this to correct signature
        // do input validation then pass to ops
        //opExec.addDrink(); //add appropriate objects here
        data = opExec.getDrinkRecipes();
        updateRVA();
    }

    @Override
    public void addFood() { //change this to correct signature
        // do input validation then pass to ops
        //opExec.addFood(); //add appropriate objects here
        data = opExec.getFoodRecipes();
        updateRVA();
    }

    @Override
    public void addMeal() { //change this to correct signature
        // do input validation then pass to ops
        //opExec.addMeal(); //add appropriate objects here
        data = opExec.getMealRecipes();
        updateRVA();
    }
}
