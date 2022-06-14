package comp3350.team10.presentation;

import comp3350.team10.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FragmentExerciseEntry extends DialogFragment {
    public static String TAG = "EditExerciseGoalDialog";
    private final static int ENTRY_LIMIT = 9999;
    private FragToMealDiary send;

    public FragmentExerciseEntry() {} //Required empty public constructor


    public static FragmentExerciseEntry newInstance() {
        FragmentExerciseEntry fragment = new FragmentExerciseEntry();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_exercise_entry, null);
        
        builder.setView(view);
        setFieldDefaults(view);
        setOnClickListeners(view);

        return builder.create();
    }

    

    private void setFieldDefaults(View view){
        Context context = view.getContext();
        EditText entryBox = null;
        String quantity = "null";

        if(context != null) {
            send = (FragToMealDiary)context;
            quantity = send.getExerciseCalories();
            entryBox = (EditText)view.findViewById(R.id.inputQty);
            entryBox.setText(quantity);
        }
    }

    private void setOnClickListeners(View view){
        Button btnOk = (Button)view.findViewById(R.id.addItem);
        Button btnCancel = (Button)view.findViewById(R.id.cancelTask);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText entryBox = (EditText) view.findViewById(R.id.inputQty);
                Integer value = Integer.parseInt(entryBox.getText().toString());
                Context context = view.getContext();
                
                if(value < 0 || value > ENTRY_LIMIT) {
                    entryBox.setError("Invalid input must be between 0 and 9999 inclusive");
                }
                else {
                    if (context != null) {
                        send = (FragToMealDiary)context;
                        send.setExerciseCalories(value);
                        dismiss();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}