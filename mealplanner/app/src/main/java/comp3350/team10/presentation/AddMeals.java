package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private EditText mealNameText,caloriesText,quantityText,instructions, ingredients;
    private Button addBtn3, cancelButton3;
    private String mealName;
    private EditText imageView;
    private int calories,quantity;
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
        imageView  = view.findViewById(R.id.mealsImagePath);
        caloriesText = view.findViewById(R.id.mealsCalories);
        quantityText = view.findViewById(R.id.mealsQnty);
        instructions = view.findViewById(R.id.mealsInstructions);
        ingredients = view.findViewById(R.id.mealsIngred);


        addBtn3 = view.findViewById(R.id.addItem3);
        cancelButton3 = view.findViewById(R.id.cancelTask3);

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

        return builder.create();
    }

    public static String TAG = "AddMeals";



    private void sendData(View view)
    {

        if(validateInput(view) == true) {

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


        if (calory.length() == 0 ) {
            Context context = view.getContext();
            CharSequence text = "Calory count can't be empty.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
            result = false;

        }


        if (name.length() == 0) {
            Context context = view.getContext();
            CharSequence text = "Meal Title can't be empty.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
            result = false;

        }

        if (recipe.length() == 0) {
            Context context = view.getContext();
            CharSequence text = "Recipe field can't be empty.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
            result = false;

        }

        if (ingred.length() == 0) {
            Context context = view.getContext();
            CharSequence text = "Field Ingredients can't be empty.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(getContext(), text, duration);
            toast.show();
            result = false;

        }


        if (quantity.length() == 0) {
            Context context = view.getContext();
            CharSequence text = "Quantities field can't be empty.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            result = false;

        }

        return result;
    }



}
