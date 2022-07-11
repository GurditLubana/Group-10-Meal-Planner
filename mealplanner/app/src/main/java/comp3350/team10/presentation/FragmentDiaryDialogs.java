package comp3350.team10.presentation;

import comp3350.team10.R;
import comp3350.team10.objects.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FragmentDiaryDialogs extends FragmentDialogCommon {
    public static final String TAG = "MealEntryDialog"; // tag name of this fragment for reference in the fragment manager
    private FragToMealDiary send;                 // Interface for communication with parent activity
    private FragToMealDiary.EntryMode mode;       // the type of dialog to show
    private TextView unitText;                    // static unit label

    public FragmentDiaryDialogs() {
        super();
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
        super.setBtnCancel(view.findViewById(R.id.btnCancel));
        super.setBtnOk(view.findViewById(R.id.btnOk));
        super.setTitle(view.findViewById(R.id.dialogTitle));
        super.setUnitSpinner(view.findViewById(R.id.inputUnit));
        super.setInputQuantity(view.findViewById(R.id.inputQty));
        this.unitText = view.findViewById(R.id.inputUnitText);

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
        ArrayAdapter<String> adapter = null;
        String quantity = "null";

        super.getTitle().setText("Edit Quantity");

        if (this.send != null && send instanceof FragToMealDiary) {
            quantity = this.send.getEntryQty();
            unit = this.send.getEntryUnit();

            super.getInputQuantity().setText(quantity);
            super.initSpinner();
            if (super.getUnitSpinner().getAdapter() instanceof ArrayAdapter) {
                adapter = (ArrayAdapter) super.getUnitSpinner().getAdapter();
                super.getUnitSpinner().setSelection(adapter.getPosition(unit.name()));
            }
            super.getUnitSpinner().setVisibility(View.VISIBLE);
            this.unitText.setVisibility(View.INVISIBLE);
        }
    }

    private void setCalorieGoalFieldDefaults() {
        if (this.send != null && send instanceof FragToMealDiary) {
            super.getTitle().setText("Set New Calorie Goal");
            super.getUnitSpinner().setVisibility(View.INVISIBLE);
            this.unitText.setVisibility(View.VISIBLE);
            super.getInputQuantity().setText(this.send.getGoalCalories());
        }
    }

    private void setExerciseActualFieldDefaults() {
        if (this.send != null && send instanceof FragToMealDiary) {
            super.getTitle().setText("Set Exercise Calories");
            super.getUnitSpinner().setVisibility(View.INVISIBLE);
            this.unitText.setVisibility(View.VISIBLE);
            super.getInputQuantity().setText(this.send.getExerciseCalories());
        }
    }

    private void setOnClickListeners() {

        super.getBtnOk().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double value;

                if (!getInputQuantity().getText().toString().equals("")) {
                    value = Double.parseDouble(getInputQuantity().getText().toString());

                    if (value >= Constant.ENTRY_MIN_VALUE && value <= Constant.ENTRY_MAX_VALUE) {
                        if (send != null && send instanceof FragToMealDiary) {
                            switch (mode) {
                                case EDIT_QTY:
                                    send.setEntryQty(value, getUnitSpinner().getSelectedItem().toString());
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
                        getInputQuantity().setError("Invalid input must be between 0 and 9999 inclusive");
                    }
                }
            }
        });

        super.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}