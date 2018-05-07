package me.ning.pro.common.vo;


import java.util.List;

public class Page<T> {

    private int pageSize;

    private int pageNum;

    private int total;

    private int pages;

    private boolean hasNext;

    private boolean hasPre;

    private boolean isFirst;

    private boolean isLast;



    private List<T> row;


    public Page() {
    }

    public Page(List<T> row) {
        this.row = row;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRow() {
        return row;
    }

    public void setRow(List<T> row) {
        this.row = row;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", total=" + total +
                ", pages=" + pages +
                ", row=" + row +
                '}';
    }

    public static int pages(int pageSize,int total){
        if(pageSize<=0){
            return 0;
        }
        return total%pageSize>0?total/pageSize+1:total/pageSize;
    }

}
