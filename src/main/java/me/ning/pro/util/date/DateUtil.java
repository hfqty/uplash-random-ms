package me.ning.pro.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static String today(){
        return dateFormat.format(new Date());
    }
}
