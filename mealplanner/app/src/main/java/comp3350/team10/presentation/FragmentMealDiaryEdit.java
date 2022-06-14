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

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FragmentMealDiaryEdit extends DialogFragment {
    private final static int ENTRY_LIMIT = 9999;
    public static String TAG = "EditMealEntryDialog";
    private FragToMealDiary send;

    public FragmentMealDiaryEdit() {} //Required empty public constructor


    public static FragmentMealDiaryEdit newInstance() {
        FragmentMealDiaryEdit fragment = new FragmentMealDiaryEdit();
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_meal_diary_edit, null);
        builder.setView(view);

        setFieldDefaults(view);
        setOnClickListeners(view);

        return builder.create();
    }

    private void setFieldDefaults(View view){
        Edible.Unit unit = Edible.Unit.serving;
        int size = Edible.Unit.values().length;
        ArrayAdapter<String> adapter = null;
        Context context = view.getContext();
        Spinner inputSpinner = null;
        EditText entryBox = null;
        String quantity = "null";
        String[] items = new String[size];

        for(int i = 0; i < size; i++){
            items[i] = Edible.Unit.values()[i].name();
        }

        adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_unit_items, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(context != null) {
            send = (FragToMealDiary) context;
            quantity = send.getEntryQty();
            unit = send.getEntryUnit();
            entryBox = (EditText) view.findViewById(R.id.inputQty);
            entryBox.setText(quantity);

            inputSpinner = (Spinner) view.findViewById(R.id.inputUnit);
            inputSpinner.setAdapter(adapter);
            inputSpinner.setSelection(adapter.getPosition(unit.name()));
        }
    }

    private void setOnClickListeners(View view){
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        Button btnOk = (Button) view.findViewById(R.id.btnOk);
        
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText entryBox = (EditText) view.findViewById(R.id.inputQty);
                Integer value = Integer.parseInt(entryBox.getText().toString());
                Spinner inputSpinner = (Spinner) view.findViewById(R.id.inputUnit);
                Context context = view.getContext();
                
                if(value < 0 || value > ENTRY_LIMIT){
                    entryBox.setError("Invalid input must be between 0 and 9999 inclusive");
                }
                else {
                    if(context != null) {
                        send = (FragToMealDiary) context;
                        send.setEntryQty(value, (String) inputSpinner.getSelectedItem());
                        dismiss();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}