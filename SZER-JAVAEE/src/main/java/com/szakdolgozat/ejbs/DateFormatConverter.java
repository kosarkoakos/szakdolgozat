package com.szakdolgozat.ejbs;

import javax.ejb.Singleton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ákos on 2016.03.14..
 */
@Singleton
public class DateFormatConverter {

    java.util.Calendar calendar = Calendar.getInstance();

    SimpleDateFormat yearMonth = new SimpleDateFormat("YYYY. MM.");
    SimpleDateFormat fullDate = new SimpleDateFormat("YYYY. MM. dd.");
    SimpleDateFormat fullDateTime = new SimpleDateFormat("YYYY. MM. dd. HH:mm");

    public String convertToYearMonth(Date date){
        return yearMonth.format(date);
    }

    public String convertToFullDate(Date date){
        return fullDate.format(date);
    }

    public String convertTofullDateTime(Date date){
        return fullDateTime.format(date);
    }


}
