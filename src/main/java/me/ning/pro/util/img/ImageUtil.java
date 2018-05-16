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



    public static String Id(String url){
        String imageId =  String.valueOf(url.substring(START_INDEX,END_INDEX));
        logger.info("图片名称："+imageId);
        return imageId;
    }



    public static float imgSize(String url){
    HttpURLConnection connection = RequestUtil.connection(url);
    int contentLength= connection.getContentLength();
    if(contentLength <= 0)
        return 0;
    BigDecimal fileSize = BigDecimal.valueOf(contentLength).divide(K);
    return fileSize.floatValue();

    }


    public static ImageExifInfo imgInfo(String url){
        ImageExifInfo imageExifInfo = new ImageExifInfo();
        File img_File = new File(fullPath(url));
        try {
            Metadata img_Metadata = JpegMetadataReader.readMetadata(img_File);
            //Directory img_exif = img_Metadata.getDirectory(ExifIFD0Directory.class);
//            if(img_exif == null)
//                return imageExifInfo;
            Iterable<Directory> img_exifs = img_Metadata.getDirectories();
            if(img_exifs == null)
                return imageExifInfo;
            for(Directory img_exif : img_exifs) {
                Collection<Tag> img_tags = img_exif.getTags();
                for (Tag img_tag : img_tags) {
                    if (img_tag.getTagName().contains("Unknown")) {
                        imageExifInfo.setUnknown(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("Make")) {
                        imageExifInfo.setMake(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("Model")) {
                        imageExifInfo.setModel(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("Image Width")) {
                        imageExifInfo.setWidth(Float.parseFloat(img_tag.getDescription().substring(0,4)));
                    } else if (img_tag.getTagName().contains("Image Height")) {
                        imageExifInfo.setHeight(Float.parseFloat(img_tag.getDescription().substring(0,4)));
                    } else if (img_tag.getTagName().contains("Resolution Unit")) {
                        imageExifInfo.setResolutionUnit(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("Software")) {
                        imageExifInfo.setSoftware(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("Date/Time")) {
                        imageExifInfo.setDateTime(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("YCbCr Positioning")) {
                        imageExifInfo.setCbcrPositioning(img_tag.getDescription());
                    } else if (img_tag.getTagName().contains("Orientation")) {
                        imageExifInfo.setOrientation(img_tag.getDescription());
                    }
                }
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info(imageExifInfo.toString());
        return imageExifInfo;
    }


    public static float width (String url){
       float width =  imgInfo(url).getWidth();
       if(width == 0){
           return 1920;
       }
       return width;
    }

    public static float height(String url){
        float height = imgInfo(url).getHeight();
        if(height == 0)
            return 1080;
        return height;
    }

    public static void toServer(String url) throws IOException {
        HttpURLConnection connection  = RequestUtil.connection(bigImgUrl(url));
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
            BigDecimal count= BigDecimal.valueOf(0.1);
            // 读取到的数据长度
            int len;
            // 输出的文件流
            // 开始读取
            while ((len = is.read(bs)) != -1) {

                downloaded = BigDecimal.valueOf(len);
                writed = writed.add(downloaded);
                downloaded = writed.divide(fileSize,10,BigDecimal.ROUND_HALF_UP);
                if(downloaded.compareTo(count)>0){
                logger.info("下载进度："+downloaded+",("+writed+"/"+fileSize+")");
                }
                os.write(bs, 0, len);
                count=  count.add(BigDecimal.valueOf(0.1));

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

    private static String bigImgUrl(String url) {
        return url.substring(0,END_INDEX);
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
        ImageUtil imageUtil = new ImageUtil();
        String basePath = imageUtil.imageSavePath;
        logger.info("配置路径："+basePath);
        if(basePath == null){
            basePath  =  "C:\\dev\\UnsplashWallpaper\\";

            File file = new File(basePath);
            if(!file.exists()){
                file.mkdir();
                logger.info("目录不存在，重新创建");
            }
            logger.info("默认路径："+basePath);
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
}
