package com.advantech.homework.controller.apidata;
public class PageModel {
    //private int currentPage = 1;//
    //private int pageSize = 10;//
    //private int totalPage ;//
    private int totalCount = 0;//
    private Object data;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
