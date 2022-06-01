package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comp3350.team10.R;
import comp3350.team10.objects.DiaryItem;

public class ActivityViewEdible extends AppCompatActivity {
    public static String RETURN_ACTIVITY_NAME = "returnActivityName";
    private Class<?> returnTo;   //Where we want to return to after this
    private DiaryItem item; //this needs to be fixed

    @Override
    protected void onCreate(Bundle savedInstanceState) { //expects returnActivityName and item
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edible);
        Bundle data = getIntent().getExtras();
        returnTo = findClass(data);
        //item = (DiaryItem)data.getParcelable("returnActivity");

        //if(False) {

        //}
        //else if(False) {
        //    System.out.println("else if...");
        //}
        //else {
          //  System.out.println("slse...");
        //}

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