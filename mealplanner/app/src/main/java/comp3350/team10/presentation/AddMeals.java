package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import comp3350.team10.R;
import comp3350.team10.objects.*;
import comp3350.team10.objects.MealIngredient;


public class AddMeals extends DialogFragment {

    private EditText mealNameText, caloriesText, quantityText, instructions, ingredients;
    private Button addBtn3, cancelButton3;
    private String mealName;
    private EditText imageView;
    private int calories, quantity;
    private MealIngredient[] ingredientsArray;
    private ImageView image;
    private FragToRecipeBook sendInput2;


    public AddMeals() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_add_meals, null);
        builder.setView(view);

        mealNameText = view.findViewById(R.id.mealsTitle);
        //imageView  = view.findViewById(R.id.mealsImagePath);
        caloriesText = view.findViewById(R.id.mealsCalories);
        quantityText = view.findViewById(R.id.mealsQnty);
        instructions = view.findViewById(R.id.mealsInstructions);
        ingredients = view.findViewById(R.id.mealsIngred);


        addBtn3 = view.findViewById(R.id.btnOk);
        cancelButton3 = view.findViewById(R.id.btnCancel);

        addBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData(view);

            }
        });

        cancelButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return dialog;
    }

    public static String TAG = "AddMeals";


    private void sendData(View view) {

        if (validateInput(view) == true) {

            String[] dInstruct = instructions.getText().toString().split("\n");
            String[] dIngredients = ingredients.getText().toString().split(",");
            mealName = mealNameText.getText().toString().trim();
            calories = Integer.parseInt(caloriesText.getText().toString().trim());
            quantity = Integer.parseInt(quantityText.getText().toString().trim());
            Intent intent = new Intent(getContext(), ActivityRecipeBook.class);
            Context context = view.getContext();

            if (context != null) {
                sendInput2 = (FragToRecipeBook) context;
                sendInput2.addMeal(mealName, R.drawable.ic_eggplant, calories, ingredientsArray, dInstruct, Edible.Unit.ml, quantity);
            }
            String text = mealName + " Successfully added to the list";
            Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
            toast.show();
            dismiss();
        }

    }


    private boolean validateInput(View view) {
        boolean result = true;
        String name = mealNameText.getText().toString().trim();
        String calory = caloriesText.getText().toString().trim();
        String quantity = quantityText.getText().toString().trim();
        String recipe = instructions.getText().toString().trim();
        String ingred = ingredients.getText().toString().trim();


        if (calory.length() == 0) {
            caloriesText.setError("Calorie count cannot be empty");
            result = false;

        }

        if (name.length() == 0) {
            mealNameText.setError("Meal name cannot be empty");
            result = false;

        }

        if (recipe.length() == 0) {
            instructions.setError("Cooking instructions cannot be empty");
            result = false;

        }

        if (ingred.length() == 0) {
            ingredients.setError("Ingredients cannot be empty");
            result = false;

        }


        if (quantity.length() == 0) {
            quantityText.setError("Quantity must be between 0 and 9999 inclusive");
            result = false;

        }

        return result;
    }


}
