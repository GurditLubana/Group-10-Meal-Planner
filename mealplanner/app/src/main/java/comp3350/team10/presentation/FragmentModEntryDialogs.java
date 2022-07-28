package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import comp3350.team10.R;
import comp3350.team10.objects.Constant;
import comp3350.team10.objects.Edible;
import comp3350.team10.objects.Validator;

public class FragmentModEntryDialogs extends FragmentDialogCommon {
    public static final String TAG = "ModEntryDialogs";  //Tag name of this fragment (for reference in the fragment manager)

    private FragToRecipeBook sendToRecipes;     //Interface used for communication with the RecipeBook activity
    private FragToMealDiary sendToDiary;        //Interface used for communication with the MealDiary activity
    private String mode;                        //The type of dialog we'd like (uses Entry modes from interfaces)

    private CheckBox isSubstitute;      //is substitute checkbox
    private TextView unitText;          //Unit label

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_mod_entry_dialogs, null);
        Context context = view.getContext();
        Bundle args = getArguments();

        super.setBtnCancel(view.findViewById(R.id.btnCancel));
        super.setBtnOk(view.findViewById(R.id.btnOk));
        super.setTitle(view.findViewById(R.id.dialogTitle));
        super.setUnitSpinner(view.findViewById(R.id.inputUnit));
        super.setInputQuantity(view.findViewById(R.id.inputQty));
        this.unitText = view.findViewById(R.id.inputUnitText);
        this.isSubstitute = view.findViewById(R.id.isSubstitute);
        this.isSubstitute.setVisibility(View.INVISIBLE);
        this.mode = args.getString(Constant.DIALOG_TYPE);

        if (context != null) {
            if (context instanceof FragToMealDiary) { //meal diary loaded the fragment
                setupDiaryDialog(context, FragToMealDiary.EntryMode.valueOf(mode));
            } else if (context instanceof FragToRecipeBook) { //recipe book loaded the fragment
                setupRecipeDialog(context, FragToRecipeBook.EntryMode.valueOf(mode));
            }
        }

        return builder.setView(view).create();
    }

    private void setupDiaryDialog(Context context, FragToMealDiary.EntryMode mode) {
        this.sendToDiary = (FragToMealDiary) context;
        setDiaryOnClickListeners();

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
    }

    private void setupRecipeDialog(Context context, FragToRecipeBook.EntryMode mode) {
        this.sendToRecipes = (FragToRecipeBook) context;
        setEditDialogFieldDefaults();
        setRecipeOnClickListeners();

        switch (mode) {
            case DRINK_INGREDIENT:
                isSubstitute.setChecked(this.sendToRecipes.getIsSubstitute());
                isSubstitute.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setEditDialogFieldDefaults() {
        Edible.Unit unit = Edible.Unit.serving;
        ArrayAdapter<String> adapter;
        String quantity = "null";

        if (this.sendToDiary != null && sendToDiary instanceof FragToMealDiary) {
            quantity = this.sendToDiary.getEntryQty();
            unit = this.sendToDiary.getEntryUnit();
        } else if (this.sendToRecipes != null && sendToRecipes instanceof FragToRecipeBook) {
            quantity = this.sendToRecipes.getEntryQty();
            unit = this.sendToRecipes.getEntryUnit();
        }

        super.getTitle().setText("Edit Quantity");
        super.getInputQuantity().setText(quantity);
        super.initSpinner();

        if (super.getUnitSpinner().getAdapter() instanceof ArrayAdapter) {
            adapter = (ArrayAdapter) super.getUnitSpinner().getAdapter();
            super.getUnitSpinner().setSelection(adapter.getPosition(unit.name()));
        }

        super.getUnitSpinner().setVisibility(View.VISIBLE);
        this.unitText.setVisibility(View.INVISIBLE);
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

            public void onClick(View v) {
                FragToMealDiary.EntryMode currMode = FragToMealDiary.EntryMode.valueOf(mode);
                String enteredValue = getInputQuantity().getText().toString();
                Double value;

                try {
                    Validator.validStringInputatLeastOne(enteredValue, "");
                    value = Double.parseDouble(enteredValue);

                    if (sendToDiary != null && sendToDiary instanceof FragToMealDiary) {
                        switch (currMode) {
                            case EDIT_QTY:
                                Validator.atLeastOne(value, "");
                                sendToDiary.setEntryQty(value, getUnitSpinner().getSelectedItem().toString());
                                break;
                            case GOAL_CALORIE:
                                Validator.atLeastZero(value, "");
                                sendToDiary.setGoalCalories(value);
                                break;
                            case ACTUAL_EXERCISE:
                                Validator.atLeastZero(value, "");
                                sendToDiary.setExerciseCalories(value);
                                break;
                        }
                        closeDialog();
                    }
                } catch (Exception e) {
                    getInputQuantity().setError("Invalid input must be between 0 and 9999");
                }
            }
        });

        super.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                closeDialog();
            }
        });
    }

    public void setRecipeOnClickListeners() {
        super.getBtnOk().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String enteredValue = getInputQuantity().getText().toString();
                Double value;

                if (sendToRecipes != null && sendToRecipes instanceof FragToRecipeBook) {
                    try {
                        Validator.validStringInputatLeastOne(enteredValue, "");
                        value = Double.parseDouble(enteredValue);
                        Validator.atLeastOne(value, "");
                        sendToRecipes.editEntry(value, getUnitSpinner().getSelectedItem().toString(), isSubstitute.isChecked());
                        closeDialog();
                    } catch (Exception e) {
                        getInputQuantity().setError("Invalid input must be between 0 and 9999");
                    }
                }
            }
        });

        super.getBtnCancel().setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                closeDialog();
            }
        });
    }

    private void closeDialog() {
        dismiss();
    }
}