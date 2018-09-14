package com.landicorp.marketing.service;

import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.SpecificProgram;

import java.util.List;

/**
 * Created by huangdonghua on 11/04/2018.
 */
public interface SpecificProgramService {

    public void insertSpecificProgram(List<SpecificProgram> specificProgramList, String actId, String actIdType);

    public TableData querySpecificProgram(String actId, String actIdType, int currentPage, int pageSize);

    public void delSpecificProgramsByCardId(String actId, String actIdType);

    public List<SpecificProgram> getSpecificProgramList(String actId, String actIdType);

}
