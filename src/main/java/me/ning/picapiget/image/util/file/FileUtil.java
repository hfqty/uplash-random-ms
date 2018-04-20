package me.ning.picapiget.image.util.file;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

    public  static void  downloadFile(HttpServletResponse response, File file) {
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);

            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
