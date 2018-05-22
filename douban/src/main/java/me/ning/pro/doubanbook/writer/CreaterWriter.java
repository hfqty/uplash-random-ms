package me.ning.pro.doubanbook.writer;

import me.ning.pro.doubanbook.entity.Creater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreaterWriter {
    static String basepath = "C:\\all\\ilife\\book\\doulist\\";
    public static BufferedWriter writer(Creater creater) throws IOException {
        BufferedWriter writer = createFileWriter(creater);
        writer.write("#" + creater.getTitle());
        writer.newLine();
        writer.write(creater.getTime());
        writer.newLine();
        writer.write("![" + creater.getNickname() + "](" + creater.getImg() + ")");
        writer.write("   [" + creater.getNickname() + "](" + creater.getHomepage() + ")");
        writer.newLine();
        writer.newLine();
        writeIntro(creater, writer);
        return writer;
    }

    public static BufferedWriter writer(Creater creater, int i) throws IOException {
        BufferedWriter writer = createFileWriter(creater, i + 1);
        writer.write("#" + creater.getTitle());
        writer.newLine();
        writer.write(creater.getTime());
        writer.newLine();
        writer.write("![" + creater.getNickname() + "](" + creater.getImg() + ")");
        writer.write("   [" + creater.getNickname() + "](" + creater.getHomepage() + ")");
        writer.newLine();
        writer.newLine();
        writeIntro(creater, writer);
        return writer;
    }

    private static void writeIntro(Creater creater, BufferedWriter writer) throws IOException {
        if (creater.getIntroduction() != null)
            writer.write(creater.getIntroduction());
    }

    private static BufferedWriter createFileWriter(Creater creater, int i) throws IOException {
        FileWriter fileWriter = createFile(creater, i);
        return new BufferedWriter(fileWriter);
    }

    private static BufferedWriter createFileWriter(Creater creater) throws IOException {
        FileWriter fileWriter = createFile(creater);
        return new BufferedWriter(fileWriter);
    }

    private static FileWriter createFile(Creater creater, int i) throws IOException {
        String filePath =  basepath+ creater.getTitle() + "by" + creater.getNickname() + "(" + i + ")"+".md";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return new FileWriter(file);
    }

    private static FileWriter createFile(Creater creater) throws IOException {
        String filePath = basepath + creater.getTitle() + "by" + creater.getNickname() + ".md";
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return new FileWriter(file);
    }
}
