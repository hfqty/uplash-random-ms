package me.ning.pro.doubanbook.entity;

public class Creater {
    private String title;
    private String nickname;
    private String img;
    private String homepage;
    private String time;
    private String introduction;

    public Creater(){

    }

    public Creater(String title, String nickname, String img, String homepage, String time, String introduction) {
        this.title = title;
        this.nickname = nickname;
        this.img = img;
        this.homepage = homepage;
        this.time = time;
        this.introduction = introduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String toString() {
        return "Creater{" +
                "title='" + title + '\'' +
                ", nickname='" + nickname + '\'' +
                ", img='" + img + '\'' +
                ", homepage='" + homepage + '\'' +
                ", time='" + time + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
