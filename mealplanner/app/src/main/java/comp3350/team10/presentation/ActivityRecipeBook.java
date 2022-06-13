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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import comp3350.team10.R;
import comp3350.team10.business.RecipeBookOps;

import comp3350.team10.objects.*;
import comp3350.team10.persistence.SharedDB;

import java.util.LinkedList;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {

    private FloatingActionButton openFab, editFab, addFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private boolean isOpen = false;
    private static int currTab;
//    public static final String Edible = "Edible";
//    public static final String Calories = "Calories";
//    public static final String Quantity = "Quantity";
//    private String fName ;
//    private int fCalories;
//    private int fQuantity;
//    FragToRecipeBook send;



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
        executeFab();// make floating action button work.



    }


    private void executeFab()
    {

        openFab = findViewById(R.id.openButton);
        addFab = findViewById(R.id.addButton);
        editFab = findViewById(R.id.editButton);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.button_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.button_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_button);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotatebackwards_button);

        openFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton();
            }
        });

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton();
            }
        });

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currTab == 0) //food tab
                {
                    new AddRecipe().show(
                            getSupportFragmentManager(), AddRecipe.TAG

                    );

                }

                else if(currTab == 1)
                {
                    new AddMeals().show(
                            getSupportFragmentManager(), AddMeals.TAG);
                }

                else if(currTab == 2)
                {
                    new AddDrinks().show(
                            getSupportFragmentManager(), AddDrinks.TAG

                    );
                }

                else{animateButton();}
            }
        });

    }


    private void animateButton()
    {
        if(isOpen){
            openFab.startAnimation(rotateForward);
//            editFab.startAnimation(fabClose);
            addFab.startAnimation(fabClose);
            addFab.setClickable(false);
//            editFab.setClickable(false);
            isOpen = false;

        }

        else
        {
            openFab.startAnimation(rotateBackward);
//            editFab.startAnimation(fabOpen);
            addFab.startAnimation(fabOpen);
            addFab.setClickable(true);

//            editFab.setClickable(true);
            isOpen = true;

        }
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
                currTab = tab.getPosition();
                if(currTab == 0){
                    data = opExec.getFoodRecipes();
                }
                else if(currTab == 1){
                    data = opExec.getMealRecipes();
                }
                else{
                    data = opExec.getDrinkRecipes();
                }
                updateRVA();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                currTab = tab.getPosition();
                System.out.println("unselected");
                System.out.println("selected: " + tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {    //this can probably be removed?
                currTab = tab.getPosition();
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
    public void addDrink(String name, int iconPath, int calories, DrinkIngredient[] ingredients, String [] instructions, ListItem.Unit baseUnit, int quantity) {
        // do input validation then pass to ops

        opExec.addDrink(name,iconPath,calories,instructions,ingredients,baseUnit,quantity); //add appropriate objects here
        data = opExec.getDrinkRecipes();
        updateRVA();
    }

    @Override
    public void addFood(String name, int iconPath, int calories, ListItem.Unit baseUnit, int quantity) { //change this to correct signature
        // do input validation then pass to ops
        opExec.addFood(name, iconPath, calories, baseUnit, quantity);
        data = opExec.getFoodRecipes();
        updateRVA();
    }

    @Override
    public void addMeal(String name, int iconPath, int calories, MealIngredient[] ingredients, String [] instructions, ListItem.Unit baseUnit, int quantity)  { //change this to correct signature

        opExec.addMeal(name,iconPath,calories,ingredients,instructions,baseUnit,quantity); //add appropriate objects here
        data = opExec.getMealRecipes();
        updateRVA();
    }

}
