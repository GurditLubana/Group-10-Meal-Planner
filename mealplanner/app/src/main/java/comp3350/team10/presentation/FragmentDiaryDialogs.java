package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;

public class FragmentDiaryDialogs extends FragmentDialogCommon {
    public static final String TAG = "ModEntryDialog";  // tag name of this fragment for reference in the fragment manager
    private FragToMealDiary sendToDiary;                // Interface for communication with parent activity
    private FragToRecipeBook sendtoRecipes;             // the type of dialog to show
    private TextView unitText;                          // static unit label
    private CheckBox isSubstitute;
    private String mode;

    private final String EDIT_QTY = "EDIT_QTY";
    private final String GOAL_CALORIE = "GOAL_CALORIE";
    private final String ACTUAL_EXERCISE = "ACTUAL_EXERCISE";
    private final String DRINK_INGRDIENT = "DRINK_INGREDIENT";


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_diary_dialogs, null);
        Context context = view.getContext();
        Bundle args = getArguments();

        this.mode = args.getString("type");

        super.setBtnCancel(view.findViewById(R.id.btnCancel));
        super.setBtnOk(view.findViewById(R.id.btnOk));
        super.setTitle(view.findViewById(R.id.dialogTitle));
        super.setUnitSpinner(view.findViewById(R.id.inputUnit));
        super.setInputQuantity(view.findViewById(R.id.inputQty));
        this.unitText = view.findViewById(R.id.inputUnitText);
        this.isSubstitute = (CheckBox)view.findViewById(R.id.isSubstitute);
        this.isSubstitute.setVisibility(View.INVISIBLE);

        if (context != null) {
            if(context instanceof FragToMealDiary) {
                setupDiaryDialog(context);
            }
            else if(context instanceof FragToRecipeBook) {
                setupRecipeDialog(context);
            }
        }

        builder.setView(view);

        return builder.create();
    }

    private void setupDiaryDialog(Context context) {
        this.sendToDiary = (FragToMealDiary) context;

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

        setDiaryOnClickListeners();
    }

    private void setupRecipeDialog(Context context) {
        this.sendtoRecipes = (FragToRecipeBook) context;

        setLOLEditDialogFieldDefaults();
        switch (mode) {
            case DRINK_INGRDIENT:
                isSubstitute.setChecked(this.sendtoRecipes.getIsSubstitute());
                isSubstitute.setVisibility(View.VISIBLE);
                break;
        }

        setRecipeOnClickListeners();
    }

    private void setEditDialogFieldDefaults() {
        Edible.Unit unit = Edible.Unit.serving;
        ArrayAdapter<String> adapter = null;
        String quantity = "null";

        super.getTitle().setText("Edit Quantity");

        if (this.sendToDiary != null && sendToDiary instanceof FragToMealDiary) {
            quantity = this.sendToDiary.getEntryQty();
            unit = this.sendToDiary.getEntryUnit();

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

    private void setLOLEditDialogFieldDefaults() {
        Edible.Unit unit = Edible.Unit.serving;
        ArrayAdapter<String> adapter = null;
        String quantity = "null";

        super.getTitle().setText("Edit Quantity");

        if (this.sendtoRecipes != null && sendtoRecipes instanceof FragToRecipeBook) {
            quantity = this.sendtoRecipes.getEntryQty();
            unit = this.sendtoRecipes.getEntryUnit();

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
        if (this.sendToDiary != null && sendToDiary instanceof FragToMealDiary) {
            super.getTitle().setText("Set New Calorie Goal");
            super.getUnitSpinner().setVisibility(View.INVISIBLE);
            this.unitText.setVisibility(View.VISIBLE);
            super.getInputQuantity().setText(this.sendToDiary.getGoalCalories());
        }
    }

    private void setExerciseActualFieldDefaults() {
        if (this.sendToDiary != null && sendToDiary instanceof FragToMealDiary) {
            super.getTitle().setText("Set Exercise Calories");
            super.getUnitSpinner().setVisibility(View.INVISIBLE);
            this.unitText.setVisibility(View.VISIBLE);
            super.getInputQuantity().setText(this.sendToDiary.getExerciseCalories());
        }
    }

    private void setDiaryOnClickListeners() {
        super.getBtnOk().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double value;

                if (!getInputQuantity().getText().toString().equals("")) {
                    value = Double.parseDouble(getInputQuantity().getText().toString());

                    if (value >= Constant.ENTRY_MIN_VALUE && value <= Constant.ENTRY_MAX_VALUE) {
                        if (sendToDiary != null && sendToDiary instanceof FragToMealDiary) {
                            switch (mode) {
                                case EDIT_QTY:
                                    sendToDiary.setEntryQty(value, getUnitSpinner().getSelectedItem().toString());
                                    break;
                                case GOAL_CALORIE:
                                    sendToDiary.setGoalCalories(value);
                                    break;
                                case ACTUAL_EXERCISE:
                                    sendToDiary.setExerciseCalories(value);
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

    public void setRecipeOnClickListeners() {
        super.getBtnOk().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double value;

                if (!getInputQuantity().getText().toString().equals("")) {
                    value = Double.parseDouble(getInputQuantity().getText().toString());

                    if (value >= Constant.ENTRY_MIN_VALUE && value <= Constant.ENTRY_MAX_VALUE) {
                        if (sendtoRecipes != null && sendtoRecipes instanceof FragToRecipeBook) {
                            sendtoRecipes.editEntry(value, getUnitSpinner().getSelectedItem().toString(), isSubstitute.isChecked());
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