package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.*;

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

public class AddDrinks extends DialogFragment {

    private EditText drinkNameText,caloriesText,quantityText,instructions, ingredients;
    private Button addBtn2, cancelButton2;
    private String drinkName;
    private EditText imageView;
    private int calories,quantity;
    private DrinkIngredient[] ingredientsArray;
    private ImageView image;
    private FragToRecipeBook sendInput;
    public static String TAG = "ADD_DRINK";
    public AddDrinks() {} //Required empty public constructor


    public static AddDrinks newInstance() {
        AddDrinks fragment = new AddDrinks();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_add_drinks, null);
        builder.setView(view);

        drinkNameText = view.findViewById(R.id.drinksTitle);
        imageView  = view.findViewById(R.id.drinksImagePath);
        caloriesText = view.findViewById(R.id.drinksCalories);
        quantityText = view.findViewById(R.id.drinksQnty);
        instructions = view.findViewById(R.id.drinkInstructions);
        ingredients = view.findViewById(R.id.drinkIngred);


        addBtn2 = view.findViewById(R.id.addItem2);
        cancelButton2 = view.findViewById(R.id.cancelTask2);

        addBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData(view);
            }
        });

        cancelButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        return builder.create();
    }

    private void sendData(View view)
    {

        if(validateInput(view) == true) {
            String[] dInstruct = instructions.getText().toString().split("\n");
            String[] dIngredients = ingredients.getText().toString().split(",");
            drinkName = drinkNameText.getText().toString().trim();
            calories = Integer.parseInt(caloriesText.getText().toString().trim());
            quantity = Integer.parseInt(quantityText.getText().toString().trim());
            Intent intent = new Intent(getContext(), ActivityRecipeBook.class);
            Context context = view.getContext();

            if (context != null) {
                sendInput = (FragToRecipeBook) context;
                sendInput.addDrink(drinkName, R.drawable.ic_eggplant, calories, ingredientsArray, dInstruct, Edible.Unit.ml, quantity);
            }

            String text = drinkName + " Successfully added to the list";
            Toast toast = Toast.makeText(getContext(), text , Toast.LENGTH_SHORT);
            toast.show();
            dismiss();
        }

    }

    private boolean validateInput(View view) {
        boolean result = true;
        String name = drinkNameText.getText().toString().trim();
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
            CharSequence text = "Drink name can't be empty.";
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
