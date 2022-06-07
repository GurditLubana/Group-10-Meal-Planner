package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import comp3350.team10.R;

public class AddDrinks extends AppCompatActivity {

    private EditText drinkNameText,image,caloriesText,quantityText;
    private Button addBtn;
    private String drinkName;
    private ImageView imageView;
    private int calories;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drinks);

        drinkNameText = findViewById(R.id.foodTitle);
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
//
//        drinkName = drinkNameText.getText().toString().trim();
//        calories = Integer.parseInt(caloriesText.getText().toString().trim());
//        quantity = Integer.parseInt(quantityText.getText().toString().trim());
//        Intent i = new Intent(AddDrinks.this, ActivityRecipeBook.class );
//        i.putExtra(ActivityRecipeBook.Edible, drinkName);
//        i.putExtra(ActivityRecipeBook.Calories,calories);
//        i.putExtra(ActivityRecipeBook.Quantity,quantity);
//

//        startActivity(i);

    }


}