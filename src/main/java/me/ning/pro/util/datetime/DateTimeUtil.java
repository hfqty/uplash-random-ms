package me.ning.pro.util.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {


    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public static Date now(){
        return new Date();
    }
}
