package me.ning.picapiget.dao;


import me.ning.picapiget.bean.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImageDao{


    @Select("select * from images")
    List<Image> allImages();

    @Insert("insert into images values(#{img.id},#{img.url},#{img.path},#{img.create_time},#{img.last_view})")
    Integer addImage(@Param("img") Image img);


    @Select("select * from images where id = #{id}")
    List<Image> hadImage(String id);


}