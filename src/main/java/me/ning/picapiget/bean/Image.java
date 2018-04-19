package me.ning.picapiget.bean;

import me.ning.picapiget.util.ImageUtil;

import java.util.Date;

public class Image {
    private Integer id;

    private String name;

    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Date create_time;

    public Image() {
    }

    public Image( String url, Date create_time) {
        this.name = ImageUtil.getImageId(url);
        this.url = url;
        this.create_time = create_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", create_time=" + create_time +
                '}';
    }


}
