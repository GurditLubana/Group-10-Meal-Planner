package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.ListItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class AddRecipe extends DialogFragment {
    private EditText foodNameText, caloriesText, quantityText;
    private Button addBtn, cancelButton;
    private String foodName;
    private EditText imageView;
    private int calories;
    private int quantity;
    private ImageView image;
    private FragToRecipeBook send;
    public static String TAG = "AddRecipe";

    public AddRecipe() {}   //Required empty public constructor


    public static AddRecipe newInstance() {
        AddRecipe fragment = new AddRecipe();
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_add_food, null);
        builder.setView(view);

        foodNameText = view.findViewById(R.id.foodTitle);
        imageView  = view.findViewById(R.id.imagePath);
        caloriesText = view.findViewById(R.id.foodCalories);
        quantityText = view.findViewById(R.id.foodQnty);
        addBtn = view.findViewById(R.id.addItem);
        cancelButton = view.findViewById(R.id.cancelTask);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData(view);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        return builder.create();
    }

    private void sendData(View view)
    {

        if(validateInput(view) == true)
        {
        foodName = foodNameText.getText().toString().trim();
        calories = Integer.parseInt(caloriesText.getText().toString().trim());
        quantity = Integer.parseInt(quantityText.getText().toString().trim());
        Intent intent = new Intent(getContext(), ActivityRecipeBook.class );
        Context context = view.getContext();


            if (context != null) {
                send = (FragToRecipeBook) context;
                send.addFood(foodName, R.drawable.ic_eggplant,calories,Edible.Unit.g,quantity);
            }

            String text = foodName + " Successfully added to the list";
            Toast toast = Toast.makeText(getContext(), text , Toast.LENGTH_SHORT);
            toast.show();
            dismiss();
        }

    }

    private boolean validateInput(View view) {
        boolean result = true;
        String name = foodNameText.getText().toString().trim();
        String calory = caloriesText.getText().toString().trim();
        String quantity = quantityText.getText().toString().trim();


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
            CharSequence text = "Food name can't be empty.";
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
