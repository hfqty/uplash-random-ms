package me.ning.picapiget.util.file;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**

     * 判断文件及目录是否存在，若不存在则创建文件及目录

     * @param filepath

     * @return

     * @throws Exception

     */

    public static File checkExist(String filepath) throws Exception{

        File file=new File(filepath);
        if (file.exists() && file.isFile()) {//判断文件目录的存在
            return file;
        }
        return null;

    }

    public static File createFile(String filePath) throws IOException {
        File file=new File(filePath);
        file.createNewFile();

        return file;

    }


}
