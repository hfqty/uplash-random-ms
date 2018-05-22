package me.ning.pro.doubanbook.parser;

import me.ning.pro.doubanbook.entity.Creater;
import me.ning.pro.doubanbook.entity.DouList;
import me.ning.pro.doubanbook.entity.DoubanBook;
import me.ning.pro.doubanbook.util.URLUtil;
import me.ning.pro.doubanbook.writer.DouListWriter;

import java.io.IOException;
import java.util.List;

public class DouListParser<T> {

    private int id;


    public DouListParser(DouList<T> douList) {
        this.id = douList.getId();
    }

    public  void parse()  {
        String url = URLUtil.url(id);
        Creater creater = CreaterParser.creater(url);
        List<DoubanBook> items = BookParser.items(url);
        try {
            DouListWriter.write(new DouList<DoubanBook>(creater,items));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
