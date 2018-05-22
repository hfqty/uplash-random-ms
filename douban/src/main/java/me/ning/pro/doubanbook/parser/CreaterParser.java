package me.ning.pro.doubanbook.parser;

import me.ning.pro.doubanbook.entity.Creater;
import me.ning.pro.doubanbook.util.URLUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

public class CreaterParser {
    public static Creater creater(String url) {
        long start = System.currentTimeMillis();
        System.out.println("开始：" + new Date());
        Connection connection = Jsoup.connect(url).timeout(30000);
        Document douList = null;
        try {
            douList = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element body = douList.body();
        //内容
        Element content = body.getElementById("content");
        //标题
        String title = content.getElementsByTag("h1").first().text();
        //主要内容
        Elements article = content.getElementsByClass("article");
        //豆列信息
        Element doulist_info = article.first().getElementById("doulist-info");
        Element avatar = doulist_info.getElementsByClass("avatar").first();
        Element bd = doulist_info.getElementsByClass("bd").first();
        Element doulist_about = bd.getElementsByClass("doulist-about").first();
        Element meta = doulist_info.getElementsByClass("meta").first();
        Element timespan = meta.getElementsByClass("time").first();
        Element img = avatar.getElementsByTag("img").first();
        String imgsrc = img.attr("src");
        String nickname = img.attr("alt");
        String homepage = avatar.attr("href");
        String time = timespan.text();
        String introduction = null;
        if (doulist_about != null)
            introduction = doulist_about.text();
        Creater creater = new Creater(title, nickname, imgsrc, homepage, time, introduction);
        System.out.println(creater);
        long end = System.currentTimeMillis() - start;
        System.out.println("结束：" + new Date() + "\n耗时：" + end + "ms");
        return creater;
    }
}
