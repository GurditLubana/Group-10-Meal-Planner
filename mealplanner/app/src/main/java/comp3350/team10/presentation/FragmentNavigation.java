package comp3350.team10.presentation;

import android.content.Intent;
import android.os.Bundle;
import comp3350.team10.R;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNavigation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNavigation extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentNavigation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FooterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNavigation newInstance(String param1, String param2) {
        FragmentNavigation fragment = new FragmentNavigation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageButton mealButton;
        ImageButton dailyButton;
        ImageButton recipeButton;
        ImageButton chartsButton;
        ImageButton socialButton;

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        mealButton = (ImageButton) view.findViewById(R.id.mealDiaryNav);
        dailyButton = (ImageButton) view.findViewById(R.id.dailyNav);
        recipeButton = (ImageButton) view.findViewById(R.id.recipeBookNav);
        chartsButton = (ImageButton) view.findViewById(R.id.chartsNav);
        socialButton = (ImageButton) view.findViewById(R.id.socialNav);

        mealButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // prevent creating new activity when button pressed while in MealDiary
                if (getActivity() != null && !(getActivity() instanceof ActivityMealDiary)) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ActivityMealDiary.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        recipeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getActivity() != null && !(getActivity() instanceof ActivityRecipeBook)) {
                    Intent intent = new Intent(getActivity(), ActivityRecipeBook.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}