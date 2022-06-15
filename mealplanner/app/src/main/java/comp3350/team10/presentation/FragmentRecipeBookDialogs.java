package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentRecipeBookDialogs extends DialogFragment {
    private TextView title;
    private TextView labelName;
    private TextView labelCalories;
    private TextView labelIngredients;
    private EditText inputInstructions;
    private EditText inputName;
    private EditText inputCalories;
    private EditText inputIngredients;
    private EditText inputQuantity;
    private Spinner inputSpinner;
    private Button btnOk;
    private Button btnCancel;
    private FragToRecipeBook send;
    public static String TAG = "AddRecipe";
    private int calories;
    private int quantity;
    private String name;
    private String instructions;
    private String ingredients;
    private Edible.Unit unit;
    private FragToRecipeBook.EntryMode mode;

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
    }

    private void setMealDialogFieldDefaults() {

        this.title.setText("Add New Meal");
        this.labelName.setText("Meal Name");
        this.inputName.setHint("Meal Name");
        this.inputInstructions.setHint("Cooking Instructions");
        this.inputIngredients.setHint("Meal Ingredients\n(, comma separated)");
    }

    private void setDrinkDialogFieldDefaults() {

        this.title.setText("Add New Drink");
        this.labelName.setText("Drink Name");
        this.inputName.setHint("Drink Name");
        this.inputInstructions.setHint("Mixing Instructions");
        this.inputIngredients.setHint("Drink Ingredients\n(, comma separated)");
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

        if (mode != FragToRecipeBook.EntryMode.ADD_FOOD) {
            if (check(this.inputIngredients)) {
                this.ingredients = this.inputIngredients.getText().toString().trim();
                success += 1;
            }
            if (check(this.inputInstructions)) {
                this.instructions = this.inputInstructions.getText().toString().trim();
                success += 1;
            }
        }

        this.unit = Edible.Unit.valueOf(this.inputSpinner.getSelectedItem().toString());

        return (success == 3 || (mode != FragToRecipeBook.EntryMode.ADD_FOOD && success == 5));
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
        if (result && (view == this.inputCalories || view == this.inputQuantity)) {
            intValue = Integer.parseInt(value);
            if (intValue < Constant.ENTRY_MIN_VALUE || intValue > Constant.ENTRY_MAX_VALUE) {
                this.inputCalories.setError("Must be between 0 and 9999 inclusive");
                result = false;
            }
        }

        return result;
    }

    private void sendData() {
        if (this.send != null && this.send instanceof FragToRecipeBook) {
            switch (mode) {
                case ADD_FOOD:
                    this.send.addFood(this.name, R.drawable.ic_eggplant, this.calories, this.unit, this.quantity);
                    break;
                case ADD_MEAL:
                    this.send.addMeal(this.name, R.drawable.ic_eggplant, this.calories, this.ingredients, this.instructions, this.unit, this.quantity);
                    break;
                case ADD_DRINK:
                    this.send.addDrink(this.name, R.drawable.ic_eggplant, this.calories, this.ingredients, this.instructions, this.unit, this.quantity);
                    break;
            }
        }

    }
}