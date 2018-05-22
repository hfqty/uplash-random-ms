package book.name;

import me.ning.pro.util.datetime.DateTimeUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookName {

    private static List<String> booknames = new ArrayList<>();
   public static void main(String []args) throws IOException {
       File bookLib = new File("C:\\Users\\Administrator\\Documents\\FreeN");
       File booknameDotMD = new File("C:\\all\\inote\\to-read-all.md");
       FileWriter booknameWriter = new FileWriter("C:\\all\\inote\\to-read-all.md");

       BookName bookName = new BookName();
       bookName.fileNameToList(bookLib);
       bookName.createMD(booknameDotMD);
       bookName.write2MD(booknameWriter);
    }

    public void createMD(File booknameDotMD) throws IOException {
        if (booknameDotMD.exists()) {
                booknameDotMD.delete();
                booknameDotMD.createNewFile();
        }else{
                booknameDotMD.createNewFile();
        }
    }

    void write2MD(FileWriter booknameWriter) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(booknameWriter);
        System.out.println(DateTimeUtil.nowStr()+"开始");
        long start = System.currentTimeMillis();
        int index = 1;
        for(String name : booknames){
            if(name.endsWith("pdf")||name.endsWith("mobi")||name.endsWith("epub")) {
                name = name.substring(0,name.indexOf("."));
                bufferedWriter.write(index+". " + name);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                index ++;
            }else{
                continue;
            }
        }
        if(bufferedWriter != null){
            bufferedWriter.close();
        }
        System.out.println(DateTimeUtil.nowStr()+"结束");
        long time = System.currentTimeMillis() - start;
        System.out.println("用时："+time + " ms");
    }


    public  void fileNameToList(File file){
        File[] files= file.listFiles();
        for (File f: files) {
            if(f.exists() &&f.isDirectory()){
                fileNameToList(f);
            }else{
                booknames.add(f.getName());
            }
        }

    }
}
