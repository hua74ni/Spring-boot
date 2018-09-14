package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.ActConsume;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface ActConsumeMapper extends CustomMapper<ActConsume> {

    public void delActConsumes(@Param("deleteIds") List<String> deleteIds);

}
