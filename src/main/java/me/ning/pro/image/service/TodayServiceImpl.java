package me.ning.pro.image.service;

import me.ning.pro.image.entity.Today;
import me.ning.pro.image.dao.TodayDao;
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
