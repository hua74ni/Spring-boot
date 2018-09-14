package com.landicorp.marketing.service;


import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.Welfare;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
public interface WelfareService {

    public TableData queryWelfareTable(Welfare welfare, int currentPage, int pageSize);

    public Integer insertWelfare(Welfare welfare);

    public Integer updateWelfare(Welfare welfare);

    public Integer delWelfare(Welfare welfare);

    public List<Welfare> queryWelfareList(Welfare welfare);

}
