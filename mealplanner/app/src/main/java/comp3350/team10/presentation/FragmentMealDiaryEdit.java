package comp3350.team10.presentation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import comp3350.team10.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMealDiaryEdit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMealDiaryEdit extends DialogFragment {

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

        return builder.create();
    }

    public static String TAG = "EditMealEntryDialog";

    private void setFieldDefaults(View view){

    }

    private void setOnClickListeners(View view){
        Button btnOk = (Button) view.findViewById(R.id.btnOK);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
    }
}