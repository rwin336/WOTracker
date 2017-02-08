package rwin336.com.wotracker1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class EllipticalWorkout extends AppCompatActivity {

    public static final String TAG = "EllipticalWorkout";

    public static final int RESISTANCE_HIGH = 0x001;
    public static final int RESISTANCE_LOW = 0x010;

    private TextView m_wo_started_textview;
    private Calendar calendar;
    private Date m_date;
    private int m_year, m_month, m_day;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle saved_instanceState) {
        super.onCreate(saved_instanceState);
        setContentView(R.layout.elliptical_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        m_wo_started_textview = (TextView) findViewById(R.id.wo_startedTextView);
        calendar = Calendar.getInstance();
        m_date = calendar.getTime();
        m_year = calendar.get(Calendar.YEAR);
        m_month = calendar.get(Calendar.MONTH) + 1;
        m_day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void recordWorkout(View v) {
        Log.d(TAG, "Button: recordWorkout");
        try {
            Date wo_date = getDate();
            String course = getCourse();
            int strides = getStrides();
            int time = getTime();
            int calories = getCalories();
            int resistance_high = getResistance(RESISTANCE_HIGH);
            int resistance_low = getResistance(RESISTANCE_LOW);

            EllipticalSession es =
                    new EllipticalSession(wo_date, time, course, strides, calories,
                            resistance_high, resistance_low);

            EllipticalWorkoutManager ewm = EllipticalWorkoutManager.get(getApplication());
            ewm.insertEllipticalSession(es);
        } catch( java.text.ParseException pe )  {
            Log.e(TAG, "ERROR: ParseException: " + pe.toString());
        }
        finish();
    }

    public int getResistance(int type) {
        switch( type) {
            case RESISTANCE_HIGH:
                return 8;
            case RESISTANCE_LOW:
                return 6;
            default:
                return 0;
        }
    }

    public Date getDate() throws java.text.ParseException {
        TextView start_date = (TextView)findViewById(R.id.wo_startedTextView);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.parse(start_date.getText().toString());
    }

    public int getCalories() {
        EditText calories_edittext = (EditText)findViewById(R.id.calories);
        return Integer.parseInt(calories_edittext.getText().toString());
    }

    public int getTime() {
        EditText time_edittext = (EditText)findViewById(R.id.total_time);
        return Integer.parseInt(time_edittext.getText().toString());
    }

    public String getCourse() {
        Spinner course_spinner = (Spinner)findViewById(R.id.elliptical_course);
        return course_spinner.getSelectedItem().toString();
    }

    public int getStrides() {
        EditText strides_edittext = (EditText)findViewById(R.id.strides);
        return Integer.parseInt(strides_edittext.getText().toString());
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
        DatePickerDialog dpd = new DatePickerDialog(this, myDateListener, m_year, m_month, m_day);
        dpd.show();
        return dpd;
    }

    private void showDate() {
        m_wo_started_textview.setText(new StringBuilder().append(m_month).append("/")
                .append(m_day).append("/").append(m_year));
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            Log.d(TAG, "onDateSet: Arg1: " + year + ", Arg2: " + month + ", Arg3: " + day);
            m_month = month + 1;
            m_year = year;
            m_day = day;
            showDate();
        }
    };

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("EllipticalWorkout Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
