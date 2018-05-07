package me.ning.pro.service.image;

import me.ning.pro.entity.image.Image;

import java.util.List;

public interface ImageService {


    List<Image> allImages();

    boolean addImage(String url) ;

    boolean hadImage(String url);





}
