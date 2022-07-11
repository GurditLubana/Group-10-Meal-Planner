package comp3350.team10.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import comp3350.team10.R;

public class FragmentRecipeBookCard extends Fragment {
    public FragmentRecipeBookCard() {
    } // Required empty public constructor


    public static FragmentRecipeBookCard newInstance() {
        FragmentRecipeBookCard fragment = new FragmentRecipeBookCard();
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
        return inflater.inflate(R.layout.fragment_recipe_book_card, container, false);
    }
}