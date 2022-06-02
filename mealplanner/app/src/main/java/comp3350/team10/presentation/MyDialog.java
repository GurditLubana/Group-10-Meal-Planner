import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import comp3350.team10.R;
import comp3350.team10.presentation.MainActivity;

public static class MyDialog extends Dialog {

    private final LayoutInflater mInflater;

    public MyDialog(Context context) {
        super(context);

        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getWindow().getDecorView().setSystemUiVisibility(MainActivity.getImmersiveModeFlags());

        View view = mInflater.inflate(R.layout.maps_dialog, null);
        setContentView(view);
    }

    @Override
    public void show() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        super.show();
    }
}