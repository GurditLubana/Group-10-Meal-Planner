package comp3350.team10.presentation;

import android.view.View;

import androidx.fragment.app.Fragment;

public interface FragToParent {
    public void navClick();
    public void showContextUI(int pos);
    public void hideContextUI(Fragment fragment);
    public void setData(View view);
}
