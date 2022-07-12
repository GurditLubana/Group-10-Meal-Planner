package comp3350.team10.presentation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import comp3350.team10.R;

public class FragmentDiaryProgress extends Fragment {
    private MealDiaryLiveData mealDiaryLiveData; // live data source for automatic field updates
    private SimpleDateFormat mon;                // date format for month
    private SimpleDateFormat day;                // date format for day
    private FragToMealDiary send;                // Interface to pass data to the parent activity

    public FragmentDiaryProgress() {
    }  //Required empty public constructor


    public static FragmentDiaryProgress newInstance() {
        FragmentDiaryProgress fragment = new FragmentDiaryProgress();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mon = new SimpleDateFormat("MMM");
        this.day = new SimpleDateFormat("dd");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary_progress, container, false);
        setClickListeners(view);

        mealDiaryLiveData = new ViewModelProvider(requireActivity()).get(MealDiaryLiveData.class);
        setObservers(view);

        return view;
    }

    private void setClickListeners(View view) {
        ImageButton prevDateProgress = (ImageButton) view.findViewById(R.id.prevDateProgress);
        ImageButton nextDateProgress = (ImageButton) view.findViewById(R.id.nextDateProgress);
        TextView exerciseProgress = (TextView) view.findViewById(R.id.exerciseProgress);
        TextView dateProgress = (TextView) view.findViewById(R.id.dateProgress);
        TextView goalCalorie = (TextView) view.findViewById(R.id.goalCalorie);

        dateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToMealDiary) context;
                    send.selectDate();
                }
            }
        });

        prevDateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToMealDiary) context;
                    send.prevDate();
                }
            }
        });

        nextDateProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToMealDiary) context;
                    send.nextDate();
                }
            }
        });

        goalCalorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToMealDiary) context;
                    send.showGoalEntryDialog();
                }
            }
        });

        exerciseProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToMealDiary) context;
                    send.showExerciseEntryDialog();
                }
            }
        });
    }

    private void setObservers(View view) {
        mealDiaryLiveData.getActivityDate().observe(getViewLifecycleOwner(), new Observer<Calendar>() {
            @Override
            public void onChanged(Calendar calendar) {
                ((TextView) view.findViewById(R.id.dateProgress)).setText(mon.format(calendar.getTime()) + " " + day.format(calendar.getTime()));
            }
        });

        mealDiaryLiveData.getGoalCalories().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double goalCalories) {
                ((TextView) view.findViewById(R.id.goalCalorie)).setText(String.valueOf(goalCalories.intValue()));
            }
        });

        mealDiaryLiveData.getConsumedCalories().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double foodConsumed) {
                ((TextView) view.findViewById(R.id.foodConsumed)).setText(String.valueOf(foodConsumed.intValue()));
            }
        });

        mealDiaryLiveData.getExerciselCalories().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double exerciseCalories) {
                ((TextView) view.findViewById(R.id.exerciseProgress)).setText(String.valueOf(exerciseCalories.intValue()));
            }
        });

        mealDiaryLiveData.getNetCalories().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double netCalories) {
                ((TextView) view.findViewById(R.id.netCalories)).setText(String.valueOf(Math.abs(netCalories.intValue())));

                if (netCalories < 0) {
                    ((TextView) view.findViewById(R.id.netCalories)).setTextColor(Color.RED);
                } else {
                    ((TextView) view.findViewById(R.id.netCalories)).setTextColor(Color.parseColor("#81C784"));
                }
            }
        });

        mealDiaryLiveData.getProgressBar().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double progressBar) {
                ((ProgressBar) view.findViewById(R.id.progressBar)).setProgress(progressBar.intValue());
            }
        });

        mealDiaryLiveData.getProgressExcess().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double progressExcess) {
                ((ProgressBar) view.findViewById(R.id.progressExcess)).setProgress(progressExcess.intValue());
            }
        });
    }
}