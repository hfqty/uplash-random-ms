package me.ning.picapiget.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import me.ning.picapiget.bean.Image;

import java.util.List;

public interface ImageService {


    List<Image> allImages();

    boolean addImage(Image image) throws MySQLIntegrityConstraintViolationException;

    Integer hadImage(String url);



}
