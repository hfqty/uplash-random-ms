package me.ning.picapiget.image.bean;

import me.ning.picapiget.image.util.datetime.DateTimeUtil;
import me.ning.picapiget.image.util.img.ImageUtil;

import java.util.Date;

public class Image {
    private Integer id;

    private String name;

    private String url;

    private float width;

    private float height;


    private float file_size;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Date create_time;

    public Image() {
        this.create_time = DateTimeUtil.now();
    }


    public Image(String url){
        ImageExifInfo imageExifInfo = ImageUtil.imgInfo(url);
        this.create_time = DateTimeUtil.now();
        this.name = ImageUtil.Id(url);
        this.url = url;
        this.file_size  = ImageUtil.imgSize(url);
        this.width = imageExifInfo.getWidth();
        this.height = imageExifInfo.getHeight();
    }

    public Image( String url, Date create_time) {
        this.name = ImageUtil.Id(url);
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


    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public float getFile_size() {
        return file_size;
    }

    public void setFile_size(float file_size) {
        this.file_size = file_size;
    }
}
