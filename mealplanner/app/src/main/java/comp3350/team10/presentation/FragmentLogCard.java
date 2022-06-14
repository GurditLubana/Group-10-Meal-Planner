package comp3350.team10.presentation;

import comp3350.team10.R;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentLogCard extends Fragment {
    public FragmentLogCard() {} //Required empty public constructor

    public static FragmentLogCard newInstance() {
        FragmentLogCard fragment = new FragmentLogCard();
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
        return inflater.inflate(R.layout.fragment_log_card, container, false);
    }

    @Override
    public void onAttach(Context context) { //This makes sure that the container activity has implemented
        super.onAttach(context);            //the callback interface. If not, it throws an exception

    }


}