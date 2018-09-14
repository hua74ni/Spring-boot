package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.ActBirthday;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface ActBirthdayMapper extends CustomMapper<ActBirthday> {

    public void delActBirthdays(@Param("deleteIds") List<String> deleteIds);

}
