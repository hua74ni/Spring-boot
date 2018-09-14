package com.landicorp.marketing.service;

import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActBirthday;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface ActBirthdayService {

    public Integer insertActBirthday(ActBirthday actBirthday);

    public Integer delActBirthdayByIds(List<String> deleteIds);

    public Integer updateCard(ActBirthday actBirthday);

    public TableData queryActBirthdayTable(ActBirthday actBirthday, int currentPage, int pageSize);

    public ActBirthday getActBirthdayById(String birthdayId);

    public TableData queryProgramByBirthdayId(String birthdayId, int currentPage, int pageSize);

    public Integer changeState(ActBirthday actBirthday);

    public List<ActBirthday> queryActCardByValid();
}
