package comp3350.team10.presentation;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import comp3350.team10.R;
import comp3350.team10.objects.Edible;

public abstract class FragmentDialogCommon extends DialogFragment {
    private TextView title;                  // Dialog Title
    private EditText inputQuantity;          // input field for item quantity
    private Spinner unitSpinner;             // input field for quantity units
    private Button btnCancel;                // Cancel Button
    private Button btnOk;                    // OK button

    public void initSpinner() {
        int size = Edible.Unit.values().length;
        ArrayAdapter<String> adapter = null;
        String[] items = new String[size];

        for (int i = 0; i < size; i++) {
            items[i] = Edible.Unit.values()[i].name();
        }

        adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_unit_items, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.unitSpinner.setAdapter(adapter);
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public void setBtnOk(Button btnOk) {
        this.btnOk = btnOk;
    }

    public void setInputQuantity(EditText inputQuantity) {
        this.inputQuantity = inputQuantity;
    }

    public void setUnitSpinner(Spinner unitSpinner) {
        this.unitSpinner = unitSpinner;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public Button getBtnCancel() {
        return this.btnCancel;
    }

    public Button getBtnOk() {
        return btnOk;
    }

    public EditText getInputQuantity() {
        return inputQuantity;
    }

    public Spinner getUnitSpinner() {
        return unitSpinner;
    }

    public TextView getTitle() {
        return title;
    }
}
