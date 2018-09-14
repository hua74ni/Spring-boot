package com.landicorp.marketing.service;

import com.landicorp.marketing.entities.ActUserGift;
import com.landicorp.marketing.entities.SpecificProgram;

/**
 * Created by huangdonghua on 2018/4/14.
 */
public interface ActUserGiftService {

    public Boolean getActLimitedByAct(String openId, SpecificProgram specificProgram);

    public void insertActUserGift(ActUserGift actUserGift);

    public Integer getActTotalNum(ActUserGift actUserGift);

}
