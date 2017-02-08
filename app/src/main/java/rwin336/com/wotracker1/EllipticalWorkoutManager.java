package rwin336.com.wotracker1;

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
 * Date:   2/5/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class EllipticalWorkoutManager {
    private static final String TAG = "EllipticalWorkoutMgr";

    private static final String PREFS_FILE = "elliptical_workouts";
    private static final String PREF_CURRENT_SESSION_ID =
            "EllipticalWorkoutManager.currentSessionId";

    private static EllipticalWorkoutManager s_elliptical_mgr;
    private Context m_app_context;
    private EllipticalDatabaseHelper m_db_helper;
    private SharedPreferences m_prefs;
    private long m_current_session_id;

    private EllipticalWorkoutManager(Context app_context) {
        m_app_context = app_context;
        m_db_helper = new EllipticalDatabaseHelper(m_app_context);
        m_prefs = m_app_context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        m_current_session_id = m_prefs.getLong(PREF_CURRENT_SESSION_ID, -1);
    }

    public static EllipticalWorkoutManager get(Context c) {
        if ( s_elliptical_mgr == null ) {
            s_elliptical_mgr = new EllipticalWorkoutManager(c.getApplicationContext());
        }
        return s_elliptical_mgr;
    }

    public void insertEllipticalSession(EllipticalSession es) {
        Log.d(TAG, "Inserting Elliptical session into DB");

        m_db_helper.insertEllipticalSession(es);
    }

    public void dropEllipticalDB() {
        m_db_helper.dropEllipicalDB();
    }
}
