package com.landicorp.marketing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.SpecificGift;
import com.landicorp.marketing.entities.SpecificProgram;
import com.landicorp.marketing.mapper.SpecificGiftMapper;
import com.landicorp.marketing.mapper.SpecificProgramMapper;
import com.landicorp.marketing.service.SpecificProgramService;
import com.landicorp.marketing.utill.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * Created by huangdonghua on 11/04/2018.
 */
@Service("specificProgramService")
@Transactional
public class SpecificProgramServiceImpl implements SpecificProgramService {
    private static final Logger LOG = LoggerFactory.getLogger(SpecificProgramServiceImpl.class);

    @Autowired
    private SpecificProgramMapper specificProgramMapper;

    @Autowired
    private SpecificGiftMapper specificGiftMapper;


    @Override
    public void insertSpecificProgram(List<SpecificProgram> specificProgramList, String actId, String actIdType) {

        for(int i = 0;i < specificProgramList.size();i++){
            SpecificProgram specificProgram = specificProgramList.get(i);
            specificProgram.setProgramId(UUID.randomUUID().toString());
            specificProgram.setActId(actId);
            specificProgram.setActIdType(actIdType);
            List<SpecificGift> specificGiftList = specificProgram.getSpecificGiftList();
            for(int j = 0;j < specificGiftList.size();j++){
                SpecificGift specificGift = specificGiftList.get(j);
                specificGift.setGiftId(UUID.randomUUID().toString());
                specificGift.setProgramId(specificProgram.getProgramId());
                specificGiftMapper.insert(specificGift);
            }
            specificProgramMapper.insert(specificProgram);
        }

    }

    @Override
    public TableData querySpecificProgram(String actId, String actIdType, int currentPage, int pageSize) {

        TableData tableData = new TableData();

        Example example = new Example(SpecificProgram.class);
        example.createCriteria().andEqualTo("actId",actId).andEqualTo("actIdType",actIdType);
        PageHelper.startPage(currentPage,pageSize);
        List<SpecificProgram> specificProgramList = specificProgramMapper.selectByExample(example);
        PageInfo<SpecificProgram> specificProgramPageInfo = new PageInfo<>(specificProgramList);

        List<SpecificProgram> specificProgramList1 =  specificProgramPageInfo.getList();
        for(int i = 0;i < specificProgramList1.size();i++){
            SpecificProgram specificProgram = specificProgramList1.get(i);
            Example example1 = new Example(SpecificGift.class);
            example1.createCriteria().andEqualTo("programId",specificProgram.getProgramId());
            List<SpecificGift> specificGiftList = specificGiftMapper.selectByExample(example1);

            specificProgram.setSpecificGiftList(specificGiftList);
            specificProgramList1.set(i,specificProgram);
        }

        tableData.setCurrentPage(currentPage);
        tableData.setDataList(specificProgramList1);
        tableData.setTotalCount(specificProgramPageInfo.getTotal());

        return tableData;
    }

    @Override
    public void delSpecificProgramsByCardId(String actId, String actIdType) {

        Example example = new Example(SpecificProgram.class);
        example.createCriteria().andEqualTo("actId",actId).andEqualTo("actIdType",actIdType);
        List<SpecificProgram> specificProgramList = specificProgramMapper.selectByExample(example);

        for(int i = 0;i < specificProgramList.size();i++){
            Example example1 = new Example(SpecificGift.class);
            example1.createCriteria().andEqualTo("programId",specificProgramList.get(i).getProgramId());
            specificGiftMapper.deleteByExample(example1);
            specificProgramMapper.delete(specificProgramList.get(i));
        }

    }

    @Override
    public List<SpecificProgram> getSpecificProgramList(String actId, String actIdType) {

        Example example = new Example(SpecificProgram.class);
        example.createCriteria().andEqualTo("actId",actId).andEqualTo("actIdType",actIdType);
        List<SpecificProgram> specificProgramList = specificProgramMapper.selectByExample(example);

        for(int i = 0;i < specificProgramList.size();i++){
            Example example1 = new Example(SpecificGift.class);
            example1.createCriteria().andEqualTo("programId",specificProgramList.get(i).getProgramId());
            List<SpecificGift> specificGiftList = specificGiftMapper.selectByExample(example1);

            SpecificProgram specificProgram = specificProgramList.get(i);
            specificProgram.setSpecificGiftList(specificGiftList);
            specificProgramList.set(i,specificProgram);
        }

        return specificProgramList;
    }
}
