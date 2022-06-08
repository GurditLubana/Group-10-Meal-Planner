package comp3350.team10.presentation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedList;

import comp3350.team10.R;
import comp3350.team10.business.RecipeBookOps;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Food;
import comp3350.team10.objects.ListItem;
import comp3350.team10.persistence.DataAccessStub;




public class AddRecipe extends DialogFragment {

    private EditText foodNameText,caloriesText,quantityText;
    private Button addBtn;
    private String foodName;
    private EditText imageView;
    private int calories;
    private int quantity;
    private ImageView image;
    private FragToRecipeBook send;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddRecipe() {
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
    public static AddRecipe newInstance(String param1, String param2) {
        AddRecipe fragment = new AddRecipe();
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

        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_add_recipe, null);
        builder.setView(view);
        //setContentView(R.layout.activity_add_recipe);

        foodNameText = view.findViewById(R.id.foodTitle);
        imageView  = view.findViewById(R.id.imagePath);
        caloriesText = view.findViewById(R.id.foodCalories);
        quantityText = view.findViewById(R.id.foodQnty);
        addBtn = view.findViewById(R.id.addItem);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();

            }
        });
        return builder.create();
    }

    public static String TAG = "AddRecipe";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_recipe);
//
//        foodNameText = findViewById(R.id.foodTitle);
//        imageView  = findViewById(R.id.imagePath);
//        caloriesText = findViewById(R.id.foodCalories);
//        quantityText = findViewById(R.id.foodQnty);
//        addBtn = findViewById(R.id.addItem);
//
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                sendData();
//
//            }
//        });
//
//
//    }

    private void sendData()
    {

        foodName = foodNameText.getText().toString().trim();
        calories = Integer.parseInt(caloriesText.getText().toString().trim());
        quantity = Integer.parseInt(quantityText.getText().toString().trim());
        Intent intent = new Intent(getContext(), ActivityRecipeBook.class );
        intent.putExtra(ActivityRecipeBook.Edible, foodName);
        intent.putExtra(ActivityRecipeBook.Calories,calories);
        intent.putExtra(ActivityRecipeBook.Quantity,quantity);

//        setResult(RESULT_OK, intent);
//        finish();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


}