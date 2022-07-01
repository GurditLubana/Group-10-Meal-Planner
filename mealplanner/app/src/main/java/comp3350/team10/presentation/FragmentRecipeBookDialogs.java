package comp3350.team10.presentation;

import static android.app.Activity.RESULT_OK;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FragmentRecipeBookDialogs extends DialogFragment {


    private TextView title;                  // Dialog Title
    private TextView labelName;              // Label of name field
    private TextView labelCalories;          // Label of calories field
    private TextView labelIngredients;       // Label of ingredients field
    private EditText inputInstructions;      // input field for instructions
    private EditText inputName;              // input field for item name
    private EditText inputCalories;          // input field for item calories
    private EditText inputIngredients;       // input field for item ingredients
    private EditText inputQuantity;          // input field for item quantity
    private Spinner inputSpinner;            // input field for quantity units
    private Button btnOk;                    // OK button
    private Button btnCancel;                // Cancel Button
    private Button btnChooseItemImage;       // Import a picture for the Edible item.
    private CheckBox isAlcoholicCheckBox;    // check if Edible item contains alcohol.
    private CheckBox isSpicyCheckBox;        // check if Edible item contains alcohol.
    private CheckBox isVegetarianCheckBox;   // check if Edible item contains alcohol.
    private CheckBox isVeganCheckBox;
    private CheckBox isNonVegCheckBox;
    private CheckBox isGluteenFree;
    private ImageView EdibleItemImage;       // Image of the edible item.
    private ImageView cameraIcon;            // The camera Icon in Add Edible interface.
    private FragToRecipeBook send;           // Interface for communication with parent activity
    private FragToRecipeBook.EntryMode mode; // the type of dialog to show
    public static String TAG = "AddRecipe";  // tag name of this fragment for reference in the fragment manager
    private int calories;                    // value of calorie input
    private int quantity;                    // value of quantity input
    private String name;                     // value of name input
    private String instructions;             // value of instructions input
    private String ingredients;            // value of ingredients input
    private Edible.Unit unit;                // value of units input
    private static final int REQUEST_CODE = 1 ;    // Request code for the edible's image


    public FragmentRecipeBookDialogs() {
        // Required empty public constructor
    }

    public static FragmentRecipeBookDialogs newInstance(String param1, String param2) {
        FragmentRecipeBookDialogs fragment = new FragmentRecipeBookDialogs();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_recipe_book_dialogs, null);

        Context context = view.getContext();

        this.title = view.findViewById(R.id.dialogRecipeTitle);
        this.labelName = view.findViewById(R.id.dialogRecipeNameLabel);
        this.labelCalories = view.findViewById(R.id.dialogRecipeCaloriesLabel);
        this.labelIngredients = view.findViewById(R.id.dialogRecipeIngredientsLabel);
        this.inputInstructions = view.findViewById(R.id.dialogRecipeInstructions);
        this.inputName = view.findViewById(R.id.dialogRecipeNameInput);
        this.inputCalories = view.findViewById(R.id.dialogRecipeCaloriesInput);
        this.inputIngredients = view.findViewById(R.id.dialogRecipeIngredientsInput);
        this.inputQuantity = view.findViewById(R.id.dialogRecipeQuantityInput);
        this.inputSpinner = view.findViewById(R.id.dialogRecipeSpinner);
        this.btnOk = view.findViewById(R.id.dialogRecipeBtnOk);
        this.btnCancel = view.findViewById(R.id.dialogRecipeBtnCancel);
        this.btnChooseItemImage = view.findViewById(R.id.dialogRecipePhotoBtn);
        this.EdibleItemImage= view.findViewById(R.id.dialogRecipePhoto);
        this.cameraIcon = view.findViewById(R.id.dialogRecipePhotoIcon);
        this.isAlcoholicCheckBox= view.findViewById(R.id.isAlcoholic);
        this.isSpicyCheckBox= view.findViewById(R.id.isSpicy);
        this.isGluteenFree = view.findViewById(R.id.isGluteenFree);
        this.isVegetarianCheckBox = view.findViewById(R.id.isVegetarian);
        this.isVeganCheckBox = view.findViewById(R.id.isVegan);
        this.isGluteenFree = view.findViewById(R.id.isGluteenFree);


        if (context != null && context instanceof FragToRecipeBook) {
            this.send = (FragToRecipeBook) context;
            this.mode = this.send.getEntryMode();
            this.inputQuantity.setHint("0 - 9999");
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
        this.title.setText("Add New Food");
        this.labelName.setText("Food Name");
        this.inputName.setHint("Food Name");
        this.inputInstructions.setVisibility(View.GONE);
        this.inputIngredients.setVisibility(View.GONE);
        this.labelIngredients.setVisibility(View.GONE);
//        this.isAlcoholicCheckBox.setVisibility(View.GONE);
    }

    private void setMealDialogFieldDefaults() {

        this.title.setText("Add New Meal");
        this.labelName.setText("Meal Name");
        this.inputName.setHint("Meal Name");
        this.inputInstructions.setHint("Cooking Instructions");
        this.inputIngredients.setHint("Meal Ingredients\n(, comma separated)");
//        this.isAlcoholicCheckBox.setVisibility(View.GONE);
    }

    private void setDrinkDialogFieldDefaults() {

        this.title.setText("Add New Drink");
        this.labelName.setText("Drink Name");
        this.inputName.setHint("Drink Name");
        this.inputInstructions.setHint("Mixing Instructions");
        this.inputIngredients.setHint("Drink Ingredients\n(, comma separated)");
//        this.isVegetarianCheckBox.setVisibility(View.GONE);
//        this.isVeganCheckBox.setVisibility(View.GONE);
//        this.isNonVegCheckBox.setVisibility(View.GONE);
    }

    private void setSpinner() {

        int size = Edible.Unit.values().length;
        ArrayAdapter<String> adapter = null;
        String[] items = new String[size];

        for (int i = 0; i < size; i++) {
            items[i] = Edible.Unit.values()[i].name();
        }

        adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_unit_items, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.inputSpinner.setAdapter(adapter);
        this.inputSpinner.setSelection(adapter.getPosition(Edible.Unit.serving.name()));

    }

    private void setOnClickListeners() {

        this.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    sendData();
                    dismiss();
                }
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        this.btnChooseItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                if(Build.VERSION.SDK_INT >= 23)
                {
                    try {

                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
                        } else {
//
                            openGallery();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

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

private void openGallery()
{

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


  private void bitmap(ActivityResult result)
    {
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
        if (check(this.inputQuantity)) {
            this.quantity = Integer.parseInt(this.inputQuantity.getText().toString().trim());
            success += 1;
        }

        if (this.mode != FragToRecipeBook.EntryMode.ADD_FOOD) {
            if (check(this.inputIngredients)) {
                String[] tempIngredients = (this.inputIngredients.getText().toString().trim()).split(",");
                for(int i = 0; i <tempIngredients.length;i++)
                {
                    System.out.println(tempIngredients[i]);
                }

                this.ingredients = this.inputIngredients.getText().toString().trim();
                success += 1;
            }
            if (check(this.inputInstructions)) {
                this.instructions = this.inputInstructions.getText().toString().trim();
                success += 1;
            }
        }

        this.unit = Edible.Unit.valueOf(this.inputSpinner.getSelectedItem().toString());

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
        if (result && (view == this.inputQuantity)) {
            intValue = Integer.parseInt(value);
            if (intValue < 1 || intValue > Constant.ENTRY_MAX_VALUE) {
                view.setError("Must be between 1 and 9999 inclusive");
                result = false;
            }
        }

        return result;
    }

    private void sendData() {
//        if (this.send != null && this.send instanceof FragToRecipeBook) {
//            switch (mode) {
//                case ADD_FOOD:
//                    this.send.addFood(this.name, R.drawable.ic_eggplant, this.calories, this.unit, this.quantity);
//                    break;
//                case ADD_MEAL:
//                    this.send.addMeal(this.name, R.drawable.ic_eggplant, this.calories, this.ingredients, this.instructions, this.unit, this.quantity);
//                    break;
//                case ADD_DRINK:
//                    this.send.addDrink(this.name, R.drawable.ic_eggplant, this.calories, this.ingredients, this.instructions, this.unit, this.quantity);
//                    break;
//            }
//        }
    }


}