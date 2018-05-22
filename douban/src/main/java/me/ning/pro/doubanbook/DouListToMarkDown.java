package me.ning.pro.doubanbook;

import me.ning.pro.doubanbook.entity.Creater;
import me.ning.pro.doubanbook.entity.DoubanBook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DouListToMarkDown {

    public static void main(String []args){
        int doulistid = 1133232;
            doulistid = 1264675;
        String url = "https://www.douban.com/doulist/"+doulistid+"/";
       // DoubanSimulate.loginDouban();
        try {
            parseDouList(url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void parseDouList(String url) throws InterruptedException {
        Creater craeter = parseCreater(url);
        List<DoubanBook> items = parseDouListItems(url);
        try {
            toFile(craeter,items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void toFile(Creater creater, List<DoubanBook> items) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("写入开始：" +new Date());
        ////////////////////////////////////////////////////
        BufferedWriter writer = createFileWriter(creater);
        writer.write("#"+creater.getTitle());
        writer.newLine();
        writer.write(creater.getTime());
        writer.newLine();
        writer.write("!["+creater.getNickname()+"]("+creater.getImg()+")");
        writer.write("   ["+creater.getNickname()+"]("+creater.getHomepage()+")");
        writer.newLine();
        writer.newLine();
        writer.write(creater.getIntroduction());
        /////////////////////////////////////////
        writer.newLine();
        writer.write("共"+items.size()+"本");
        int index = 0;
        for(DoubanBook book :items){
            index = index +1;
            writer.newLine();
            writer.write("### No."+(index)+" "+book.getName());
            writer.newLine();
            writer.write("> **图书名称**：["+book.getName()+"]("+book.getUrl()+")");
            writer.newLine();
            writer.write("> **豆瓣评分**："+book.getRate()+book.getRatePeople());
            writer.newLine();
            writeAbstract(writer,book);
            writer.newLine();
            writer.write(book.getComment());
        }
        if(writer != null){
            writer.close();
        }
        ////////////////////////////////////////////////
        long end = System.currentTimeMillis()-start;
        System.out.println("写入结束："+new Date()+"\n耗时："+end+"ms");
    }

    private static void writeAbstract(BufferedWriter writer, DoubanBook book) throws IOException {
        if(book.getAbstracts().length<=6){
            writer.write("> **"+book.a(0)+"**"+book.a(1));
            writer.newLine();
            writer.write("> **"+book.a(2)+"**"+book.a(3));
            writer.newLine();
            if(book.getAbstracts().length>5) {
                writer.write("> **"+book.a(4) +"**"+ book.a(5));
            }
        }else if (book.getAbstracts().length == 7){
            writer.write("> **"+book.a(0)+"**"+book.a(1) +book.a(2));
            writer.newLine();
            writer.write("> **"+book.a(3)+"**"+book.a(4));
            writer.newLine();
            writer.write("> **"+book.a(5)+"**"+book.a(6));
        }else if(book.getAbstracts().length == 8){
            writer.write("> **"+book.a(0)+"**"+book.a(1) +book.a(2)+book.a(3));
            writer.newLine();
            writer.write("> **"+book.a(4)+"**"+book.a(5));
            writer.newLine();
            writer.write("> **"+book.a(6)+"**"+book.a(7));
        }

    }



    private static BufferedWriter createFileWriter(Creater creater) throws IOException {
        FileWriter fileWriter = createFile(creater);
        return new BufferedWriter(fileWriter);
    }

    private static FileWriter createFile(Creater creater) throws IOException {
        String filePath = "C:\\all\\ilife\\book\\doulist\\"+creater.getTitle()+"by"+creater.getNickname()+".md";
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        return new FileWriter(file);
    }

    private static Creater parseCreater(String url) {
        long start = System.currentTimeMillis();
        System.out.println("开始：" +new Date());
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
        String introduction =doulist_about.text();
        Creater creater = new Creater(title,nickname,imgsrc,homepage,time,introduction);
        System.out.println(creater);
        long end = System.currentTimeMillis()-start;
        System.out.println("结束："+new Date()+"\n耗时："+end+"ms");
        return creater;
    }


    private static List<DoubanBook> parseDouListItems(String url ) throws InterruptedException {
        long startt = System.currentTimeMillis();
        System.out.println("书籍开始："+new Date());
        int start = 0;
        List<DoubanBook> allBooks = new ArrayList<>();
       for(;;) {
           List<DoubanBook> doubanBookList = doulistBooks(url, start);
           start = 25 + start;
           if(doubanBookList == null)
               break;
           else
               allBooks.addAll(doubanBookList);
           System.out.println("休息一下，马上回来");
           Thread.sleep(3000);

       }
        long end = System.currentTimeMillis()-startt;
        System.out.println("书籍结束："+new Date()+"\n耗时："+end/1000+"s");
       return allBooks;
    }

    private static List<DoubanBook> doulistBooks(String url,int start) {
        url = url +"?start="+start;
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
        if(doulist_items == null || doulist_items.isEmpty()){
            return null;
        }
        for(Element item : doulist_items){
            Element mod = item.getElementsByClass("mod").first();
            Element bd = mod.getElementsByClass("bd").first();
            String book_info = bd.text();
            if(book_info.contains(" ")){
                Element title = bd.getElementsByClass("title").first();
                String name = title.text();
                Element titlea = title.getElementsByTag("a").first();
                String burl = titlea.attr("href");
                Elements rates = bd.getElementsByClass("rating").first().getElementsByTag("span");
                String rate = "暂无";
                String ratep = "评分";
                if(rates.size() > 2){
                     rate = rates.get(1).text();
                     ratep = rates.get(2).text();
                }
                Element abstracts = bd.getElementsByClass("abstract").first();
                String []babstracts = abstracts.text().split(" ");
                Element ft = mod.getElementsByClass("ft").first();
                Element content = ft.getElementsByClass("content").first();

                String comment = "";
                if(content != null){
                    comment= content.text();
                }
                System.out.println(name);
                doubanBookList.add(new DoubanBook(burl,name,rate,ratep,comment,babstracts));
            }else{
                continue;
            }
        }

        return doubanBookList;
    }


}
