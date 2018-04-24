package me.ning.picapiget.image.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;

public class FileUtil {

    private static  final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**

     * 判断文件及目录是否存在，若不存在则创建文件及目录

     * @param filepath

     * @return

     * @throws Exception

     */

    public static File checkExist(String filepath) throws Exception{

        File file=new File(filepath);
        if (file.exists() && file.isFile()) {//判断文件目录的存在
            logger.info("检查文件：文件已存在");
            return file;
        }
        logger.info("检查文件：文件不存在");
        return null;

    }

    public static File createFile(String filePath) throws IOException {
        if(filePath.contains("?")){
            filePath.replace("?","0");
        }
        File file=new File(filePath);
        file.createNewFile();
        return file;

    }

    public  static void  downloadFile(HttpServletResponse response, File file) {
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            long fileLength = file.length();

            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int len;
            BigDecimal writed = BigDecimal.ZERO;
            BigDecimal fileSize = BigDecimal.valueOf(fileLength);
            BigDecimal downloaded = BigDecimal.ZERO;
            while ((len = bis.read(buffer)) != -1) {
                downloaded = BigDecimal.valueOf(len);
                writed = writed.add(downloaded);
                downloaded = writed.divide(fileSize,10,BigDecimal.ROUND_HALF_UP);
                logger.info("下载进度："+downloaded+",("+writed+"/"+fileSize+")");
                os.write(buffer, 0, len);
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


    public static boolean checkExist(File file) {
        return file.exists()&&file.isFile();

    }
}
