package com.landicorp.marketing.service;

import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActConsume;
import com.landicorp.marketing.entities.SpecificProgram;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface ActConsumeService {

    public Integer insertActConsume(ActConsume actConsume);

    public Integer delActConsumeByIds(List<String> deleteIds);

    public Integer updateCard(ActConsume actConsume);

    public TableData queryActConsumeTable(ActConsume actConsume, int currentPage, int pageSize);

    public ActConsume getActConsumeById(String consumeId);

    public TableData queryProgramByConsumeId(String consumeId, int currentPage, int pageSize);

    public Integer changeState(ActConsume actConsume);

    public List<ActConsume> queryActConsumeByValid(String storeId);
    
}
