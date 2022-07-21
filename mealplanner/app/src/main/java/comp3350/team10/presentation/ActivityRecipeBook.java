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
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;

public class ActivityRecipeBook extends AppCompatActivity implements FragToRecipeBook {
    private ActivityResultLauncher<Intent> pickIngredient;
    private final static int TITLE_COLOR = Color.WHITE;        //The title color of the acitivty
    private final static String TITLE_CONTENT = "MealPlanner"; //The title content of the activity

    private Animation fabOpen, fabClose, rotateForward, rotateBackward; //Animations for floating buttons
    private FloatingActionButton openFab, editFab, addFab;              //Floating buttons

    private RVARecipeBook recyclerViewAdapter;      // Houses the logic for a recycle view with recipes
    private RecyclerView recipeRecyclerView;        // Houses a recycle view for recipes
    private RecipeBookOps opExec;                   // Buisness logic for RecipeBook
    private Toolbar toolbar;                        // app title
    private ArrayList<Edible> data;                 // The data for the recipe book
    private Edible modifyUICard;

    private final String RECIPEMODIFYCARD = "recipeModify";

    private boolean modMenuIsOpen;                  // Represents whether the menu to add/edit recipes is toggled on
    private int savedItemPosition;                  // Saves the position of an item for temporary removal
    private Edible savedItem;                       // Saves the item for temporary removal
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
        this.initUICardObjects();
        this.initLiveData();
        this.initRecyclerView();
        this.setTabListeners();
        this.initActionButtons();
        this.createActivityCallbackListener();
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
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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

    @Override
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

    private void initUICardObjects() {
        this.savedItemPosition = -1;
        this.modifyUICard = new Edible();

        try {
            this.modifyUICard.setName(RECIPEMODIFYCARD);
        } catch (Exception e) {
            System.out.println(e);

        }
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

    public void showContextUI(int position) {
        int otherPosition = -1;
        if (position >= 0 && position != this.savedItemPosition) {
            if (this.savedItem == null) {
                saveItem(position);
            } else {
                otherPosition = this.savedItemPosition;
                swapSaved(position);
                this.recyclerViewAdapter.notifyItemChanged(otherPosition);
            }
            this.data.remove(position);
            this.data.add(position, modifyUICard);
        } else {
            restoreSaved();
        }
        this.recyclerViewAdapter.notifyItemChanged(position);
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    private void restoreSaved() {
        if (savedItem != null) {
            this.data.remove(this.savedItemPosition);
            this.data.add(this.savedItemPosition, this.savedItem);
            this.savedItemPosition = -1;
            this.savedItem = null;
        }
    }

    private void saveItem(int position) {
        this.savedItemPosition = position;
        this.savedItem = this.data.get(position);
    }

    private void swapSaved(int position) {
        Edible temp = this.data.get(position);
        restoreSaved();
        this.savedItemPosition = position;
        this.savedItem = temp;
    }

    @Override
    public void addToMealDiary() {
        Intent intent = new Intent();
        boolean isCustom = false;
        int dbkey = -1;

        if (this.savedItem != null) {
            dbkey = this.savedItem.getDbkey();  //this is against all
            isCustom = this.savedItem.getIsCustom();
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

    public void removeItem(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);

        addRecipe.removeItem(position);
    }

    public String getEntryQty() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);
        return addRecipe.getEntryQty();
    }

    public Edible.Unit getEntryUnit() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);
        return addRecipe.getEntryUnit();
    }

    public void editEntry(Double value, String unit, boolean isSubstitute) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);

        addRecipe.editEntry(value, unit, isSubstitute);
    }

    public void editItem() {
        loadEditorView();
    }

    public void addEntry(int pos) { //launch recipebook use ActivityResultLauncher to allow data passing
        Intent intent = new Intent(this, ActivityRecipeBook.class);
        this.pickIngredient.launch(intent);
    }

    private void createActivityCallbackListener() {
        this.pickIngredient = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);
                        Intent data;
                        Edible currEdible;
                        boolean isCustom;
                        int dbkey;

                        if (result.getResultCode() == Activity.RESULT_OK) {
                            data = result.getData();
                            dbkey = data.getExtras().getInt("DBKEY"); //rva recipe book
                            isCustom = data.getExtras().getBoolean("isCustom");
                            currEdible = opExec.findIngredient(dbkey, isCustom);
                            addRecipe.loadIngredients(currEdible);
                        }
                    }
                });
    }

    public void showIngredientContextUI(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);

        addRecipe.showContextUI(position);
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

    private void loadEditorView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);
        FragmentModEntryDialogs editorView = new FragmentModEntryDialogs();
        Bundle args = new Bundle();

        if(addRecipe.isModdingDrinkIngredients()) {
            args.putString("type", "recipeBook");
        }
        else {
            args.putString("type", "DRINK_INGREDIENT");
        }

        editorView.setArguments(args);
        editorView.show(fm, FragmentModEntryDialogs.TAG);
    }

    public boolean getIsSubstitute() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentRecipeBookDialogs addRecipe = (FragmentRecipeBookDialogs)fm.findFragmentByTag(FragmentRecipeBookDialogs.TAG);

        return addRecipe.getIsChecked();
    }
}
