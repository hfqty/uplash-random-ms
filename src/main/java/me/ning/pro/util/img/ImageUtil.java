package me.ning.pro.util.img;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import me.ning.pro.entity.image.ImageExifInfo;
import me.ning.pro.util.file.FileUtil;
import me.ning.pro.util.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.Collection;

public class ImageUtil {

    private final static  Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    private final static int START_INDEX = 28;

    private final static int END_INDEX = 60;

    private final static BigDecimal K = BigDecimal.valueOf(1024);

    @Value("${img.save.path}")
    private String imageSavePath;


    private final static int IS_FIRST =0;

    private final static int NOT_FIRST = 1;

    private final static int FIRST = IS_FIRST;

    private static String bigImgUrl(String url) {
        return url.substring(0,END_INDEX);
    }

    public static String Id(String url){
        String imageId =  String.valueOf(url.substring(START_INDEX,END_INDEX));
        return imageId;
    }

    public  static String name(String url){
        String fileName = ImageUtil.Id(url)+ ".jpg";
        return  fileName;
    }
    public static float imgSize(String url){
        HttpURLConnection connection = RequestUtil.connection(url);
        int contentLength= connection.getContentLength();
        if(contentLength <= 0)
            return 0;
        BigDecimal fileSize = BigDecimal.valueOf(contentLength).divide(ImageUtil.K);
        return fileSize.floatValue();

    }


    public static void toServer(String url) throws IOException {
        HttpURLConnection connection  = RequestUtil.connection(bigImgUrl(url));
        String fullPath = fullPath(url);
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
            BigDecimal count= BigDecimal.valueOf(0.1);
            BigDecimal fileSizeKB = fileSize.divide(BigDecimal.valueOf(1024),3,BigDecimal.ROUND_HALF_UP);
            if(fileSizeKB.compareTo(BigDecimal.valueOf(1024))>=0){
                BigDecimal fileSizeMB  = fileSizeKB.divide(BigDecimal.valueOf(1024),3,BigDecimal.ROUND_HALF_UP);
                logger.info("图片大小："+fileSizeMB+"MB");
            }
            else
            logger.info("图片大小："+fileSizeKB+"KB");

            // 读取到的数据长度
            int len;
            // 输出的文件流
            // 开始读取
            while ((len = is.read(bs)) != -1) {

                downloaded = BigDecimal.valueOf(len);
                writed = writed.add(downloaded);
                downloaded = writed.divide(fileSize,10,BigDecimal.ROUND_HALF_UP);
                if(downloaded.compareTo(count)>=0){
                    showDownloadPro(downloaded);
                    count=  count.add(BigDecimal.valueOf(0.1));
//                logger.info("下载进度："+downloaded+",("+writed+"/"+fileSize+")");
                }
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



    public static String fullPath(String url){
        ImageUtil imageUtil = new ImageUtil();
        String basePath = imageUtil.imageSavePath;
        if(basePath == null){
            basePath  =  "C:\\dev\\UnsplashWallpaper\\";
            File file = new File(basePath);
            if(!file.exists()){
                file.mkdir();
                logger.info("目录不存在，重新创建");
            }
        }

        String imageName = name(url);
        return basePath+imageName;
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


    public static void showDownloadPro(BigDecimal downloaded){
        System.out.print("进度：");
        showPro(downloaded);
    }

    public static void showPro(BigDecimal count){
        if(count == null ||count.compareTo(BigDecimal.ZERO)<=0){
            for(int i=0;i<100;i++){
                System.out.print("-");
            }
        }else{
            count = count.multiply(BigDecimal.valueOf(100));
            int num = count.intValue();
            int num2 = 100 -num;
            System.out.print("<"+num+"%>");
            for(int i = 0; i< num;i++){
                System.out.print(">");
            }
            for(int j = 0;j<num2;j++){
                System.out.print("-");
            }
            System.out.println();
        }
    }
}
