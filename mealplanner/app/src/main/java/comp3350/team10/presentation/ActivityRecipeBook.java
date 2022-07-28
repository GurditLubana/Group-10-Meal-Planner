package comp3350.team10.presentation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.application.Main;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {

    private Animation fabOpen, fabClose, rotateForward, rotateBackward; //Animations for floating buttons
    private FloatingActionButton openFab, editFab, addFab;              //Floating buttons
    private boolean modMenuIsOpen;                                      //If the add recipes button is toggled

    private ActivityResultLauncher<Intent> pickIngredient;  //Activity used to select an ingredient when adding a recipe
    private Toolbar toolbar;                                //Activity header
    private int currTab;                                    //The tab that is currently displayed

    private RVARecipeBook recyclerViewAdapter;              //Houses the logic for a recycle view with recipes
    private RecyclerView recipeRecyclerView;                //Houses a recycle view for recipes
    private ArrayList<Edible> data;                         //The data for the recipe book
    private RecipeBookOps opExec;                           //Buisness logic for RecipeBook


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        this.modMenuIsOpen = false;
        this.currTab = 0;

        this.initToolbar();
        this.initLiveData();
        this.initRecyclerView();
        this.setTabListeners();
        this.initActionButtons();
        this.createActivityCallbackListener();
    }

    private void initToolbar() {
        View toolbar = findViewById(R.id.toolbar);

        if (toolbar instanceof Toolbar) {
            this.toolbar = (Toolbar) toolbar;
            this.toolbar.setTitleTextColor(Color.parseColor(Constant.TITLE_COLOR));
            this.toolbar.setTitle(Constant.TITLE_CONTENT);
            this.toolbar.setElevation(0);
        }
    }

    private void initLiveData() {
        this.opExec = new RecipeBookOps();
        this.data = opExec.getFoodRecipes();
    }

    private void initRecyclerView() {
        View view = findViewById(R.id.recipeRecyclerView);

        if (this.data != null && view instanceof RecyclerView) {
            this.recyclerViewAdapter = new RVARecipeBook(this.data);
            this.recipeRecyclerView = (RecyclerView) view;
            this.recipeRecyclerView.setAdapter(recyclerViewAdapter);
            this.recipeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    private void setTabListeners() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            public void onTabSelected(TabLayout.Tab tab) {
                currTab = tab.getPosition();
                if (currTab == 0) {
                    data = opExec.getFoodRecipes();
                } else if (currTab == 1) {
                    data = opExec.getMealRecipes();
                } else {
                    data = opExec.getDrinkRecipes();
                }

                recyclerViewAdapter.restoreSaved();
                updateRVA();
            }


            public void onTabUnselected(TabLayout.Tab tab) {
            }


            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    protected void onDestroy() {
        super.onDestroy();
        Main.saveDB();
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

            public void onClick(View view) {
                animateButton();
            }
        });

        this.editFab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                animateButton();
            }
        });

        this.addFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentRecipeBookDialogs addRecipe = new FragmentRecipeBookDialogs();
                Bundle args = new Bundle();

                if (currTab == 0) {
                    args.putString(Constant.DIALOG_TYPE, EntryMode.ADD_FOOD.toString());
                } else if (currTab == 1) {
                    args.putString(Constant.DIALOG_TYPE, EntryMode.ADD_MEAL.toString());
                } else if (currTab == 2) {
                    args.putString(Constant.DIALOG_TYPE, EntryMode.ADD_DRINK.toString());
                }

                addRecipe.setArguments(args);
                addRecipe.show(fragmentManager, FragmentRecipeBookDialogs.TAG);
            }
        });
    }

    private void animateButton() {
        if (this.modMenuIsOpen) {
            this.openFab.startAnimation(rotateForward);
            this.editFab.startAnimation(fabClose);
            this.addFab.startAnimation(fabClose);
            this.addFab.setClickable(false);
            this.addFab.setVisibility(View.INVISIBLE);
            this.editFab.setClickable(false);
            this.modMenuIsOpen = false;
        } else {
            this.openFab.startAnimation(rotateBackward);
            this.editFab.startAnimation(fabOpen);
            this.addFab.startAnimation(fabOpen);
            this.addFab.setClickable(true);
            this.addFab.setVisibility(View.VISIBLE);
            this.editFab.setClickable(true);
            this.modMenuIsOpen = true;
        }
    }

    public void addToMealDiary() {
        Intent intent = new Intent();
        boolean isCustom = false;
        int dbkey = -1;
        Edible savedItem = this.recyclerViewAdapter.getSavedItem();

        if (savedItem != null) {
            dbkey = savedItem.getDbkey();
            isCustom = savedItem.getIsCustom();
        }

        intent.putExtra("DBKEY", dbkey);
        intent.putExtra("IsCustom", isCustom);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void updateRVA() {
        if (this.recyclerViewAdapter != null) {
            this.recyclerViewAdapter.changeData(this.data);
            this.recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    private FragmentRecipeBookDialogs getAddRecipeFragment() {
        FragmentManager fm = getSupportFragmentManager();
        return (FragmentRecipeBookDialogs) fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);
    }

    public void removeItem(int position) {
        getAddRecipeFragment().removeItem(position);
    }

    public String getEntryQty() {
        return getAddRecipeFragment().getEntryQty();
    }

    public Edible.Unit getEntryUnit() {
        return getAddRecipeFragment().getEntryUnit();
    }

    public void editEntry(Double value, String unit, boolean isSubstitute) {
        getAddRecipeFragment().editEntry(value, unit, isSubstitute);
    }

    public boolean getIsSubstitute() {
        return getAddRecipeFragment().getIsChecked();
    }

    public void addEntry(int pos) { //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);
        this.pickIngredient.launch(intent);
    }

    private void createActivityCallbackListener() {
        this.pickIngredient = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                public void onActivityResult(ActivityResult result) {
                    FragmentRecipeBookDialogs addRecipe = getAddRecipeFragment();
                    Intent data;
                    Edible currEdible;
                    boolean isCustom;
                    int dbkey;

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        data = result.getData();
                        dbkey = data.getExtras().getInt("DBKEY"); //rva recipe book
                        isCustom = data.getExtras().getBoolean("isCustom");
                        currEdible = opExec.findIngredient(currTab, dbkey, isCustom);
                        addRecipe.loadIngredients(currEdible);
                    }
                }
            });
    }

    public void addDrink(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
                         boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo, String instructions, ArrayList<DrinkIngredient> ingredients) {
        if (ingredients.size() > 0) {
            this.opExec.addPreparedDrink(name, desc, qty, unit, photo, instructions, ingredients);
        } else {
            this.opExec.addSimpleDrink(name, desc, qty, unit, calories, protein, carbs, fat, alcoholic, spicy, vegan, vegetarian, glutenFree, photo);
        }

        this.data = this.opExec.getDrinkRecipes();
        this.updateRVA();
    }

    public void addFood(String name, String desc, int qty, Edible.Unit unit, int calories, int protein, int carbs, int fat, boolean alcoholic,
                        boolean spicy, boolean vegan, boolean vegetarian, boolean glutenFree, String photo) {

        opExec.addFood(name, desc, qty, unit, calories, protein, carbs, fat, alcoholic, spicy, vegan, vegetarian, glutenFree, photo);
        this.data = this.opExec.getFoodRecipes();
        this.updateRVA();
    }

    public void addMeal(String name, String desc, int qty, Edible.Unit unit, String photo, String instructions, ArrayList<Ingredient> ingredients) {
        this.opExec.addMeal(name, desc, qty, unit, photo, instructions, ingredients);
        this.data = opExec.getMealRecipes();
        this.updateRVA();
    }


    public String getIntentExtra(String key) {
        Intent intent = getIntent();
        return intent.getStringExtra(key);
    }

    public void loadEditorView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentModEntryDialogs editorView = new FragmentModEntryDialogs();
        Bundle args = new Bundle();

        if (this.currTab == Constant.DRINK) {
            args.putString(Constant.DIALOG_TYPE, EntryMode.DRINK_INGREDIENT.toString());
        } else {
            args.putString(Constant.DIALOG_TYPE, EntryMode.INGREDIENT.toString());
        }

        editorView.setArguments(args);
        editorView.show(fm, FragmentModEntryDialogs.TAG);
    }
}