package me.ning.picapiget.util.http;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {



    public static void responseHeader(HttpServletResponse response, String fileName){
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" +fileName);// 设置文件名
    }


}
