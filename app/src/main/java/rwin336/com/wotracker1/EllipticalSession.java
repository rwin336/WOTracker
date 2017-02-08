package rwin336.com.wotracker1;

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
 * Date:   2/5/17.
 */

public class EllipticalSession {

    private long m_id;
    private Date m_start_date;
    private String m_course;
    private int m_calories;
    private int m_time;
    private int m_strides;
    private int m_resistance_high;
    private int m_resistance_low;

    public EllipticalSession(Date start_date,
                             int time,
                             String course,
                             int strides,
                             int calories,
                             int resistance_high,
                             int resistance_low) {
        m_start_date = start_date;
        m_course = course;
        m_time = time;
        m_strides = strides;
        m_calories = calories;
        m_resistance_high = resistance_high;
        m_resistance_low = resistance_low;
    }

    public long getId() {
        return m_id;
    }

    public void setId(long id) {
        m_id = id;
    }

    public Date getStartDate() {
        return m_start_date;
    }

    public void setStartDate(Date start_date) {
        m_start_date = start_date;
    }

    public int getDurationSeconds(long end_millis) {
        return (int)((end_millis - m_start_date.getTime()) / 1000);
    }

    public String getCourse() {
        return m_course;
    }

    public int getTime() {
        return m_time;
    }

    public int getStrides() {
        return m_strides;
    }

    public int getCalories() {
        return m_calories;
    }

    public int getResistanceHigh() {
        return m_resistance_high;
    }

    public int getResistanceLow() {
        return m_resistance_low;
    }

}
