package com.aashdit.digiverifier.utils;

import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ApplicationDateUtils {

    static SimpleDateFormat sdfDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
    static int finYrStartDay = 1;
    static int finYrStartMon = 4;


    public static String getStringTodayAsDDMMYYYY() {
        Calendar calendarObj = Calendar.getInstance();
        String strTodayAsDDMMYYYY = "";
        calendarObj.clear(Calendar.HOUR);
        calendarObj.clear(Calendar.MINUTE);
        calendarObj.clear(Calendar.SECOND);
        Date todayDate = calendarObj.getTime();
        strTodayAsDDMMYYYY = sdfDDMMYYYY.format(todayDate);
        return strTodayAsDDMMYYYY;
    }

    public static String subtractNoOfDaysFromDateAsDDMMYYYY(Date dtDate, int intDaysToSubtract) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dtDate);
        cal.add(Calendar.DATE, -intDaysToSubtract);

        return new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
    }
}
