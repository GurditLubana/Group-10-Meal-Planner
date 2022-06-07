package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;

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




public class AddRecipe extends AppCompatActivity {

    private EditText foodNameText,caloriesText,quantityText;
    private Button addBtn;
    private String foodName;
    private EditText imageView;
    private int calories;
    private int quantity;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        foodNameText = findViewById(R.id.foodTitle);
        imageView  = findViewById(R.id.imagePath);
        caloriesText = findViewById(R.id.foodCalories);
        quantityText = findViewById(R.id.foodQnty);
        addBtn = findViewById(R.id.addItem);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData();

            }
        });


    }

    private void sendData()
    {

        foodName = foodNameText.getText().toString().trim();
        calories = Integer.parseInt(caloriesText.getText().toString().trim());
        quantity = Integer.parseInt(quantityText.getText().toString().trim());
        Intent intent = new Intent(AddRecipe.this, ActivityRecipeBook.class );
        intent.putExtra(ActivityRecipeBook.Edible, foodName);
        intent.putExtra(ActivityRecipeBook.Calories,calories);
        intent.putExtra(ActivityRecipeBook.Quantity,quantity);

        setResult(RESULT_OK, intent);
        finish();

       startActivity(intent);

    }


}