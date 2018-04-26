package me.ning.picapiget.image.service.impl;

import me.ning.picapiget.image.bean.Today;
import me.ning.picapiget.image.dao.TodayDao;
import me.ning.picapiget.image.service.TodayService;
import me.ning.picapiget.image.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TodayServiceImpl implements TodayService {


    @Autowired
    private TodayDao todayDao;



    @Override
    public Today selectToday() {
        return todayDao.selectToday(DateUtil.today());
    }

    @Override
    public void incrase() {
        Today today = todayDao.selectToday(DateUtil.today());
        if(today == null){
            todayDao.insert(DateUtil.today());
        }else{
            todayDao.increase(DateUtil.today());
        }
    }


}
