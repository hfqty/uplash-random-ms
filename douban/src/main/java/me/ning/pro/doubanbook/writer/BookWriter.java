package me.ning.pro.doubanbook.writer;

import me.ning.pro.doubanbook.entity.Creater;
import me.ning.pro.doubanbook.entity.DoubanBook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class BookWriter {
    public static void write(Creater creater, List<DoubanBook> items) throws IOException {
        BufferedWriter writer = CreaterWriter.writer(creater);
        writer.newLine();
        writer.write("## 共" + items.size() + "本");
        writeItems(items, writer);
    }

    public static void write(Creater creater, List<DoubanBook> items, int num, int i) throws IOException {
        List<DoubanBook> doubanBooks = DouListWriter.doubanBooks(items, num, i);
        BufferedWriter writer = CreaterWriter.writer(creater, i);
        writer.newLine();
        writer.write("## 共" + doubanBooks.size() + "本");
        writeItems(doubanBooks, writer);
    }

    private static void writeItems(List<DoubanBook> items, BufferedWriter writer) throws IOException {
        int index = 0;
        for (DoubanBook book : items) {
            index = index + 1;
            writer.newLine();
            writer.write("### No." + (index) + " " + book.getName());
            writer.newLine();
            writer.write("> **图书名称**：[" + book.getName() + "](" + book.getUrl() + ")");
            writer.newLine();
            writer.write("> **豆瓣评分**：" + book.getRate() + book.getRatePeople());
            writer.newLine();
            writeAbstract(writer, book);
            writer.newLine();
            writer.write(book.getComment());
        }
        if (writer != null) {
            writer.close();
        }
    }

    private static void writeAbstract(BufferedWriter writer, DoubanBook book) throws IOException {
        if (book.getAbstracts().length > 1) {
            if (book.getAbstracts().length <= 6) {
                writer.write("> **" + book.a(0) + "**" + book.a(1));
                writer.newLine();
                //todo keng
                if (book.getAbstracts().length > 3) {
                    writer.write("> **" + book.a(2) + "**" + book.a(3));
                    writer.newLine();
                    if (book.getAbstracts().length > 5) {
                        writer.write("> **" + book.a(4) + "**" + book.a(5));
                    }
                }
            } else if (book.getAbstracts().length == 7) {
                writer.write("> **" + book.a(0) + "**" + book.a(1) + book.a(2));
                writer.newLine();
                writer.write("> **" + book.a(3) + "**" + book.a(4));
                writer.newLine();
                writer.write("> **" + book.a(5) + "**" + book.a(6));
            } else if (book.getAbstracts().length == 8) {
                writer.write("> **" + book.a(0) + "**" + book.a(1) + book.a(2) + book.a(3));
                writer.newLine();
                writer.write("> **" + book.a(4) + "**" + book.a(5));
                writer.newLine();
                writer.write("> **" + book.a(6) + "**" + book.a(7));
            }
        }

    }
}
