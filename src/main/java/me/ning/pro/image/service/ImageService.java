package me.ning.pro.image.service;

import me.ning.pro.image.entity.Image;

import java.util.List;

public interface ImageService {


    List<Image> allImages();

    boolean addImage(String url) ;

    boolean hadImage(String url);





}
