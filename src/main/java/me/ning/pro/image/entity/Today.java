package me.ning.pro.image.entity;

import java.util.Date;

public class Today {

    private Integer id;

    private Date view_date;

    private Integer count;


    public Today() {
    }

    public Today(Date view_date, Integer count) {
        this.view_date = view_date;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getView_date() {
        return view_date;
    }

    public void setView_date(Date view_date) {
        this.view_date = view_date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Today{" +
                "id=" + id +
                ", view_date=" + view_date +
                ", count=" + count +
                '}';
    }
}
