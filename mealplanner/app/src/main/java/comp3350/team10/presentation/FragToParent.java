package comp3350.team10.presentation;

import androidx.fragment.app.Fragment;

public interface FragToParent {
    public void navClick();
    public void showContextUI(Fragment fragment);
    public void hideContextUI(Fragment fragment);
}
