package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActBirthday;
import com.landicorp.marketing.entities.BirthdayProgram;
import com.landicorp.marketing.mapper.ActBirthdayMapper;
import com.landicorp.marketing.mapper.BirthdayProgramMapper;
import com.landicorp.marketing.service.ActBirthdayService;
import com.landicorp.marketing.utill.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * Created by huangdonghua on 11/04/2018.
 */
@Service("actBirthdayService")
@Transactional
public class ActBirthdayServiceImpl implements ActBirthdayService {
    private static final Logger LOG = LoggerFactory.getLogger(ActBirthdayServiceImpl.class);

    @Autowired
    private ActBirthdayMapper actBirthdayMapper;

    @Autowired
    private BirthdayProgramMapper birthdayProgramMapper;


    @Override
    public Integer insertActBirthday(ActBirthday actBirthday) {
        LOG.info("新增生日活动 actBirthday=" + JSON.toJSONString(actBirthday));

        try{

            String birthdayId = UUID.randomUUID().toString();
            actBirthday.setBirthdayId(birthdayId);
            actBirthday.setState("unpublished");
            if(actBirthday.getIsNotice().equals("0")){
                actBirthday.setNoticeStartDatetime(null);
                actBirthday.setNoticeEndDatetime(null);
            }

            actBirthdayMapper.insertSelective(actBirthday);
            List<BirthdayProgram> birthdayProgramList = actBirthday.getBirthdayProgramList();
            for(int i = 0;i < birthdayProgramList.size();i++){
                BirthdayProgram birthdayProgram = birthdayProgramList.get(i);
                birthdayProgram.setProgramId(UUID.randomUUID().toString());
                birthdayProgram.setBirthdayId(birthdayId);
                if(StringUtils.isNullOrBlank(birthdayProgram.getGiftAmount())){
                    birthdayProgram.setGiftAmount(null);
                }
                birthdayProgramMapper.insertSelective(birthdayProgram);
            }

            return 1;
        }catch (Exception e){
            LOG.error("新增生日活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer delActBirthdayByIds(List<String> deleteIds) {
        LOG.info("删除生日活动 birthdayId=" + JSON.toJSONString(deleteIds));

        try{
            for(int i = 0;i < deleteIds.size();i++){
                Example example = new Example(BirthdayProgram.class);
                example.createCriteria().andEqualTo("birthdayId",deleteIds.get(i));
                birthdayProgramMapper.deleteByExample(example);
            }
            actBirthdayMapper.delActBirthdays(deleteIds);
            return 1;
        }catch (Exception e){
            LOG.error("删除生日活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer updateCard(ActBirthday actBirthday) {
        return null;
    }

    @Override
    public TableData queryActBirthdayTable(ActBirthday actBirthday, int currentPage, int pageSize) {
        LOG.info("查询生日活动列表 actBirthday=" + JSON.toJSONString(actBirthday));
        TableData tableData = new TableData();
        try{

            Example example = new Example(ActBirthday.class);
            Example.Criteria criteria = example.createCriteria();
            if(actBirthday.getBirthdayName() != null && !actBirthday.getBirthdayName().trim().equals("")){
                criteria.andLike("birthdayName","%"+actBirthday.getBirthdayName()+"%");
            }
            if(actBirthday.getState() != null && !actBirthday.getState().trim().equals("")){
                criteria.andEqualTo("state",actBirthday.getState());
            }
            
            PageHelper.startPage(currentPage,pageSize);
            List<ActBirthday> actBirthdayList = actBirthdayMapper.selectByExample(example);
            PageInfo<ActBirthday> pageInfo = new PageInfo<ActBirthday>(actBirthdayList);

            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(pageInfo.getTotal());

            return tableData;
        }catch (Exception e){
            LOG.error("查询生日活动列表异常", e);
            return null;
        }
    }

    @Override
    public ActBirthday getActBirthdayById(String birthdayId) {
        LOG.info("根据Id查询生日活动 birthdayId=" + JSON.toJSONString(birthdayId));

        try{
            ActBirthday actBirthday = actBirthdayMapper.selectByPrimaryKey(birthdayId);
            Example example = new Example(BirthdayProgram.class);
            example.createCriteria().andEqualTo("birthdayId",birthdayId);
            List<BirthdayProgram> birthdayProgramList = birthdayProgramMapper.selectByExample(example);
            actBirthday.setBirthdayProgramList(birthdayProgramList);
            return actBirthday;
        }catch (Exception e){
            LOG.error("修改生日活动异常", e);
            return null;
        }
    }

    @Override
    public TableData queryProgramByBirthdayId(String birthdayId, int currentPage, int pageSize) {
        LOG.info("根据Id查询生日活动 birthdayId=" + JSON.toJSONString(birthdayId));

        try{

            TableData tableData = new TableData();

            Example example = new Example(BirthdayProgram.class);
            example.createCriteria().andEqualTo("birthdayId",birthdayId);

            PageHelper.startPage(currentPage,pageSize);
            List<BirthdayProgram> birthdayProgramList = birthdayProgramMapper.selectByExample(example);
            PageInfo<BirthdayProgram> pageInfo = new PageInfo<BirthdayProgram>(birthdayProgramList);

            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(pageInfo.getTotal());

            return tableData;
        }catch (Exception e){
            LOG.error("根据Id查询生日活动异常", e);
            return null;
        }
    }

    @Override
    public Integer changeState(ActBirthday actBirthday) {
        LOG.info("根据Id修改生日活动的状态 actBirthday=" + JSON.toJSONString(actBirthday));

        try{
            actBirthdayMapper.updateByPrimaryKeySelective(actBirthday);
            return 1;
        }catch (Exception e){
            LOG.error("修改生日活动状态异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public List<ActBirthday> queryActCardByValid() {
        LOG.info("根据有效的生日活动 " + JSON.toJSONString(""));

        try{

            Example example = new Example(ActBirthday.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("state","processing");

            List<ActBirthday> actBirthdayList = actBirthdayMapper.selectByExample(example);

            for(int i = 0;i < actBirthdayList.size();i++){

                ActBirthday actBirthday = actBirthdayList.get(i);
                Example example1 = new Example(BirthdayProgram.class);
                example1.createCriteria().andEqualTo("birthdayId",actBirthday.getBirthdayId());
                List<BirthdayProgram> birthdayProgramList = birthdayProgramMapper.selectByExample(example1);
                actBirthday.setBirthdayProgramList(birthdayProgramList);
                actBirthdayList.set(i,actBirthday);
            }

            return actBirthdayList;
        }catch (Exception e){
            LOG.error("根据有效的生日活动异常", e);
            return null;
        }
    }
}
