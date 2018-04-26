package me.ning.picapiget.image.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import me.ning.picapiget.image.bean.Image;

import java.util.List;

public interface ImageService {


    List<Image> allImages();

    boolean addImage(Image image) ;

    Integer hadImage(String url);





}
