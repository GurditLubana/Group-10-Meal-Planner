package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

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

import java.util.LinkedList;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {
    private final static int TITLE_COLOR = Color.WHITE;
    private final static String TITLE_CONTENT = "MealPlanner";
    private static enum EDIBLE_TYPES {FOOD, MEAL, DRINK}

    private Animation fabOpen, fabClose, rotateForward, rotateBackward; //Animations for floating buttons
    private FloatingActionButton openFab, editFab, addFab;              //Floating buttons

    private RVARecipeBook recyclerViewAdapter;      //Houses the logic for a recycle view with recipes
    private RecyclerView recipeRecyclerView;        //Houses a recycle view for recipes
    private RecipeBookOps opExec;                   //Buisness logic for RecipeBook
    private Toolbar toolbar;                        //Progress bar

    private LinkedList<Edible> data;                //The data for the recipe book
    private boolean modMenuIsOpen;                  //Represents whether the menu to add/edit recipes is toggled on
    private int savedPosi;                          //Saves the position of an item for temporary removal
    private Edible saved;                           //Saves the item for temporary removal
    private int currTab;                            //The tab that is currently displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        this.modMenuIsOpen = false;
        this.currTab = 0;
        this.initToolbar();
        this.initLiveData();
        this.initRecyclerView();
        this.setTabListeners();
        this.executeFab();       //Make floating action button work.
    }


    private void executeFab() {
        this.openFab = findViewById(R.id.openButton);
        this.addFab = findViewById(R.id.addButton);
        this.editFab = findViewById(R.id.editButton);

        //Loads animations
        this.fabOpen = AnimationUtils.loadAnimation(this, R.anim.button_open);
        this.fabClose = AnimationUtils.loadAnimation(this, R.anim.button_close);
        this.rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_button);
        this.rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotatebackwards_button);

        //Adds event listeners
        this.openFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton();
            }
        });

        this.editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateButton();
            }
        });

        this.addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currTab == 0) {
                    new AddRecipe().show(getSupportFragmentManager(), AddRecipe.TAG);
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

    private void animateButton() {
        if(this.modMenuIsOpen) {
            this.openFab.startAnimation(rotateForward);
            this.editFab.startAnimation(fabClose);
            this.addFab.startAnimation(fabClose);
            this.addFab.setClickable(false);
            this.editFab.setClickable(false);
            this.modMenuIsOpen = false;
        }
        else {
            this.openFab.startAnimation(rotateBackward);
            this.editFab.startAnimation(fabOpen);
            this.addFab.startAnimation(fabOpen);
            this.addFab.setClickable(true);
            this.editFab.setClickable(true);
            this.modMenuIsOpen = true;
        }
    }

    private void initToolbar() {
        View object = findViewById(R.id.toolbar);

        if(object instanceof Toolbar) {
            this.toolbar = (Toolbar)object;

            this.toolbar.setTitleTextColor(TITLE_COLOR);
            this.toolbar.setTitle(TITLE_CONTENT);
            this.toolbar.setElevation(0);
        }
    }

    private void initLiveData() {
        this.opExec = new RecipeBookOps(SharedDB.getSharedDB());
        this.data = opExec.getFoodRecipes();
    }

    private void initRecyclerView() {
        View object = findViewById(R.id.recipeRecyclerView);

        if(this.data != null && object instanceof RecyclerView) {
            this.recipeRecyclerView = (RecyclerView)object;
            this.recyclerViewAdapter = new RVARecipeBook(data);
            this.recipeRecyclerView.setAdapter(recyclerViewAdapter);
            this.recipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
                else {
                    data = opExec.getDrinkRecipes();
                }

                updateRVA();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                currTab = tab.getPosition();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });
    }

    @Override
    public void showContextUI(int posi) {
        if(posi != this.savedPosi && this.saved != null) {
            this.data.remove(this.savedPosi);
            this.data.add(this.savedPosi, this.saved);
        }

        if(this.data.get(posi).getFragmentType() != ListItem.FragmentType.cardSelection) {
            this.saved = this.data.remove(posi);
            this.savedPosi = posi;
            this.data.add(posi, new Food("",0,0,ListItem.FragmentType.cardSelection, null, 0, 0));
        }
        else {
            this.data.remove(posi);
            this.data.add(posi, this.saved);
            this.saved = null;
        }

        this.recyclerViewAdapter.notifyItemRemoved(posi);
        this.recyclerViewAdapter.notifyItemRangeChanged(posi, data.size());
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToMealDiary(int posi){
        Intent intent = new Intent();
        int dbkey = -1;

        if(saved != null) {
            dbkey =  ((Edible) saved).getDbkey();
        }

        intent.putExtra("DBKEY", dbkey);
        setResult(RESULT_OK, intent);
        finish();
    };

    private void updateRVA(){
        if(recyclerViewAdapter != null) {
            recyclerViewAdapter.changeData(data);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addDrink(String name, int iconPath, int calories, DrinkIngredient[] ingredients, String [] instructions, ListItem.Unit baseUnit, int quantity) {
        // do input validation then pass to ops

        opExec.addDrink(name,iconPath,calories,instructions,ingredients,baseUnit,quantity); //add appropriate objects here
        data = opExec.getDrinkRecipes();
        this.updateRVA();
    }

    @Override
    public void addFood(String name, int iconPath, int calories, Edible.Unit baseUnit, int quantity) { //change this to correct signature
        // do input validation then pass to ops
        opExec.addFood(name, iconPath, calories, baseUnit, quantity);
        data = opExec.getFoodRecipes();
        this.updateRVA();
    }

    @Override
    public void addMeal(String name, int iconPath, int calories, MealIngredient[] ingredients, String [] instructions, ListItem.Unit baseUnit, int quantity)  { //change this to correct signature

        opExec.addMeal(name,iconPath,calories,ingredients,instructions,baseUnit,quantity); //add appropriate objects here
        data = opExec.getMealRecipes();
        this.updateRVA();
    }
}
