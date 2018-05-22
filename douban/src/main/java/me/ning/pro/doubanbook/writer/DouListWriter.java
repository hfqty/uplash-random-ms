package me.ning.pro.doubanbook.writer;

import me.ning.pro.doubanbook.entity.DouList;
import me.ning.pro.doubanbook.entity.DoubanBook;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DouListWriter {
    public static void write(DouList<DoubanBook> douList) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("写入开始：" + new Date());
        int num = fileNum(douList.getItems());
        if (num > 1) {
            for (int i = 0; i < num; i++) {
                BookWriter.write(douList.getCreater(), douList.getItems(), num, i);
            }
        } else {
            BookWriter.write(douList.getCreater(),douList.getItems());
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("写入结束：" + new Date() + "\n耗时：" + end + "ms");
    }

    public static List<DoubanBook> doubanBooks(List<DoubanBook> items, int num, int i) {
        List<DoubanBook> doubanBooks = null;
        if ((i + 1) < num) {
            doubanBooks = items.subList(i*500,(1+i)*500);
        } else if((i+1) == num){
            if (num * 500 > items.size()) {
                doubanBooks = items.subList(i * 500, items.size());
            }
        }
        return doubanBooks;
    }

    private static int fileNum(List<DoubanBook> items) {
        if (items.size() < 500) {
            return 1;
        }
        if (items.size() % 500 > 1) {
            return items.size() / 500 + 1;
        }
        return items.size() / 500;
    }
}
