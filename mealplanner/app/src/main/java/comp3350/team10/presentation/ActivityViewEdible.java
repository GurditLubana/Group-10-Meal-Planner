package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.team10.R;

public class ActivityViewEdible extends AppCompatActivity {
    public static String RETURN_ACTIVITY_NAME = "returnActivityName";   //Key for additional data that is passed from caller
    private Class<?> returnTo;   //Where we want to return to after this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_edible);
        Bundle data = getIntent().getExtras();
        this.returnTo = findClass(data);
        addBackListener();
    }

    private Class<?> findClass(Bundle data) {
        Class<?> currClass = null;  //checks extra parameters for the return activity
                                    //if provided value was not correct exits fragment
        try {
            currClass = Class.forName((String)data.get(RETURN_ACTIVITY_NAME));
        }
        catch(Exception e) {
            System.out.println("Incorrect return class provided, exiting activity");
            finish();
        }

        return currClass;
    }

    private void addBackListener() {
        Button button = (Button) findViewById(R.id.backBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), returnTo);
                
                view.getContext().startActivity(myIntent);
            }
        });
    }
}