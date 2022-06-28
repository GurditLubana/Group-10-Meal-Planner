package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.*;
import comp3350.team10.persistence.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {
    private final static int TITLE_COLOR = Color.WHITE; //The title color of the acitivty
    private final static String TITLE_CONTENT = "MealPlanner"; //The title content of the activity

    private static enum EDIBLE_TYPES {FOOD, MEAL, DRINK}; //The different types of food types

    private Animation fabOpen, fabClose, rotateForward, rotateBackward; //Animations for floating buttons
    private FloatingActionButton openFab, editFab, addFab;              //Floating buttons

    private RVARecipeBook recyclerViewAdapter;      // Houses the logic for a recycle view with recipes
    private RecyclerView recipeRecyclerView;        // Houses a recycle view for recipes
    private RecipeBookOps opExec;                   // Buisness logic for RecipeBook
    private Toolbar toolbar;                        // app title

    private ArrayList<Edible> data;                // The data for the recipe book
    private boolean modMenuIsOpen;                  // Represents whether the menu to add/edit recipes is toggled on
    private int savedPosition;                      // Saves the position of an item for temporary removal
    private Edible saved;                           // Saves the item for temporary removal
    private int currTab;                            // The tab that is currently displayed
    private EntryMode mode;                         // The type of dialog to show
    private boolean detailsFlag = false;            // flag to show detailed recipes

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
        this.initActionButtons();       //Make floating action button work.
    }

    private void initToolbar() {
        View object = findViewById(R.id.toolbar);

        if (object instanceof Toolbar) {
            this.toolbar = (Toolbar) object;
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
        //ArrayList<ListItem> tempList = convertToListItem(this.data);

        if (this.data != null && object instanceof RecyclerView) {
            this.recipeRecyclerView = (RecyclerView) object;
            this.recyclerViewAdapter = new RVARecipeBook(this.data);
            this.recipeRecyclerView.setAdapter(recyclerViewAdapter);
            this.recipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    private ArrayList<ListItem> convertToListItem(ArrayList<Edible> currList) {
        ArrayList<ListItem> tempList = new ArrayList<ListItem>();

        for(int i = 0; i < currList.size(); i++) {
            if(currList.get(i) instanceof ListItem) {
                tempList.add(this.data.get(i));
            }
        }

        return tempList;
    }

    private void setTabListeners() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //tab.getPosition() tab 0 = food, 1 = meal, 2 = drink
                currTab = tab.getPosition();
                if (currTab == 0) {
                    data = opExec.getFoodRecipes();
                } else if (currTab == 1) {
                    data = opExec.getMealRecipes();
                } else {
                    data = opExec.getDrinkRecipes();
                }

                updateRVA();
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

    private void initActionButtons() {
        this.openFab = findViewById(R.id.openButton);
        this.addFab = findViewById(R.id.addButton);
        this.editFab = findViewById(R.id.editButton);
        this.editFab.setVisibility(View.GONE);

        this.fabOpen = AnimationUtils.loadAnimation(this, R.anim.button_open);
        this.fabClose = AnimationUtils.loadAnimation(this, R.anim.button_close);
        this.rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_button);
        this.rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotatebackwards_button);

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
                if (currTab == 0) {
                    mode = EntryMode.ADD_FOOD;
                } else if (currTab == 1) {
                    mode = EntryMode.ADD_MEAL;
                } else if (currTab == 2) {
                    mode = EntryMode.ADD_DRINK;
                } else {
                    animateButton();
                }

                new FragmentRecipeBookDialogs().show(getSupportFragmentManager(), FragmentRecipeBookDialogs.TAG);
            }
        });
    }

    private void animateButton() {
        if (this.modMenuIsOpen) {
            this.openFab.startAnimation(rotateForward);
            this.editFab.startAnimation(fabClose);
            this.addFab.startAnimation(fabClose);
            this.addFab.setClickable(false);
            this.editFab.setClickable(false);
            this.modMenuIsOpen = false;
        } else {
            this.openFab.startAnimation(rotateBackward);
            this.editFab.startAnimation(fabOpen);
            this.addFab.startAnimation(fabOpen);
            this.addFab.setClickable(true);
            this.editFab.setClickable(true);
            this.modMenuIsOpen = true;
        }
    }

    @Override
    public void showContextUI(int position) {
        Food modifyUIcard = new Food();
        if (position != this.savedPosition && this.saved != null) {
            this.data.remove(this.savedPosition);
            this.data.add(this.savedPosition, this.saved);
        }

        if (this.data.get(position).getFragmentType() != ListItem.FragmentType.cardSelection) {
            this.saved = this.data.remove(position);
            this.savedPosition = position;

            try {
                modifyUIcard.setFragmentType(ListItem.FragmentType.cardSelection);
                this.data.add(position, modifyUIcard);
            }
            catch(Exception e) {
                System.out.println(e);
                System.exit(1);
            }
        } 
        else {
            this.data.remove(position);
            this.data.add(position, this.saved);
            this.saved = null;
        }

        this.recyclerViewAdapter.notifyItemRemoved(position);
        this.recyclerViewAdapter.notifyItemRangeChanged(position, data.size());
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToMealDiary() {
        Intent intent = new Intent();
        int dbkey = -1;

        if (this.saved != null) {
            dbkey = this.saved.getDbkey();
        }

        intent.putExtra("DBKEY", dbkey);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void updateRVA() {
        //ArrayList<ListItem> tempList;

        if (this.recyclerViewAdapter != null) {
            //tempList = convertToListItem(this.data);
            this.recyclerViewAdapter.changeData(this.data);
            this.recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public void addDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
            boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, byte[] photo, String instructions, ArrayList<DrinkIngredient> ingredients) {
        if(ingredients.size() > 0) {
            this.opExec.addPreparedDrink(name, desc, qty, unit, photo, instructions, ingredients);
        }
        else {
            this.opExec.addSimpleDrink(name, desc, qty, unit, calories, protein, carbs, fat, alcoholic, spicy, vegan, vegetarian, glutenFree, photo);
        }

        this.data = this.opExec.getDrinkRecipes();
        this.updateRVA();
    }

    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
            boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, byte[] photo) {

        opExec.addFood(name, desc, qty, unit, calories, protein, carbs, fat, alcoholic, spicy, vegan, vegetarian, glutenFree, photo);
        this.data = this.opExec.getFoodRecipes();
        this.updateRVA();
    }

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, byte[] photo, String instructions, ArrayList<Ingredient> ingredients) {
        this.opExec.addMeal(name, desc, qty, unit, photo, instructions, ingredients);
        this.data = opExec.getMealRecipes();
        this.updateRVA();
    }

    public EntryMode getEntryMode() {
        return this.mode;
    }

    @Override
    public String getIntentExtra(String key) {
        Intent intent = getIntent();
        return intent.getStringExtra(key);
    }

    @Override
    public void showDetails() {
        this.detailsFlag = true;
        new FragmentRecipeBookDialogs().show(getSupportFragmentManager(), FragmentRecipeBookDialogs.TAG);
    }

    @Override
    public boolean getDetails() {
        boolean result = this.detailsFlag;
        this.detailsFlag = false;

        return result;
    }
}
