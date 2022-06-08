package comp3350.team10.presentation;

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
import android.widget.Button;
import android.widget.EditText;

import comp3350.team10.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGoalEntry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGoalEntry extends DialogFragment {
    FragToMealDiary send;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentGoalEntry() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGoalEntry.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGoalEntry newInstance(String param1, String param2) {
        FragmentGoalEntry fragment = new FragmentGoalEntry();
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

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_goal_entry, null);
        builder.setView(view);

        setFieldDefaults(view);
        setOnClickListeners(view);

        return builder.create();
    }

    public static String TAG = "EditCalorieGoalDialog";

    private void setFieldDefaults(View view){
        Context context = view.getContext();
        EditText entryBox = null;
        String quantity = "null";

        if (context != null) {
            send = (FragToMealDiary) context;
            quantity = send.getGoalCalories();
            entryBox = (EditText) view.findViewById(R.id.inputQty);
            entryBox.setText(quantity);

        }
    }

    private void setOnClickListeners(View view){
        Button btnOk = (Button) view.findViewById(R.id.btnOK);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText entryBox = (EditText) view.findViewById(R.id.inputQty);
                Integer value = Integer.parseInt(entryBox.getText().toString());
                Context context = view.getContext();
                if(value < 0 || value > 9999){
                    entryBox.setError("Invalid input must be between 0 and 9999 inclusive");
                }
                else {

                    if (context != null) {
                        send = (FragToMealDiary) context;
                        send.setGoalCalories(value);
                        dismiss();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
    }
}