package comp3350.team10.presentation;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import comp3350.team10.R;
import comp3350.team10.business.MealDiaryOps;
import comp3350.team10.business.RecipeBookOps;

import comp3350.team10.objects.*;

import java.util.LinkedList;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recipeRecyclerView;
    private static LinkedList<ListItem> data;
    private RecipeBookOps opExec = new RecipeBookOps();
    private ListItem saved;
    private int savedPos;
    private Toolbar toolbar;
    private FloatingActionButton openFab, editFab, addFab;
    private Animation fabOpen, fabClose, rotateForward, rotateBackward;
    private boolean isOpen = false;
    private static int currTab;
    public static final String Edible = "Edible";
    public static final String Calories = "Calories";
    public static final String Quantity = "Quantity";
    private String fName ;
    private int fCalories;
    private int fQuantity;




    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipe_book);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        //setActionBar(toolbar);
//        toolbar.setTitle("MealPlanner");
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setElevation(0);
//        setTabListeners();
//        init();
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        initToolbar();
        opExec = new RecipeBookOps();
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
                    //Intent i = new Intent(ActivityRecipeBook.this, AddRecipe.class);
                    //addItemToRecipe();
                    //startActivity(i);
                    new AddRecipe().show(
                            getSupportFragmentManager(), AddRecipe.TAG
                    );

                }
                else if(currTab == 1)
                {
                    Intent i = new Intent(ActivityRecipeBook.this, AddMeals.class);
                    startActivity(i);
                }

                else if(currTab == 2)
                {
                    Intent i = new Intent(ActivityRecipeBook.this, AddDrinks.class);
                    startActivity(i);
                }
                else{animateButton();}
            }
        });

    }


    private void animateButton()
    {
        if(isOpen){
            openFab.startAnimation(rotateForward);
            editFab.startAnimation(fabClose);
            addFab.startAnimation(fabClose);
            addFab.setClickable(false);
            editFab.setClickable(false);
            isOpen = false;

        }

        else
        {
            openFab.startAnimation(rotateBackward);
            editFab.startAnimation(fabOpen);
            addFab.startAnimation(fabOpen);
            addFab.setClickable(true);
            editFab.setClickable(true);
            isOpen = true;

        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MealPlanner");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(0);
    }

    private void initLiveData() {
        /*mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class);
        if (opExec.isDataReady()) {
            updateLiveData();
        }*/
        data = opExec.getData(0);

    }

    private void initRecyclerView() {
        if (data != null) {
            recyclerViewAdapter = new RecyclerViewAdapter(data);
            recipeRecyclerView = (RecyclerView) findViewById(R.id.recipeRecyclerView);
            recipeRecyclerView.setAdapter(recyclerViewAdapter);
            recipeRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        } else {
            //throw new Exception("Meal Diary Linked list empty");
        }
    }
    private void setTabListeners(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //tab.getPosition() tab 0 = food, 1 = meal, 2 = drink
                currTab = tab.getPosition();
                data = opExec.getData(tab.getPosition());


                System.out.println("********************** " + data.size());
                recyclerViewAdapter.changeData(data);
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

//    private void init(){
//        MealDiaryLiveData mealDiaryData = new ViewModelProvider(this).get(MealDiaryLiveData.class); //????
//        //diaryOpExec = new MealDiaryOps(mealDiaryData);  //this is why its not working its cause there is 2 instances
//        System.out.println("are we crashing here?");
//        data = opExec.getData(0);    //gets recipe data from db
//        System.out.println("after");
//        recyclerViewAdapter = new RecyclerViewAdapter(data);
//        recipeRecyclerView = (RecyclerView)findViewById(R.id.recipeRecyclerView);
//        recipeRecyclerView.setAdapter(recyclerViewAdapter);
//        recipeRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
//    }

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

    private void addItemToRecipe()
    {

        if(currTab == 0)
        {
            System.out.println("The size is   ---------------" + data.size());
            Intent intent = getIntent();
            fName = intent.getStringExtra(Edible);
            fCalories = intent.getIntExtra(Calories,0);
            fQuantity = intent.getIntExtra(Quantity,0);
            if(fName != null)
            {
//                Food food = new Food(fName,R.drawable.apple, fCalories, ListItem.FragmentType.recipe, ListItem.Unit.g, fQuantity, 24);
                Food food = new Food(fName,R.drawable.apple, 4, ListItem.FragmentType.recipe, ListItem.Unit.g, 69, 24);

                RecipeBookItem r = new RecipeBookItem(food, R.drawable.apple, 3);
                data.add(0,r);
                opExec.insertItem(0,food);

                recyclerViewAdapter.notifyItemInserted(0);
                recyclerViewAdapter.notifyItemRangeChanged(0, data.size());
                recyclerViewAdapter.notifyDataSetChanged();
                fName= null;

                //fName = null;
            }

        }
//        else if(currTab == 2) // still needs to complete
//        {
//            Intent intent = getIntent();
//            fName = intent.getStringExtra(Edible);
//            fCalories = intent.getIntExtra(Calories,0);
//            fQuantity = i.getIntExtra(Quantity,0);
//            if(fName != null) {
//                Food food = new Food(fName, 0, fCalories, ListItem.FragmentType.recipe, ListItem.Unit.g, fQuantity, 24);
//                RecipeBookItem r = new RecipeBookItem(food, R.drawable.apple, 3);
//                data.add(0, r);
//                recyclerViewAdapter.notifyItemInserted(0);
//                fName = null;
//            }
//        }
    }

    public void addFoodEntry(int pos){
        Intent intent = new Intent();
        intent.putExtra("FoodItem", pos); //
        setResult(RESULT_OK, intent);
        finish();
    };




}
