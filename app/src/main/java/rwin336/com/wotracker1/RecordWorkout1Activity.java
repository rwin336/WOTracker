package rwin336.com.wotracker1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.util.Calendar;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.app.Dialog;
import java.text.DateFormat;

/**
 * Copyright 2017 by rwin
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the license.
 * <p>
 * Author: rwin
 * Date:   2/4/17.
 *
 */
public class RecordWorkout1Activity extends AppCompatActivity {

    private static final String TAG = "RecordWorkout1Activity";
    private TextView mWoStartedTextView;
    private Calendar calendar;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mWoStartedTextView = (TextView)findViewById(R.id.wo_startedTextView);
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        showDate();
    }

    public void recordWorkout(View v) {
        Log.d(TAG, "Button: recordWorkout");

        // Record workout
        finish();
    }

    public void cancelRecordWorkout(View v) {
        Log.d(TAG, "Button: CancelRecordWorkout");
        finish();
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

    protected Dialog onCreateDialog() {
        Log.d(TAG, "onCreateDialog");
        return null;
    }

    public DatePickerDialog setDate(View v) {
        Log.d(TAG, "Showing user a date dialog");
        DatePickerDialog dpd = new DatePickerDialog(this,  myDateListener, mYear, mMonth, mDay);
        dpd.show();
        return dpd;
    }

    private void showDate() {
        mWoStartedTextView.setText(new StringBuilder().append(mMonth).append("/")
                .append(mDay).append("/").append(mYear));
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int year, int month, int day) {
                    Log.d(TAG, "onDateSet: Arg1: " + year + ", Arg2: " + month + ", Arg3: " + day);
                    mMonth = month + 1;
                    mYear = year;
                    mDay = day;
                    showDate();
                }
            };
}
