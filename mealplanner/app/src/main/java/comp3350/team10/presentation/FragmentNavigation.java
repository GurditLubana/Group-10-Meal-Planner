package comp3350.team10.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import comp3350.team10.R;

public class FragmentNavigation extends Fragment {
    ImageButton mealButton = null;    // button to launch the diary screen
    ImageButton dailyButton = null;   // button to launch the multi goal daily charts screen
    ImageButton recipeButton = null;  // button to launch the recipebook screen
    ImageButton trendsButton = null;  // button to launch the long term charts screen
    ImageButton socialButton = null;  // button to launch the social screen


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        mealButton = (ImageButton) view.findViewById(R.id.mealDiaryNav);
        dailyButton = (ImageButton) view.findViewById(R.id.dailyNav);
        recipeButton = (ImageButton) view.findViewById(R.id.recipeBookNav);
        trendsButton = (ImageButton) view.findViewById(R.id.chartsNav);
        socialButton = (ImageButton) view.findViewById(R.id.socialNav);


        setButtonClickListeners(view);
        setActiveButton(view);

        return view;
    }

    private void setButtonClickListeners(View view) {

        mealButton.setOnClickListener(new View.OnClickListener() {

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

        dailyButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (getActivity() != null && !(getActivity() instanceof ActivityDailyProgress)) {
                    Intent intent = new Intent(getActivity(), ActivityDailyProgress.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.putExtra("Source", "NAV");
                    startActivity(intent);
                }
            }
        });
        trendsButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (getActivity() != null && !(getActivity() instanceof ActivityTrends)) {
                    Intent intent = new Intent(getActivity(), ActivityTrends.class);
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
        } else if (context instanceof ActivityDailyProgress) {
            dailyButton.setColorFilter(Color.parseColor("#FFFFBB33"));
            dailyButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5885AF")));
        } else if (context instanceof ActivityTrends) {
            trendsButton.setColorFilter(Color.parseColor("#FFFFBB33"));
            trendsButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#5885AF")));
        }
    }
}
