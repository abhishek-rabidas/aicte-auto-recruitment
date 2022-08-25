package org.aicte.sih.SIHProject.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date parseDateString(String dateString, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Couldn't parse date : " + dateString);
        }
        return null;
    }


    public static Date getDate(Date stamp, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(sdf.format(stamp));
        } catch (ParseException e) {
            System.out.println("Couldn't parse date : " + format);
        }
        return null;
    }

    public static String toDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    public static Date absoluteStart(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

    public static Date absoluteEnd(Date date) {
        return DateUtils.addMilliseconds(
                DateUtils.addDays(absoluteStart(date), 1), -1);
    }

    public static Date asDate(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    public static Date getNextDate(String endDateTime) {
        Calendar c=Calendar.getInstance();
        c.setTime(parseDateString(endDateTime,"yyyy-MM-dd"));
        c.add(Calendar.DATE,1);
        return c.getTime();
    }
}
