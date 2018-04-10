package me.ning.picapiget.util;

import me.ning.picapiget.bean.Image;
import me.ning.picapiget.util.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;

public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    private final static int START_INDEX = 28;

    private final static int END_INDEX = 60;

    public static String getImageId(String url){
        return String.valueOf(url.substring(START_INDEX,END_INDEX));
    }

    public static void saveToServer(HttpURLConnection connection, String imageName) {
        logger.info("保存图片：保存到服务器");
        // 输入流
        try {
            InputStream is = connection.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(FileUtil.createFile(imageName));
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("保存图片：保存到服务器成功");
    }

    public static void deleteFromServer(File file) {
        if(file.delete()){
           logger.info("删除文件："+file.getName() + " 文件已被删除！");
        }else{
            logger.info("删除文件："+file.getName() + " 删除失败！");
        }

    }

    public  static String getImageName(String url){
        String imageId = ImageUtil.getImageId(url);
        logger.info("图片名称："+imageId);
        return  imageId+".jpg";
    }

    public static String getImageFullPath(String url){
        String imageName = getImageId(url);
        return "D:\\devf\\file server\\images\\"+imageName;
    }

    public static boolean checkAndDownload(HttpURLConnection connection,String fullPath){
        logger.info("完整路径："+fullPath);
        logger.info("开始检查文件是否存在");
        File file = new File(fullPath);
        if(file.exists()&&file.isFile()){
            return true;
        }
        try {
            file = FileUtil.checkExist(fullPath);
            if(file == null ){
                saveToServer(connection, fullPath);
                file = new File(fullPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.exists()&&file.isFile()){
            return true;
        }
        return false;
    }
}
