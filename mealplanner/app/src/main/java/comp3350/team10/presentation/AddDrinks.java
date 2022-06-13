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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import comp3350.team10.R;
import comp3350.team10.objects.DrinkIngredient;
import comp3350.team10.objects.ListItem;


public class AddDrinks extends DialogFragment {

    private EditText drinkNameText,caloriesText,quantityText,instructions, ingredients;
    private Button addBtn2, cancelButton2;
    private String drinkName;
    private EditText imageView;
    private int calories,quantity;
    private DrinkIngredient[] ingredientsArray;
    private ImageView image;
    private FragToRecipeBook sendInput;

    public AddDrinks() {
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

    public static String TAG = "AddDrinks";



    private void sendData(View view)
    {

        String [] dInstruct = instructions.getText().toString().split("\n");
        String[] dIngredients = ingredients.getText().toString().split(",");
        drinkName = drinkNameText.getText().toString().trim();
        calories = Integer.parseInt(caloriesText.getText().toString().trim());
        quantity = Integer.parseInt(quantityText.getText().toString().trim());
        Intent intent = new Intent(getContext(), ActivityRecipeBook.class );
        Context context = view.getContext();

        if (context != null) {
            sendInput = (FragToRecipeBook) context;
            sendInput.addDrink(drinkName, R.drawable.ic_eggplant,calories,ingredientsArray, dInstruct,ListItem.Unit.ml,quantity);
        }

        dismiss();

    }


}