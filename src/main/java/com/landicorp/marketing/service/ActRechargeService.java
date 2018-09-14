package com.landicorp.marketing.service;

import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActRecharge;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface ActRechargeService {

    public Integer insertActRecharge(ActRecharge actRecharge);

    public Integer delActRechargeByIds(List<String> deleteIds);

    public Integer updateCard(ActRecharge actRecharge);

    public TableData queryActRechargeTable(ActRecharge actRecharge, int currentPage, int pageSize);

    public ActRecharge getActRechargeById(String rechargeId);

    public TableData queryProgramByRechargeId(String rechargeId, int currentPage, int pageSize);

    public Integer changeState(ActRecharge actRecharge);

    public List<ActRecharge> queryActRechargeByValid(String storeId);
    
}
