package com.example.dklearn.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Service
public class DateUtil {
    public static  long   getTimeDifference(LocalDateTime dateTime1, LocalDateTime dateTime2){
        Duration duration= Duration.between(dateTime1,dateTime2);
        return duration.toMinutes();
    }

    public static boolean isDateAfter(LocalDateTime time){
        return  LocalDateTime.now().isAfter(time);
    }

    public String dateFormat(Date strDate){
        return new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(new Date());
    }


    public static String dateFormat(String strDate){
        try{
            Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            return formatter.format(date1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public LocalDateTime getTransactionDate(){
        ZoneId zoneId = ZoneId.of ( "Africa/Lagos" );
        LocalDateTime ldt = new Date().toInstant().atZone(zoneId).toLocalDateTime();
        return ldt;
    }
}
