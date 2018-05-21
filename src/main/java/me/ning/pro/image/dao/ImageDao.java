package me.ning.pro.image.dao;


import me.ning.pro.image.entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImageDao{


    @Select("select * from images order by create_time desc")
    List<Image> allImages();

    @Insert("insert into images(name,url,width,height,file_size,create_time) values(#{image.name},#{image.url},#{image.width},#{image.height},#{image.file_size},#{image.create_time})")
    Integer addImage(@Param("image") Image img);


    @Select("select * from images where name = #{name}")
    List<Image> hadImage(String name);

    @Delete("delete from images where id  = #{id}")
    void deleteImageById(String id);





}
