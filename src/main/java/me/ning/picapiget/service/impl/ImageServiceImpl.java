package me.ning.picapiget.service.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import me.ning.picapiget.bean.Image;
import me.ning.picapiget.dao.ImageDao;
import me.ning.picapiget.service.ImageService;
import me.ning.picapiget.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public List<Image> allImages() {
        return imageDao.allImages();
    }

    @Override
    public boolean addImage(Image image) throws MySQLIntegrityConstraintViolationException {
        System.out.println("保存图片:保存中-------------");
        Integer result = imageDao.addImage(image);
        if (result > 0) {
            System.out.println("保存图片:保存成功-------------");
            return true;
        } else {
            System.out.println("保存图片:保存失败-------------");
            return false;
        }
    }

    @Override
    public Integer hadImage(String url) {
        List<Image> images = imageDao.hadImage(ImageUtil.getImageId(url));
        if (images == null || images.isEmpty())
            return 0;
        return images.size();
    }
}
