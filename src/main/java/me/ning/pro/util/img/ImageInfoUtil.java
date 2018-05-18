package me.ning.pro.util.img;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import me.ning.pro.entity.image.ImageExifInfo;
import me.ning.pro.util.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.Collection;

public class ImageInfoUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageInfoUtil.class);



    public static ImageExifInfo imgInfo(String url){
        ImageExifInfo imageExifInfo = new ImageExifInfo();
        File img_File = new File(ImageUtil.fullPath(url));
        try {
            Metadata img_Metadata = JpegMetadataReader.readMetadata(img_File);
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
}
