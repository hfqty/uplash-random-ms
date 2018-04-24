package me.ning.picapiget.image.util.img;

import me.ning.picapiget.image.util.file.FileUtil;
import me.ning.picapiget.image.util.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;

public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    private final static int START_INDEX = 28;

    private final static int END_INDEX = 47;

    public static String Id(String url){
        String imageId =  String.valueOf(url.substring(START_INDEX,END_INDEX));
        logger.info("图片名称："+imageId);
        return imageId;
    }

    public static void toServer(String url) throws IOException {
        HttpURLConnection connection  = RequestUtil.connection(url);
        String fullPath = fullPath(url);
        logger.info("保存图片：保存到服务器,路径："+fullPath);
        // 输入流
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1K的数据缓冲
            byte[] bs = new byte[4096];
          is = connection.getInputStream();
          os = new FileOutputStream(FileUtil.createFile(fullPath));
            //总大小
            int contentLength = connection.getContentLength();

            BigDecimal writed = BigDecimal.ZERO;
            BigDecimal fileSize = BigDecimal.valueOf(contentLength);
            BigDecimal downloaded = BigDecimal.ZERO;
            // 读取到的数据长度
            int len;
            // 输出的文件流
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                downloaded = BigDecimal.valueOf(len);
                writed = writed.add(downloaded);
                downloaded = writed.divide(fileSize,10,BigDecimal.ROUND_HALF_UP);
                logger.info("下载进度："+downloaded+",("+writed+"/"+fileSize+")");
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
        } catch (IOException e) {
            throw new IOException();
        }finally {
            if(is != null)
            is.close();
            if(os != null)
            os.close();
        }
    }

    public static void deleteFromServer(File file) {
        if(file.delete()){
           logger.info("删除文件："+file.getName() + " 文件已被删除！");
        }else{
            logger.info("删除文件："+file.getName() + " 删除失败！");
        }

    }

    public  static String name(String url){
        String imageId = ImageUtil.Id(url);
        String fileName = imageId + ".jpg";
        logger.info("文件名称："+fileName);
        return  fileName;
    }

    public static String fullPath(String url){
        String imageName = ImageUtil.name(url);
        return "C:\\Users\\yu\\Pictures\\UnsplashWallpaper\\"+imageName;
    }

    public static boolean checkAndDownload(String url){
        logger.info("检查本地：并下载图片");
        String fullPath = ImageUtil.fullPath(url);
        try {
            if(FileUtil.checkExist(fullPath) == null ){
                toServer(url);
                logger.info("保存图片：保存到服务器成功");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
