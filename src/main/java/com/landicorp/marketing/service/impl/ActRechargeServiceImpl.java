package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActRecharge;
import com.landicorp.marketing.entities.SpecificProgram;
import com.landicorp.marketing.mapper.ActRechargeMapper;
import com.landicorp.marketing.service.ActRechargeService;
import com.landicorp.marketing.service.SpecificProgramService;
import com.landicorp.marketing.utill.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by huangdonghua on 11/04/2018.
 */
@Service("actRechargeService")
@Transactional
public class ActRechargeServiceImpl implements ActRechargeService {
    private static final Logger LOG = LoggerFactory.getLogger(ActRechargeServiceImpl.class);

    @Autowired
    private ActRechargeMapper actRechargeMapper;

    @Autowired
    private SpecificProgramService specificProgramService;


    @Override
    public Integer insertActRecharge(ActRecharge actRecharge) {
        LOG.info("新增充值活动 actRecharge=" + JSON.toJSONString(actRecharge));

        try{

            String rechargeId = UUID.randomUUID().toString();
            actRecharge.setRechargeId(rechargeId);
            actRecharge.setState("unpublished");
            if(actRecharge.getIsNotice().equals("0")){
                actRecharge.setNoticeStartDatetime(null);
                actRecharge.setNoticeEndDatetime(null);
            }

            actRechargeMapper.insertSelective(actRecharge);
            List<SpecificProgram> cardProgramList = actRecharge.getSpecificProgramList();
            specificProgramService.insertSpecificProgram(cardProgramList,rechargeId,"actRecharge");

            return 1;
        }catch (Exception e){
            LOG.error("新增充值活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer delActRechargeByIds(List<String> deleteIds) {
        LOG.info("删除充值活动 birthdayId=" + JSON.toJSONString(deleteIds));

        try{
            for(int i = 0;i < deleteIds.size();i++){
                specificProgramService.delSpecificProgramsByCardId(deleteIds.get(i),"actRecharge");
            }
            actRechargeMapper.delActRecharges(deleteIds);
            return 1;
        }catch (Exception e){
            LOG.error("删除充值活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer updateCard(ActRecharge actRecharge) {
        return null;
    }

    @Override
    public TableData queryActRechargeTable(ActRecharge actRecharge, int currentPage, int pageSize) {
        LOG.info("查询充值活动列表 actRecharge=" + JSON.toJSONString(actRecharge));
        TableData tableData = new TableData();
        try{

            Example example = new Example(ActRecharge.class);
            Example.Criteria criteria = example.createCriteria();
            if(actRecharge.getRechargeName() != null && !actRecharge.getRechargeName().trim().equals("")){
                criteria.andLike("rechargeName","%"+actRecharge.getRechargeName()+"%");
            }
            if(actRecharge.getState() != null && !actRecharge.getState().trim().equals("")){
                criteria.andEqualTo("state",actRecharge.getState());
            }

            if(actRecharge.getStartDate() != null && !actRecharge.getStartDate().trim().equals("")){
                criteria.andGreaterThanOrEqualTo("startDate",actRecharge.getStartDate());
            }
            if(actRecharge.getEndDate() != null && !actRecharge.getEndDate().trim().equals("")){
                criteria.andLessThanOrEqualTo("endDate",actRecharge.getEndDate());
            }

            PageHelper.startPage(currentPage,pageSize);
            List<ActRecharge> actRechargeList = actRechargeMapper.selectByExample(example);
            PageInfo<ActRecharge> pageInfo = new PageInfo<ActRecharge>(actRechargeList);

            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(pageInfo.getTotal());

            return tableData;
        }catch (Exception e){
            LOG.error("查询充值活动列表异常", e);
            return null;
        }
    }

    @Override
    public ActRecharge getActRechargeById(String rechargeId) {
        LOG.info("根据Id查询充值活动 birthdayId=" + JSON.toJSONString(rechargeId));

        try{
            ActRecharge actRecharge = actRechargeMapper.selectByPrimaryKey(rechargeId);
            List<SpecificProgram> specificProgramList = specificProgramService.getSpecificProgramList(rechargeId,"actRecharge");
            actRecharge.setSpecificProgramList(specificProgramList);
            return actRecharge;
        }catch (Exception e){
            LOG.error("修改充值活动异常", e);
            return null;
        }
    }

    @Override
    public TableData queryProgramByRechargeId(String rechargeId, int currentPage, int pageSize) {
        LOG.info("根据Id查询充值活动 rechargeId=" + JSON.toJSONString(rechargeId));

        try{

            return specificProgramService.querySpecificProgram(rechargeId,"actRecharge",currentPage,pageSize);
        }catch (Exception e){
            LOG.error("根据Id查询充值活动异常", e);
            return null;
        }
    }

    @Override
    public Integer changeState(ActRecharge actRecharge) {
        LOG.info("根据Id修改充值活动的状态 actRecharge=" + JSON.toJSONString(actRecharge));

        try{
            actRechargeMapper.updateByPrimaryKeySelective(actRecharge);
            return 1;
        }catch (Exception e){
            LOG.error("修改充值活动状态异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public List<ActRecharge> queryActRechargeByValid(String storeId) {
        LOG.info("根据storeId查询有效的充值活动 storeId=" + JSON.toJSONString(storeId));

        try{

            Example example = new Example(ActRecharge.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("state","processing")
                    .andLike("storeIds","%"+storeId+"%")
                    .andGreaterThanOrEqualTo("endDate", DateUtil.formatDay(new Date()))
                    .andGreaterThanOrEqualTo("endTime",DateUtil.formatTime(new Date()))
                    .andLessThanOrEqualTo("startDate",DateUtil.formatDay(new Date()))
                    .andLessThanOrEqualTo("startTime",DateUtil.formatTime(new Date()));

            example.and().orEqualTo("specificTime",'0')
                    .orLike("specificTime", DateUtil.getWeekOfDate(new Date()));

            List<ActRecharge> actRechargeList = actRechargeMapper.selectByExample(example);

            for(int i = 0;i < actRechargeList.size();i++){

                ActRecharge actRecharge = actRechargeList.get(i);
                List<SpecificProgram> specificProgramList = specificProgramService.getSpecificProgramList(actRecharge.getRechargeId(),"actRecharge");
                actRecharge.setSpecificProgramList(specificProgramList);
                actRechargeList.set(i,actRecharge);
            }

            return actRechargeList;
        }catch (Exception e){
            LOG.error("根据storeId查询有效的充值活动异常", e);
            return null;
        }
    }
}
