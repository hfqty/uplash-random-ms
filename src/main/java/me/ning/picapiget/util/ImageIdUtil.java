package me.ning.picapiget.util;

public class ImageIdUtil {

    private final static int START_INDEX = 28;

    private final static int END_INDEX = 60;

    public static String getImageId(String url){
        return String.valueOf(url.substring(START_INDEX,END_INDEX));
    }
}
