package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.team10.R;
import comp3350.team10.objects.ListItem;

import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;

public class MealDiary extends AppCompatActivity implements FragToParent {

    private LinkedList<ListItem> data;
    private MealCustomAdapter mealCustomAdapter;
    private RecyclerView mealRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        getData();
    }

    private void getData() {
        //String[] data = {"one"};
        data = new LinkedList();
        mealCustomAdapter = new MealCustomAdapter(data);
        mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
        mealRecyclerView.setAdapter(mealCustomAdapter);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showContextUI(int pos) {

        View view = mealRecyclerView.getLayoutManager().findViewByPosition(pos);
        Fragment childFragment = new ModifyLogFragment();
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contextUI, childFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
        System.out.println("clicked " + " " + pos);
    }

    @Override
    public void navClick(){};

    @Override
    public void hideContextUI(Fragment fragment){};

    @Override
    public void setData(View view){};
}