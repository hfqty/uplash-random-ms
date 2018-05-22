package me.ning.pro.doubanbook.entity;

public class DoubanBook {

    private String url;

    private String name;

    private String rate;

    private String ratePeople;

    private String comment;

    private String img;
    private String[] abstracts;

    public String a(int index){
        return abstracts[index];
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRatePeople() {
        return ratePeople;
    }

    public void setRatePeople(String ratePeople) {
        this.ratePeople = ratePeople;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String[] getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String[] abstracts) {
        this.abstracts = abstracts;
    }

    public DoubanBook() {
    }

    public DoubanBook(String url, String name, String rate, String ratePeople, String comment,  String[] abstracts) {
        this.url = url;
        this.name = name;
        this.rate = rate;
        this.ratePeople = ratePeople;
        this.comment = comment;
        this.abstracts = abstracts;
    }

    public String toString() {
        return "DoubanBook{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                ", ratePeople='" + ratePeople + '\'' +
                ", comment='" + comment + '\'' +
                ", img='" + img + '\'' +
                ", abstracts='" + abstracts + '\'' +
                '}';
    }
}
