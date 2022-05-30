package comp3350.team10.presentation;

import android.content.Context;
import android.os.Bundle;
import comp3350.team10.R;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDailyProgress#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDailyProgress extends Fragment {
    FragToParent send;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDailyProgress() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDailyProgress newInstance(String param1, String param2) {
        FragmentDailyProgress fragment = new FragmentDailyProgress();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_daily_progress, container, false);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_progress, container, false);
        setClickListeners(view);
        setObservers(view);

        return view;
    }

    private void setClickListeners(View view){
        TextView dateProgress = (TextView) view.findViewById(R.id.dateProgress);
        ImageButton prevDateProgress = (ImageButton) view.findViewById(R.id.prevDateProgress);
        ImageButton nextDateProgress = (ImageButton) view.findViewById(R.id.nextDateProgress);
        TextView goalGoal = (TextView) view.findViewById(R.id.goalGoal);

        dateProgress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToParent) context;
                    send.selectDate();
                }
            }
        });
        prevDateProgress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToParent) context;
                    send.prevDate();
                }
            }
        });
        nextDateProgress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToParent) context;
                    send.nextDate();
                }
            }
        });
        goalGoal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = view.getContext();

                if (context != null) {
                    send = (FragToParent) context;
                    send.setGoal();
                }
            }
        });
    }

    private void setObservers(View view){

    }

    public void update(){

    }
}