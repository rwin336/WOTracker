package rwin336.com.wotracker1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
 */

public class EllipticalDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "elliptical.sqlite";
    private static final int VERSION = 1;

    private static final String TABLE_ELLIPTICAL = "elliptical";
    private static final String COLUMN_ELLIPTICAL_START_DATE = "start_date";

    private static final String TABLE_SESSION = "session";
    private static final String COLUMN_ELLIPTICAL_SESSION_ID = "session_id";
    private static final String COLUMN_ELLIPTICAL_SESSION_COURSE = "course";
    private static final String COLUMN_ELLIPTICAL_SESSION_STRIDES = "strides";
    private static final String COLUMN_ELLIPTICAL_SESSION_TIME  = "time";
    private static final String COLUMN_ELLIPTICAL_SESSION_CALORIES = "calories";
    private static final String COLUMN_ELLIPTICAL_SESSION_RESISTANCE_HIGH = "resistance_high";
    private static final String COLUMN_ELLIPTICAL_SESSION_RESISTANCE_LOW = "resistance_low";


    public EllipticalDatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the "elliptical  table
        db.execSQL("create table elliptical (" +
                "_id integer primary key autoincrement, start_date integer)");

        // Create the "session" table
        db.execSQL("create table session (" +
                " timestamp integer, course varchar(4), strides integer, time integer," +
                " calories integer, resistance_high integer, resistance_low integer," +
                " session_id integer references elliptical(_id))");
    }

    public long insertEllipticalSession(EllipticalSession es) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ELLIPTICAL_SESSION_COURSE, es.getCourse());
        cv.put(COLUMN_ELLIPTICAL_SESSION_STRIDES, es.getStrides());
        cv.put(COLUMN_ELLIPTICAL_SESSION_TIME , es.getTime());
        cv.put(COLUMN_ELLIPTICAL_SESSION_CALORIES , es.getCalories());
        cv.put(COLUMN_ELLIPTICAL_SESSION_RESISTANCE_HIGH, es.getResistanceHigh());
        cv.put(COLUMN_ELLIPTICAL_SESSION_RESISTANCE_LOW, es.getResistanceLow());
        cv.put(COLUMN_ELLIPTICAL_SESSION_ID, es.getId());
        return getWritableDatabase().insert(TABLE_SESSION, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    public void dropEllipicalDB() {

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELLIPTICAL);

        // Create the "elliptical  table
        db.execSQL("create table elliptical (" +
                "_id integer primary key autoincrement, start_date integer)");

        // Create the "session" table
        db.execSQL("create table session (" +
                " timestamp integer, course varchar(4), strides integer, time integer," +
                " calories integer, resistance_high integer, resistance_low integer," +
                " session_id integer references elliptical(_id))");

    }
}
