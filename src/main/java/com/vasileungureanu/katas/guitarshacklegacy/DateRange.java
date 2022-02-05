package com.vasileungureanu.katas.guitarshacklegacy;

import java.util.Calendar;
import java.util.Date;

public class DateRange {
    final CurrentSystemTime currentSystemTime;
    private Date startDate;
    private Date endDate;

    public DateRange(CurrentSystemTime currentSystemTime) {
        this.currentSystemTime = currentSystemTime;

        setDateRange();
    }

    private void setDateRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentSystemTime.get());
        calendar.add(Calendar.YEAR, -1);
        startDate = calendar.getTime();
        calendar.add(Calendar.DATE, 30);
        endDate = calendar.getTime();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}