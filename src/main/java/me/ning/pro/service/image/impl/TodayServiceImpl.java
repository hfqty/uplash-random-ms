package me.ning.pro.service.image.impl;

import me.ning.pro.entity.image.Today;
import me.ning.pro.dao.image.TodayDao;
import me.ning.pro.service.image.TodayService;
import me.ning.pro.util.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
