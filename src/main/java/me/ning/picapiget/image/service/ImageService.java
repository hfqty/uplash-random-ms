package me.ning.picapiget.image.service;

import me.ning.picapiget.image.bean.Image;

import java.util.List;

public interface ImageService {


    List<Image> allImages();

    boolean addImage(String url) ;

    boolean hadImage(String url);





}
