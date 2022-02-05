package com.vasileungureanu.katas.guitarshacklegacy;

import java.util.Calendar;
import java.util.Date;

public class CurrentSystemTime {
    public Date get() {
        return Calendar.getInstance().getTime();
    }
}