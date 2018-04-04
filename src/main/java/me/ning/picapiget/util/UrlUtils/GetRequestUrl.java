package me.ning.picapiget.util.UrlUtils;

import me.ning.picapiget.util.HttpUtils.RequestUtil;

import java.net.HttpURLConnection;

public class GetRequestUrl {


    public static String RedirectUrl(){
        try {
            String url = "https://source.unsplash.com/random/1920x1080";
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
