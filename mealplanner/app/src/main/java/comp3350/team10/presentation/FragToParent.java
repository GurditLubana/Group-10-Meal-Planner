package comp3350.team10.presentation;

import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.LinkedList;

import comp3350.team10.objects.Edible;
import comp3350.team10.objects.ListItem;

public interface FragToParent {
    public void navClick();
    public void showContextUI(int pos);
    public void hideContextUI(Fragment fragment);
    public void setData(View view);
    public void selectDate();
    public void prevDate();
    public void nextDate();
    public void setGoal();
    public LinkedList<ListItem> getList();
}
