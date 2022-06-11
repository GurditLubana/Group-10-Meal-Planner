package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import comp3350.team10.R;
import comp3350.team10.objects.ListItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMealDiaryEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMealDiaryEdit extends DialogFragment {
    FragToMealDiary send;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentMealDiaryEdit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMealDiaryEdit.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMealDiaryEdit newInstance(String param1, String param2) {
        FragmentMealDiaryEdit fragment = new FragmentMealDiaryEdit();
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
        builder.setView(view);

        setFieldDefaults(view);
        setOnClickListeners(view);

        return builder.create();
    }

    public static String TAG = "EditMealEntryDialog";

    private void setFieldDefaults(View view){
        ListItem.Unit unit = ListItem.Unit.serving;
        ArrayAdapter<String> adapter = null;
        Context context = view.getContext();
        Spinner inputSpinner = null;
        EditText entryBox = null;
        String quantity = "null";
        int size = ListItem.Unit.values().length;
        String[] items = new String[size];

        for(int i = 0; i < size; i++){
            items[i] = ListItem.Unit.values()[i].name();
        }

        adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_unit_items, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (context != null) {
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
        Button btnOk = (Button) view.findViewById(R.id.btnOK);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText entryBox = (EditText) view.findViewById(R.id.inputQty);
                Integer value = Integer.parseInt(entryBox.getText().toString());
                Spinner inputSpinner = (Spinner) view.findViewById(R.id.inputUnit);
                Context context = view.getContext();
                if(value < 0 || value > 9999){
                    entryBox.setError("Invalid input must be between 0 and 9999 inclusive");
                }
                else {

                    if (context != null) {
                        send = (FragToMealDiary) context;
                        send.setEntryQty(value, (String) inputSpinner.getSelectedItem());
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