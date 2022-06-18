package comp3350.team10.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class HSqlDB extends SQLiteOpenHelper implements DataInterface {
    private static final int CURR_VERSION = 1;

    public HSqlDB(@Nullable Context context, @Nullable String name) {
        super(context, name, null, CURR_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        
    }

    @Override //used when the a new version is created
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
