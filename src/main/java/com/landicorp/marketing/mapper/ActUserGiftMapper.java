package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.ActUserGift;

/**
 * Created by huangdonghua on 2018/4/14.
 */
public interface ActUserGiftMapper extends CustomMapper<ActUserGift> {

    public Integer getActTotalNum(ActUserGift actUserGift);

}

