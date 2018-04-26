package me.ning.picapiget.image.service.impl;

import me.ning.picapiget.image.bean.Image;
import me.ning.picapiget.image.dao.ImageDao;
import me.ning.picapiget.image.service.ImageService;
import me.ning.picapiget.image.util.img.ImageUtil;
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
    public boolean addImage(String url) {
        Integer result = imageDao.addImage(new Image(url));
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer hadImage(String url) {
        List<Image> images = imageDao.hadImage(ImageUtil.Id(url));
        if (images == null || images.isEmpty())
            return 0;
        return images.size();
    }



}
