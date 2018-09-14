package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.UserMoneyStream;

/**
 * Created by huangdonghua on 2018/4/14.
 */
public interface UserMoneyStreamMapper extends CustomMapper<UserMoneyStream>{

    public Double getTotalMoney(UserMoneyStream userMoneyStream);

}
