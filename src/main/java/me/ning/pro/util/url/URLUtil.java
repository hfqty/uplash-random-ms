package me.ning.pro.util.url;

import me.ning.pro.util.http.RequestUtil;

import java.net.HttpURLConnection;

public class URLUtil {


    public static String RedirectUrl(String url){
        try {
            System.out.println("访问地址:" + url);
            HttpURLConnection conn = RequestUtil.connection(url);
            String location = conn.getHeaderField("Location");
            System.out.println("跳转地址:"+location);
            return location;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
