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

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDiaryDialogs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDiaryDialogs extends DialogFragment {
    public static String TAG = "MealEntryDialog";
    private FragToMealDiary send;
    private FragToMealDiary.EntryMode mode;
    private Button btnCancel;
    private Button btnOk;
    private TextView title;
    private EditText quantity;
    private TextView unitText;
    private Spinner unitSpinner;

    public FragmentDiaryDialogs() {
        // Required empty public constructor
    }


    public static FragmentDiaryDialogs newInstance(String param1, String param2) {
        FragmentDiaryDialogs fragment = new FragmentDiaryDialogs();
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_diary_dialogs, null);
        Context context = view.getContext();
        this.btnCancel = view.findViewById(R.id.btnCancel);
        this.btnOk = view.findViewById(R.id.btnOk);
        this.title = view.findViewById(R.id.dialogTitle);
        this.quantity = view.findViewById(R.id.inputQty);
        this.unitText = view.findViewById(R.id.inputUnitText);
        this.unitSpinner = view.findViewById(R.id.inputUnit);
        ;

        if (context != null && context instanceof FragToMealDiary) {
            this.send = (FragToMealDiary) context;
            this.mode = this.send.getEntryMode();

            switch (mode) {
                case EDIT_QTY:
                    setEditDialogFieldDefaults();
                    break;
                case GOAL_CALORIE:
                    setCalorieGoalFieldDefaults();
                    break;
                case ACTUAL_EXERCISE:
                    setExerciseActualFieldDefaults();
                    break;
            }
            setOnClickListeners();
        }

        builder.setView(view);
        return builder.create();
    }

    private void setEditDialogFieldDefaults() {
        Edible.Unit unit = Edible.Unit.serving;
        int size = Edible.Unit.values().length;
        ArrayAdapter<String> adapter = null;
        String quantity = "null";
        String[] items = new String[size];

        this.title.setText("Edit Quantity");
        for (int i = 0; i < size; i++) {
            items[i] = Edible.Unit.values()[i].name();
        }

        adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_unit_items, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (this.send != null && send instanceof FragToMealDiary) {
            quantity = this.send.getEntryQty();
            unit = this.send.getEntryUnit();

            this.quantity.setText(quantity);
            this.unitSpinner.setAdapter(adapter);
            this.unitSpinner.setSelection(adapter.getPosition(unit.name()));
            this.unitSpinner.setVisibility(View.VISIBLE);
            this.unitText.setVisibility(View.INVISIBLE);
        }
    }

    private void setCalorieGoalFieldDefaults() {
        if (this.send != null && send instanceof FragToMealDiary) {
            this.title.setText("Set New Calorie Goal");
            this.unitSpinner.setVisibility(View.INVISIBLE);
            this.unitText.setVisibility(View.VISIBLE);
            this.quantity.setText(this.send.getGoalCalories());
        }
    }

    private void setExerciseActualFieldDefaults() {
        if (this.send != null && send instanceof FragToMealDiary) {
            this.title.setText("Set Exercise Calories");
            this.unitSpinner.setVisibility(View.INVISIBLE);
            this.unitText.setVisibility(View.VISIBLE);
            this.quantity.setText(this.send.getExerciseCalories());
        }
    }

    private void setOnClickListeners() {

        this.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer value = Integer.parseInt(quantity.getText().toString());

                if (value >= Constant.ENTRY_MIN_VALUE && value <= Constant.ENTRY_MAX_VALUE) {
                    if (send != null && send instanceof FragToMealDiary) {
                        switch (mode) {
                            case EDIT_QTY:
                                send.setEntryQty(value, unitSpinner.getSelectedItem().toString());
                                break;
                            case GOAL_CALORIE:
                                send.setGoalCalories(value);
                                break;
                            case ACTUAL_EXERCISE:
                                send.setExerciseCalories(value);
                                break;
                        }
                        dismiss();
                    }
                } else {
                    quantity.setError("Invalid input must be between 0 and 9999 inclusive");
                }
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}