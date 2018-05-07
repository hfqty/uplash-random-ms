package me.ning.pro.entity.book;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Book  {

    private Integer bookId;

    private String name;

    private String author;


    private String press;

    private Date publicationDate;

    private Integer pagesNum;

    private BigDecimal price;

    private Long ISBN;

    private Date createTime;

    private Date updateTime;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(Integer pagesNum) {
        this.pagesNum = pagesNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Book(){

    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", publicationDate=" + publicationDate +
                ", pagesNum=" + pagesNum +
                ", price=" + price +
                ", ISBN=" + ISBN +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
