package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.ListItem;

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

public class AddDrinks extends DialogFragment {

    private EditText drinkNameText,caloriesText,quantityText;
    private Button addBtn2, cancelButton2;
    private String drinkName;
    private EditText imageView;
    private int calories;
    private int quantity;
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
                Intent intent = new Intent(getContext(), ActivityRecipeBook.class );
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return builder.create();
    }

    private void sendData(View view) {
        drinkNameText = view.findViewById(R.id.foodTitle);
        imageView  = view.findViewById(R.id.imagePath);
        caloriesText = view.findViewById(R.id.foodCalories);
        quantityText = view.findViewById(R.id.foodQnty);

        //Recieve input from fields
        drinkName = drinkNameText.getText().toString().trim();
        calories = Integer.parseInt(caloriesText.getText().toString().trim());
        quantity = Integer.parseInt(quantityText.getText().toString().trim());

        Intent intent = new Intent(getContext(), ActivityRecipeBook.class );
        Context context = view.getContext();

        if(context != null) {
            sendInput = (FragToRecipeBook) context;
            sendInput.addFood(drinkName, R.drawable.ic_eggplant,calories,ListItem.Unit.ml,quantity);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}