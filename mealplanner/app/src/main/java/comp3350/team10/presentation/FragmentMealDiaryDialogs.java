package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMealDiaryDialogs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMealDiaryDialogs extends DialogFragment {
    public static String TAG = "MealEntryDialog";
    private FragToMealDiary send;
    private FragToMealDiary.EntryMode mode;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMealDiaryDialogs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMealDiaryDialogs.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMealDiaryDialogs newInstance(String param1, String param2) {
        FragmentMealDiaryDialogs fragment = new FragmentMealDiaryDialogs();
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_meal_diary_edit, null);
        Context context = view.getContext();
        builder.setView(view);

        if(context != null && context instanceof FragToMealDiary){
            this.send = (FragToMealDiary) context;
            this.mode = this.send.getEntryMode();

            switch(mode){
                case EDIT_QTY:

                    break;
                case GOAL_CALORIE:

                    break;
            }
        }

        setFieldDefaults(view);
        setOnClickListeners(view);

        return builder.create();
    }


}