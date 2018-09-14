package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.ActRecharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface ActRechargeMapper extends CustomMapper<ActRecharge> {

    public void delActRecharges(@Param("deleteIds") List<String> deleteIds);

}
