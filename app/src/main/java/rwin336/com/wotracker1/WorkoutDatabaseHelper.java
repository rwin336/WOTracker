package rwin336.com.wotracker1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by riwinters on 1/26/17.
 */

public class WorkoutDatabaseHelper extends SQLiteOpenHelper {

    private static WorkoutDatabaseHelper s_instance;
    private static final String DB_NAME = "wos.sqlite";
    private static final int VERSION = 1;


    public WorkoutDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
