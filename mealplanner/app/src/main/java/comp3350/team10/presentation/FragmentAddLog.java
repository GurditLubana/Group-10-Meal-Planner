package comp3350.team10.presentation;

import comp3350.team10.R;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentAddLog extends Fragment {
    public FragmentAddLog() {} //Required empty public constructor

    public static FragmentAddLog newInstance() {
        FragmentAddLog fragment = new FragmentAddLog();
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
        return inflater.inflate(R.layout.fragment_add_log, container, false);
    }
}