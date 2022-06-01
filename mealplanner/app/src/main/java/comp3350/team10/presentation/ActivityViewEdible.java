package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.team10.R;

public class ActivityViewEdible extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edible);
        addBackListener();
    }

    //to do:
    //save previous tab
    //go back to previous tab when back button is clicked

    private void addBackListener() {
        Button button = (Button) findViewById(R.id.backBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("never going back...");
                // Do something in response to button click
            }
        });
    }
}