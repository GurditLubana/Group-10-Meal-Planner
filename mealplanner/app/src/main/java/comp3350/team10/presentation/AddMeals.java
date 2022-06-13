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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMeals() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMealDiaryEdit.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMeals newInstance(String param1, String param2) {
        AddMeals fragment = new AddMeals();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        String [] dInstruct = instructions.getText().toString().split("\n");
        String[] dIngredients = ingredients.getText().toString().split(",");
        mealName = mealNameText.getText().toString().trim();
        calories = Integer.parseInt(caloriesText.getText().toString().trim());
        quantity = Integer.parseInt(quantityText.getText().toString().trim());
        Intent intent = new Intent(getContext(), ActivityRecipeBook.class );
        Context context = view.getContext();

        if (context != null) {
            sendInput2 = (FragToRecipeBook) context;
            sendInput2.addMeal(mealName, R.drawable.ic_eggplant,calories,ingredientsArray, dInstruct,ListItem.Unit.ml,quantity);
        }

        dismiss();

    }


}