package com.clx.composite.model;

import java.util.List;

public class ExcelInfo<T> {
    private int pages;  //总页数
    private int pageNum;//当前页数
    private int nextPage;//下一页页数
    private int lastPage;//上一页页数
    private boolean hasNextPage;//是否有下一页
    private boolean hasPreviousPage;//是否有上一页
    private List<T> list;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if(pageNum<this.pages&&pageNum>1){
            this.hasNextPage=true;
            this.hasPreviousPage=true;
            this.lastPage=pageNum-1;
            this.nextPage=pageNum+1;
            this.pageNum=pageNum;
        }
        else if(pageNum>=this.pages){
            this.hasNextPage=false;
            this.hasPreviousPage=true;
            this.nextPage=pages;
            this.lastPage=pageNum-1;
            this.pageNum=this.pages;
        }else if(pageNum<=1){
            this.hasNextPage=true;
            this.hasPreviousPage=false;
            this.nextPage=pageNum+1;
            this.lastPage=1;
            this.pageNum=1;
        }
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
