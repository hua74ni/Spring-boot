package com.landicorp.marketing.common;

import java.util.List;

/**
 * Created by jiangjt on 2017/9/23.
 */
public class TableData {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 总页数
     */
    private Long totalCount;
    /**
     * 记录
     */
    private List<?> dataList;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }
}
