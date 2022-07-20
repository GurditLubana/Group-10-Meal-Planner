package comp3350.team10.presentation;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Drink;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Ingredient;
import comp3350.team10.objects.Meal;

public class FragmentRecipeBookDialogs extends FragmentDialogCommon {
    private final String DIARYADDCARD = "diaryAdd";
    private TextView labelName;              // Label of name field
    private TextView labelCalories;          // Label of calories field
    private TextView labelIngredients;       // Label of ingredients field
    private EditText inputInstructions;      // input field for instructions
    private EditText inputName;              // input field for item name
    private EditText inputCalories;          // input field for item calories
    private RecyclerView inputIngredients;       // input field for item ingredients
    private Button btnChooseItemImage;       // Import a picture for the Edible item.
    private CheckBox isAlcoholicCheckBox;    // check if Edible item contains alcohol.
    private CheckBox isSpicyCheckBox;        // check if Edible item spicy.
    private CheckBox isVegetarianCheckBox;   // check if Edible item vegetarian.
    private CheckBox isVeganCheckBox;        // check if Edible item vegan.
    private CheckBox isGluteenFree;          // check if Edible item glutenfree.
    private ImageView EdibleItemImage;         // Image of the edible item.
    private ImageView cameraIcon;              // The camera Icon in Add Edible interface.
    private FragToRecipeBook send;             // Interface for communication with parent activity
    private FragToRecipeBook.EntryMode mode;   // the type of dialog to show
    public static String TAG = "AddRecipe";    // tag name of this fragment for reference in the fragment manager
    private int calories;                      // value of calorie input
    private int quantity;                      // value of quantity input
    private String name;                       // value of name input
    private String instructions;               // value of instructions input
    private String photo;                      // value of ingredients input
    private Edible.Unit unit;                  // value of units input
    private static final int REQUEST_CODE = 1; // Request code for the edible's image
    ArrayList<Ingredient> mealIngredients;
    ArrayList<DrinkIngredient> drinkIngredients;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView ingredientRecyclerView;
    Edible addBtn;

    private ArrayList<Ingredient> ingredients;
    private ArrayList<Edible> ingredientEdibles;
    private Edible modifyLog;
    private int savedItemPosition;              //Saves the position of an item for temporary removal
    private Edible savedItem;                   //Saves the item for temporary removal
    private final String DIARYMODIFYCARD = "diaryModify";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedItemPosition = -1;
        modifyLog = new Edible();
        modifyLog.setName(DIARYMODIFYCARD);
        ingredients = new ArrayList<Ingredient>();
        this.ingredientEdibles = new ArrayList<Edible>();

        this.addBtn = new Edible();
        this.addBtn.setName(DIARYADDCARD);
        this.ingredientEdibles.add(this.ingredientEdibles.size(), this.addBtn);
    }


    private void initRecyclerView(View view) {
        View recycler = view.findViewById(R.id.dialogRecipeIngredientsInput);

        if (recycler instanceof RecyclerView) {
            this.recyclerViewAdapter = new RVAAddIngredient(ingredientEdibles);
            this.ingredientRecyclerView = (RecyclerView) recycler;
            this.ingredientRecyclerView.setAdapter(this.recyclerViewAdapter);
            this.ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_recipe_book_dialogs, null);

        Context context = view.getContext();

        super.setTitle(view.findViewById(R.id.dialogRecipeTitle));
        super.setInputQuantity(view.findViewById(R.id.dialogRecipeQuantityInput));
        super.setUnitSpinner(view.findViewById(R.id.dialogRecipeSpinner));
        super.setBtnOk(view.findViewById(R.id.dialogRecipeBtnOk));
        super.setBtnCancel(view.findViewById(R.id.dialogRecipeBtnCancel));
        this.labelName = view.findViewById(R.id.dialogRecipeNameLabel);
        this.labelCalories = view.findViewById(R.id.dialogRecipeCaloriesLabel);
        this.labelIngredients = view.findViewById(R.id.dialogRecipeIngredientsLabel);
        this.inputInstructions = view.findViewById(R.id.dialogRecipeInstructions);
        this.inputName = view.findViewById(R.id.dialogRecipeNameInput);
        this.inputCalories = view.findViewById(R.id.dialogRecipeCaloriesInput);
        this.inputIngredients = view.findViewById(R.id.dialogRecipeIngredientsInput);
        this.btnChooseItemImage = view.findViewById(R.id.dialogRecipePhotoBtn);
        this.EdibleItemImage = view.findViewById(R.id.dialogRecipePhoto);
        this.cameraIcon = view.findViewById(R.id.dialogRecipePhotoIcon);
        this.isAlcoholicCheckBox = view.findViewById(R.id.isAlcoholic);
        this.isSpicyCheckBox = view.findViewById(R.id.isSpicy);
        this.isGluteenFree = view.findViewById(R.id.isGluteenFree);
        this.isVegetarianCheckBox = view.findViewById(R.id.isVegetarian);
        this.isVeganCheckBox = view.findViewById(R.id.isVegan);
        this.isGluteenFree = view.findViewById(R.id.isGluteenFree);
        this.photo = "photo.jpg";
        this.initRecyclerView(view);

        if (context != null && context instanceof FragToRecipeBook) {
            this.send = (FragToRecipeBook) context;
            this.mode = this.send.getEntryMode();
            super.getInputQuantity().setHint("0 - 9999");
            this.inputCalories.setHint("0 - 9999");
            switch (mode) {
                case ADD_FOOD:
                    setFoodDialogFieldDefaults();
                    break;
                case ADD_MEAL:
                    setMealDialogFieldDefaults();
                    break;
                case ADD_DRINK:
                    setDrinkDialogFieldDefaults();
                    break;
            }
            setSpinner();
            setOnClickListeners();
        }

        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return dialog;
    }

    private void setFoodDialogFieldDefaults() {
        super.getTitle().setText("Add New Food");
        this.labelName.setText("Food Name");
        this.inputName.setHint("Food Name");
        this.inputInstructions.setVisibility(View.GONE);
        this.inputIngredients.setVisibility(View.GONE);
        this.labelIngredients.setVisibility(View.GONE);
    }

    private void setMealDialogFieldDefaults() {

        super.getTitle().setText("Add New Meal");
        this.labelName.setText("Meal Name");
        this.inputName.setHint("Meal Name");
        this.inputInstructions.setHint("Cooking Instructions");
    }

    private void setDrinkDialogFieldDefaults() {

        super.getTitle().setText("Add New Drink");
        this.labelName.setText("Drink Name");
        this.inputName.setHint("Drink Name");
        this.inputInstructions.setHint("Mixing Instructions");
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = null;

        super.initSpinner();

        if (super.getUnitSpinner().getAdapter() instanceof ArrayAdapter) {
            adapter = (ArrayAdapter) super.getUnitSpinner().getAdapter();
            super.getUnitSpinner().setSelection(adapter.getPosition(Edible.Unit.serving.name()));
        }

    }

    private void setOnClickListeners() {

        super.getBtnOk().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    sendData();
                    restIngredients();
                    dismiss();
                }
            }
        });

        super.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restIngredients();
                dismiss();
            }
        });

        this.btnChooseItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
                        } else {    
                            openGallery();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    private void restIngredients() {
        this.ingredients.clear();
    }

    @Override
    public void onResume() {
        super.onResume();

        this.ingredientEdibles = new ArrayList<Edible>();
        for(Ingredient currIngredient: this.ingredients) { //build a list based on edibles from ingredients
            ingredientEdibles.add(currIngredient.getIngredient());
        }

        if(!ingredientEdibles.contains(this.addBtn)) {
            ingredientEdibles.add(this.addBtn);
        }

        recyclerViewAdapter.changeData(ingredientEdibles);
    }

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if (result) {
                        openGallery();
                    } else {
                        Toast.makeText(getContext(), "Please allow read external storage permission", Toast.LENGTH_LONG);
                    }
                }
            }
    );

    public void removeItem(int position) {
        if (position >= 0 && position < this.ingredients.size()) {
            this.savedItem = null;
            this.savedItemPosition = -1;
            this.ingredients.remove(position);
            this.ingredientEdibles.remove(position);
            this.recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntentLauncher.launch(galleryIntent);
    }

    ActivityResultLauncher<Intent> galleryIntentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        bitmap(result);

                    }
                }
            });


    private void bitmap(ActivityResult result) {
        Uri imageUri = result.getData().getData();

        EdibleItemImage.setImageURI(imageUri);
        cameraIcon.setVisibility(View.GONE);

    }


    private boolean validateData() {
        int success = 0;

        if (check(this.inputName)) {
            this.name = this.inputName.getText().toString().trim();
            success += 1;
        }
        if (check(this.inputCalories)) {
            this.calories = Integer.parseInt(this.inputCalories.getText().toString().trim());
            success += 1;
        }
        if (check(super.getInputQuantity())) {
            this.quantity = Integer.parseInt(super.getInputQuantity().getText().toString().trim());
            success += 1;
        }

        if (this.mode != FragToRecipeBook.EntryMode.ADD_FOOD) {
            if (true) { //check(this.inputIngredients)
                //String[] tempIngredients = (this.inputIngredients.getText().toString().trim()).split(",");
                //for (int i = 0; i < tempIngredients.length; i++) {
                //    System.out.println(tempIngredients[i]);
                //}

                //this.ingredients = this.inputIngredients.getText().toString().trim();
                success += 1;
            }
            if (check(this.inputInstructions)) {
                this.instructions = this.inputInstructions.getText().toString().trim();
                success += 1;
            }
        }

        this.unit = Edible.Unit.valueOf(super.getUnitSpinner().getSelectedItem().toString());

        return (success == 3 || (this.mode != FragToRecipeBook.EntryMode.ADD_FOOD && success == 5));
    }

    private boolean check(EditText view) {
        boolean result = true;
        String value = null;
        int intValue = 0;

        value = view.getText().toString().trim();
        if (value.length() == 0) {
            view.setError("Field cannot be empty");
            result = false;
        }
        if (result && (view == this.inputCalories)) {
            intValue = Integer.parseInt(value);
            if (intValue < Constant.ENTRY_MIN_VALUE || intValue > Constant.ENTRY_MAX_VALUE) {
                view.setError("Must be between 0 and 9999 inclusive");
                result = false;
            }
        }
        if (result && (view == super.getInputQuantity())) {
            intValue = Integer.parseInt(value);
            if (intValue < 1 || intValue > Constant.ENTRY_MAX_VALUE) {
                view.setError("Must be between 1 and 9999 inclusive");
                result = false;
            }
        }

        return result;
    }

    private void sendData() {
        this.patchData();
        if (this.send != null && this.send instanceof FragToRecipeBook) {
            switch (mode) {
                case ADD_FOOD:
                    this.send.addFood(this.name, this.name, this.quantity, this.unit, this.calories, this.calories,this.calories, this.calories, false, false, false, false, false, this.photo);
                    break;
                case ADD_MEAL:
                    this.send.addMeal(this.name, this.name, this.quantity, this.unit, this.photo, this.instructions, this.mealIngredients);
                    break;
                case ADD_DRINK:
                    this.send.addDrink(this.name, this.name, this.quantity, this.unit, this.calories, this.calories,this.calories, this.calories, false, false, false, false, false, this.photo, this.instructions, this.drinkIngredients);
                    break;
            }
        }
    }

    private void patchData() {
        this.mealIngredients = new ArrayList<>();
        this.drinkIngredients = new ArrayList<>();
        Ingredient ingredient;
        DrinkIngredient drinkIngredient;

        ingredient = new Ingredient().init((new Edible()
                .initDetails(4, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                .initNutrition(400, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "rice.jpg")), 1, Edible.Unit.cups);
        this.mealIngredients.add(ingredient);

        ingredient = new Ingredient().init((new Edible()
                .initDetails(12, "Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                .initNutrition(100, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "bologna.jpg")), 1, Edible.Unit.cups);
        this.mealIngredients.add(ingredient);

        drinkIngredient = new DrinkIngredient();
        drinkIngredient.setIngredient((new Edible()
                .initDetails(4, "Grain of Rice", "rice desc", 400, Edible.Unit.g)
                .initNutrition(400, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "rice.jpg")));
        this.drinkIngredients.add(drinkIngredient);

        drinkIngredient = new DrinkIngredient();
        drinkIngredient.setIngredient((new Edible()
                .initDetails(12, "Bologna", "Bologna desc", 1, Edible.Unit.tsp)
                .initNutrition(100, 30, 20, 50)
                .initCategories(false, false, false, false, false)
                .initMetadata(false, "bologna.jpg")));
        this.drinkIngredients.add(drinkIngredient);

    }

    public void loadIngredients(Edible currEdible) {
        if(currEdible instanceof Meal) {
            for(Ingredient ingredient: ((Meal)currEdible).getIngredients()) {
                addIngredient(ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getQuantityUnits(), false);
            }
        }
        else if(currEdible instanceof Drink && ((Drink) currEdible).getIngredients().size() > 0) {
            for(DrinkIngredient ingredient: ((Drink)currEdible).getIngredients()) {
                addIngredient(ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getQuantityUnits(), ingredient.getIsSubstitute());
            }
        }
        else {
            addIngredient(currEdible, currEdible.getQuantity(), currEdible.getUnit(), false);
        }
    }

    private void addIngredient(Edible currEdible, double quantity, Edible.Unit unit, boolean isSubstitute) {
        DrinkIngredient newIngredient = new DrinkIngredient();

        if(isUniqueIngredient(currEdible)) {
            if (savedItem instanceof Drink) {
                newIngredient.init(currEdible, quantity, unit);
                newIngredient.setSubstitute(isSubstitute);
                ingredients.add(newIngredient);
            } else { //needs a regular ingredient
                ingredients.add(newIngredient.init(currEdible, quantity, unit)); //returns ingredient when inited
            }
        }
    }

    private boolean isUniqueIngredient(Edible newIngredient) { //change so dupes arent allowed
        boolean alreadyAnIngredient = true;
        Edible currEdible;

        for(Ingredient currIngredient: this.ingredients) {
            currEdible = currIngredient.getIngredient();

            if (currEdible.getDbkey() == newIngredient.getDbkey() && currEdible.getIsCustom() == newIngredient.getIsCustom()) {
                alreadyAnIngredient = false;
                break;
            }
        }

        return alreadyAnIngredient;
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
            this.ingredientEdibles.remove(position);
            this.ingredientEdibles.add(position, modifyLog);
        } else {
            restoreSaved();
        }
        this.recyclerViewAdapter.notifyItemChanged(position);
        this.recyclerViewAdapter.notifyDataSetChanged();
    }

    private void saveItem(int position) {
        this.savedItemPosition = position;
        this.savedItem = this.ingredientEdibles.get(position);
    }

    private void swapSaved(int position) {
        Edible temp = this.ingredientEdibles.get(position);
        restoreSaved();
        this.savedItemPosition = position;
        this.savedItem = temp;
    }

    private void restoreSaved() {
        if (savedItem != null) {
            this.ingredientEdibles.remove(this.savedItemPosition);
            this.ingredientEdibles.add(this.savedItemPosition, this.savedItem);
            this.savedItemPosition = -1;
            this.savedItem = null;
        }
    }
}