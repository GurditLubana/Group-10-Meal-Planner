package comp3350.team10.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import comp3350.team10.R;
import comp3350.team10.objects.*;
import comp3350.team10.business.*;
import comp3350.team10.persistence.*;

import java.util.LinkedList;
import java.util.ArrayList;

public class MealDiary extends AppCompatActivity implements FragToParent {

    private LinkedList<ListItem> data;
    private MealCustomAdapter mealCustomAdapter;
    private RecyclerView mealRecyclerView;
    private ListItem saved;
    private int savedPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_diary);
        getData();
    }

    private void getData() {
        DataAccessStub db = new DataAccessStub();
        db.open("someDB");
        ArrayList<DiaryItem> dbFetch = db.getToday();
        data = new LinkedList();
        data.addAll(dbFetch);
        mealCustomAdapter = new MealCustomAdapter(data);
        mealRecyclerView = (RecyclerView) findViewById(R.id.mealRecyclerView);
        mealRecyclerView.setAdapter(mealCustomAdapter);
        mealRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showContextUI(int pos) {
        System.out.println("clicked " + " " + pos);
        if (pos != savedPos && saved != null) {
            data.remove(savedPos);
            data.add(savedPos, saved);
        }
        if (data.get(pos).getFragmentType() == ListItem.FragmentType.diaryEntry) {
            saved = data.remove(pos);
            savedPos = pos;
            data.add(pos, new DiaryItem(0, ListItem.FragmentType.diaryModify, "null", 00, ListItem.Unit.g, 0, "noIcon"));
        } else {
            data.remove(pos);
            data.add(pos, saved);
            saved = null;
        }
        //mealRecyclerView.removeViewAt(pos);
        mealCustomAdapter.notifyItemRemoved(pos);
        mealCustomAdapter.notifyItemRangeChanged(pos, data.size());
        mealCustomAdapter.notifyDataSetChanged();
    }

    @Override
    public void navClick() {
    }

    ;

    @Override
    public void hideContextUI(Fragment fragment) {
    }

    ;

    @Override
    public void setData(View view) {
    }

    ;
}