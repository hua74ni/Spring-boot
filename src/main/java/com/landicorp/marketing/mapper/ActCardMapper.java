package com.landicorp.marketing.mapper;

import com.landicorp.marketing.entities.ActCard;
import com.landicorp.marketing.TkMapper.CustomMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangdonghua on 08/04/2018.
 */
public interface ActCardMapper extends CustomMapper<ActCard> {

    public void delActCards(@Param("deleteIds") List<String> deleteIds);

}