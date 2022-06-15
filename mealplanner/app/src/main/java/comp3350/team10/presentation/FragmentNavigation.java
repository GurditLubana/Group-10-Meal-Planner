package comp3350.team10.presentation;

import static android.graphics.Color.parseColor;

import comp3350.team10.R;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentNavigation extends Fragment {
    ImageButton mealButton = null;
    ImageButton dailyButton = null;
    ImageButton recipeButton = null;
    ImageButton chartsButton = null;
    ImageButton socialButton = null;

    public FragmentNavigation() {
    } //Required empty public constructor


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
        mealButton = (ImageButton) view.findViewById(R.id.mealDiaryNav);
        dailyButton = (ImageButton) view.findViewById(R.id.dailyNav);
        recipeButton = (ImageButton) view.findViewById(R.id.recipeBookNav);
        chartsButton = (ImageButton) view.findViewById(R.id.chartsNav);
        socialButton = (ImageButton) view.findViewById(R.id.socialNav);


        setButtonClickListeners(view);
        setActiveButton(view);

        return view;
    }

    private void setButtonClickListeners(View view) {

        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null && !(getActivity() instanceof ActivityMealDiary)) {
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
                if (getActivity() != null && !(getActivity() instanceof ActivityRecipeBook)) {
                    Intent intent = new Intent(getActivity(), ActivityRecipeBook.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Source", "NAV");
                    startActivity(intent);
                }
            }
        });
    }

    private void setActiveButton(View view) {
        Context context = view.getContext();

        if (context instanceof ActivityMealDiary) {
            mealButton.setColorFilter(Color.parseColor("#FFFFBB33"));
            mealButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5885AF")));
        } else if (context instanceof ActivityRecipeBook) {
            recipeButton.setColorFilter(Color.parseColor("#FFFFBB33"));
            recipeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5885AF")));
        }
    }
}