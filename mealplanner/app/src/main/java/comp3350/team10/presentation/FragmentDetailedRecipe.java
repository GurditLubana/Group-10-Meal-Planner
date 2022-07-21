package comp3350.team10.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import comp3350.team10.R;

public class FragmentDetailedRecipe extends Fragment {
    public FragmentDetailedRecipe() {
    } //Required empty public constructor


    public static FragmentDetailedRecipe newInstance() {
        FragmentDetailedRecipe fragment = new FragmentDetailedRecipe();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_recipe, container, false);
    }
}