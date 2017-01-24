package rwin336.com.wotracker1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.DialogInterface;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.content.Intent;
import android.content.res.Resources;

public class WoMainActivity extends AppCompatActivity {

    private static final String TAG = "WoMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void recordWorkout(View v) {
        Log.d(TAG, "Clicked Record workout -  starting dialog");
        AlertDialog.Builder builder =
                new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));

        List<String> listItems = new ArrayList<String>();
        Resources res = getResources();
        listItems.add(res.getString(R.string.workout1));
        listItems.add(res.getString(R.string.workout2));
        listItems.add(res.getString(R.string.eliptical));
        CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
        builder.setSingleChoiceItems(items, 0, null);

        // Add the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Log.d(TAG, "Clicked OK: ID = " + id);
                ListView lw = ((AlertDialog)dialog).getListView();
                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());

                String selected_workout = String.valueOf(checkedItem);
                Log.d(TAG, "Checked item = " + selected_workout);
                Intent myIntent = null;

                Resources res = getResources();
                if (selected_workout.equals(res.getString(R.string.workout1))) {
                    myIntent = new Intent(getApplicationContext(), RecordWorkout1Activity.class);
                } else if(selected_workout.equals(res.getString(R.string.workout2))) {
                    myIntent = new Intent(getApplicationContext(), RecordWorkout2Activity.class);
                } else if(selected_workout.equals(res.getString(R.string.eliptical)))
                    myIntent = new Intent(getApplicationContext(), ElipticalWorkout.class);
                if (myIntent != null) {
                    startActivity(myIntent);
                }
                return;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setTitle(R.string.select_workout);
        dialog.show();
    }

    public void reviewPastWorkouts(View v) {
        Log.d(TAG, "Review Past workouts");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wo_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
