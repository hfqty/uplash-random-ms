package me.ning.picapiget.image.dao;

import me.ning.picapiget.image.bean.Today;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface TodayDao {


    @Insert("insert into img_today (view_date,count) values (#{today},1)")
    int insert(String today);


    @Update("update img_today set count = count+1 where view_date = #{today}")
    int increase(String today);


    @Select("select * from img_today where view_date = #{today}")
    Today selectToday(String today);


}
