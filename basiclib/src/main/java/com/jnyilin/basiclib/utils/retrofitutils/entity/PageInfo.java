package com.jnyilin.basiclib.utils.retrofitutils.entity;

/**
 * @author HRR
 * @datetime 2017/11/29
 * @describe 分页信息实体类，带有分页信息的接口会将分页信息解析到该实体类
 * @modifyRecord
 */

public class PageInfo {

    /**
     * page : 1
     * pageSize : 20
     * count : 4
     * firstRow : 0
     * totalRows : 4
     * totalPages : 1
     * isMore : 0
     */

    private int page;
    private int pageSize;
    private int count;
    private int firstRow;
    private String totalRows;
    private int totalPages;
    private int isMore;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getIsMore() {
        return isMore;
    }

    public void setIsMore(int isMore) {
        this.isMore = isMore;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", count=" + count +
                ", firstRow=" + firstRow +
                ", totalRows='" + totalRows + '\'' +
                ", totalPages=" + totalPages +
                ", isMore=" + isMore +
                '}';
    }
}
