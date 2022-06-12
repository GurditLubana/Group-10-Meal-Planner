package comp3350.team10.presentation;

import comp3350.team10.R;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentNavigation extends Fragment {
    public FragmentNavigation() {} //Required empty public constructor


    public static FragmentNavigation newInstance() {
        FragmentNavigation fragment = new FragmentNavigation();
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
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        ImageButton mealButton = (ImageButton) view.findViewById(R.id.mealDiaryNav);
        ImageButton dailyButton = (ImageButton) view.findViewById(R.id.dailyNav);
        ImageButton recipeButton = (ImageButton) view.findViewById(R.id.recipeBookNav);
        ImageButton chartsButton = (ImageButton) view.findViewById(R.id.chartsNav);
        ImageButton socialButton = (ImageButton) view.findViewById(R.id.socialNav);

        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // prevent creating new activity when button pressed while in MealDiary
                if(getActivity() != null && !(getActivity() instanceof ActivityMealDiary)) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), ActivityMealDiary.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity() != null && !(getActivity() instanceof ActivityRecipeBook)) {
                    Intent intent = new Intent(getActivity(), ActivityRecipeBook.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
        
        return view;
    }
}