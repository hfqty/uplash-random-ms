package me.ning.picapiget.bean;

import me.ning.picapiget.util.ImageIdUtil;

import java.util.Date;

public class Image {
    private String id;

    private String url;

    private String path;

    private Date create_time;

    private Date last_view;

    public Image() {
    }

    public Image( String url, Date create_time, Date last_view) {
        this.id = ImageIdUtil.getImageId(url);
        this.url = url;
        this.create_time = create_time;
        this.last_view = last_view;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_view() {
        return last_view;
    }

    public void setLast_view(Date last_view) {
        this.last_view = last_view;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", create_time=" + create_time +
                ", last_view=" + last_view +
                '}';
    }
}
