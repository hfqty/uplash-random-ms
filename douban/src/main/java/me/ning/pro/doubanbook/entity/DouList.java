package me.ning.pro.doubanbook.entity;

import me.ning.pro.doubanbook.DouListToMarkDown;

import java.io.BufferedWriter;
import java.util.List;

public class DouList<T> {
    private int id;

    private Creater creater;

    private List<T> items;

    public Creater getCreater() {
        return creater;
    }

    public void setCreater(Creater creater) {
        this.creater = creater;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DouList(int id, Creater creater, List<T> items) {
        this.id = id;
        this.creater = creater;
        this.items = items;
    }

    public DouList(Creater creater, List<T> items) {
        this.creater = creater;
        this.items = items;
    }

    public DouList(int id) {
        this.id = id;
    }

    public DouList() {
    }

    public DouList<T> id(int id){
        this.id = id;
        return this;
    }
}
