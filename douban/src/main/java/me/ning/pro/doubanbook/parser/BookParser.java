package me.ning.pro.doubanbook.parser;

import me.ning.pro.doubanbook.entity.DoubanBook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookParser {
    public static List<DoubanBook> items(String url)  {
        long startt = System.currentTimeMillis();
        System.out.println("书籍开始：" + new Date());
        int start = 0;
        List<DoubanBook> allBooks = new ArrayList<>();
        for (; ; ) {
            List<DoubanBook> doubanBookList = doulistBooks(url, start);
            start = 25 + start;
            if (doubanBookList == null)
                break;
            else
                allBooks.addAll(doubanBookList);
            System.out.println("休息一下，马上回来");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("线程休眠失败");
            }

        }
        long end = System.currentTimeMillis() - startt;
        System.out.println("书籍结束：" + new Date() + "\n耗时：" + end / 1000 + "s");
        return allBooks;
    }

    private static List<DoubanBook> doulistBooks(String url, int start) {
        url = url + "?start=" + start;
        List<DoubanBook> doubanBookList = new ArrayList<>();
        Connection doubanbook = Jsoup.connect(url).timeout(30000);
        Document douList = null;
        try {
            douList = doubanbook.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element body = douList.body();
        Element article = body.getElementById("content").getElementsByClass("article").first();
        Elements doulist_items = article.getElementsByClass("doulist-item");
        if (doulist_items == null || doulist_items.isEmpty()) {
            return null;
        }
        for (Element item : doulist_items) {
            Element mod = item.getElementsByClass("mod").first();
            Element bd = mod.getElementsByClass("bd").first();
            String book_info = bd.text();
            if (book_info.contains(" ")) {
                Element title = bd.getElementsByClass("title").first();
                String name = title.text();
                Element titlea = title.getElementsByTag("a").first();
                String burl = titlea.attr("href");
                Element rating = bd.getElementsByClass("rating").first();
                Elements rates = null;
                if (rating != null)
                    rates = rating.getElementsByTag("span");
                String rate = "暂无";
                String ratep = "评分";
                if (rates != null && rates.size() > 2) {
                    rate = rates.get(1).text();
                    ratep = rates.get(2).text();
                }
                Element abstracts = bd.getElementsByClass("abstract").first();
                String[] babstracts = abstracts.text().split(" ");
                Element ft = mod.getElementsByClass("ft").first();
                Element content = null;
                if (ft != null)
                    content = ft.getElementsByClass("content").first();

                String comment = "";
                if (content != null) {
                    comment = content.text();
                }
                System.out.println(name);
                doubanBookList.add(new DoubanBook(burl, name, rate, ratep, comment, babstracts));
            } else {
                continue;
            }
        }

        return doubanBookList;
    }
}
