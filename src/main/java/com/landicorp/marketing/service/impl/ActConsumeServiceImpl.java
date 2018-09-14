package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActConsume;
import com.landicorp.marketing.entities.ActConsume;
import com.landicorp.marketing.entities.ActConsume;
import com.landicorp.marketing.entities.SpecificProgram;
import com.landicorp.marketing.mapper.ActConsumeMapper;
import com.landicorp.marketing.mapper.ActConsumeMapper;
import com.landicorp.marketing.service.ActConsumeService;
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
@Service("actConsumeService")
@Transactional
public class ActConsumeServiceImpl implements ActConsumeService {
    private static final Logger LOG = LoggerFactory.getLogger(ActConsumeServiceImpl.class);

    @Autowired
    private ActConsumeMapper actConsumeMapper;

    @Autowired
    private SpecificProgramService specificProgramService;

    @Override
    public Integer insertActConsume(ActConsume actConsume) {
        LOG.info("新增消费活动 actConsume=" + JSON.toJSONString(actConsume));

        try{

            String consumeId = UUID.randomUUID().toString();
            actConsume.setConsumeId(consumeId);
            actConsume.setState("unpublished");
            if(actConsume.getIsNotice().equals("0")){
                actConsume.setNoticeStartDatetime(null);
                actConsume.setNoticeEndDatetime(null);
            }

            actConsumeMapper.insertSelective(actConsume);
            List<SpecificProgram> cardProgramList = actConsume.getSpecificProgramList();
            specificProgramService.insertSpecificProgram(cardProgramList,consumeId,"actConsume");

            return 1;
        }catch (Exception e){
            LOG.error("新增消费活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer delActConsumeByIds(List<String> deleteIds) {
        LOG.info("删除消费活动 birthdayId=" + JSON.toJSONString(deleteIds));

        try{
            for(int i = 0;i < deleteIds.size();i++){
                specificProgramService.delSpecificProgramsByCardId(deleteIds.get(i),"actConsume");
            }
            actConsumeMapper.delActConsumes(deleteIds);
            return 1;
        }catch (Exception e){
            LOG.error("删除消费活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer updateCard(ActConsume actConsume) {
        return null;
    }

    @Override
    public TableData queryActConsumeTable(ActConsume actConsume, int currentPage, int pageSize) {
        LOG.info("查询消费活动列表 actConsume=" + JSON.toJSONString(actConsume));
        TableData tableData = new TableData();
        try{

            Example example = new Example(ActConsume.class);
            Example.Criteria criteria = example.createCriteria();
            if(actConsume.getConsumeName() != null && !actConsume.getConsumeName().trim().equals("")){
                criteria.andLike("consumeName","%"+actConsume.getConsumeName()+"%");
            }
            if(actConsume.getState() != null && !actConsume.getState().trim().equals("")){
                criteria.andEqualTo("state",actConsume.getState());
            }

            if(actConsume.getStartDate() != null && !actConsume.getStartDate().trim().equals("")){
                criteria.andGreaterThanOrEqualTo("startDate",actConsume.getStartDate());
            }
            if(actConsume.getEndDate() != null && !actConsume.getEndDate().trim().equals("")){
                criteria.andLessThanOrEqualTo("endDate",actConsume.getEndDate());
            }

            PageHelper.startPage(currentPage,pageSize);
            List<ActConsume> actConsumeList = actConsumeMapper.selectByExample(example);
            PageInfo<ActConsume> pageInfo = new PageInfo<ActConsume>(actConsumeList);

            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(pageInfo.getTotal());

            return tableData;
        }catch (Exception e){
            LOG.error("查询消费活动列表异常", e);
            return null;
        }
    }

    @Override
    public ActConsume getActConsumeById(String consumeId) {
        LOG.info("根据Id查询消费活动 birthdayId=" + JSON.toJSONString(consumeId));

        try{
            ActConsume actConsume = actConsumeMapper.selectByPrimaryKey(consumeId);
            List<SpecificProgram> specificProgramList = specificProgramService.getSpecificProgramList(consumeId,"actConsume");
            actConsume.setSpecificProgramList(specificProgramList);
            return actConsume;
        }catch (Exception e){
            LOG.error("修改消费活动异常", e);
            return null;
        }
    }

    @Override
    public TableData queryProgramByConsumeId(String consumeId, int currentPage, int pageSize) {
        LOG.info("根据Id查询消费活动 consumeId=" + JSON.toJSONString(consumeId));

        try{

            return specificProgramService.querySpecificProgram(consumeId,"actConsume",currentPage,pageSize);
        }catch (Exception e){
            LOG.error("根据Id查询消费活动异常", e);
            return null;
        }
    }

    @Override
    public Integer changeState(ActConsume actConsume) {
        LOG.info("根据Id修改消费活动的状态 actConsume=" + JSON.toJSONString(actConsume));

        try{
            actConsumeMapper.updateByPrimaryKeySelective(actConsume);
            return 1;
        }catch (Exception e){
            LOG.error("修改消费活动状态异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }



    /*
        消费活动需要考虑：

            1. 触发时间是活动期间及状态是开启
            2. 对应的商家及活动是否有时间星期几限制

        sql:

            SELECT
                *
            FROM
                t_act_consume
            WHERE
                (
                    state = 'processing'
                    AND store_ids LIKE '%00080001%'
                    AND end_date >= now()
                    AND end_time >= now()
                    AND start_date <= now()
                    AND start_time <= now()
                )
            AND(
                specific_time = '0'
                OR specific_time LIKE '_____1_'
            )

    */

    @Override
    public List<ActConsume> queryActConsumeByValid(String storeId) {
        LOG.info("根据storeId查询有效的消费活动 storeId=" + JSON.toJSONString(storeId));

        try{

            Example example = new Example(ActConsume.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("state","processing")
                    .andLike("storeIds","%"+storeId+"%")
                    .andGreaterThanOrEqualTo("endDate",DateUtil.formatDay(new Date()))
                    .andGreaterThanOrEqualTo("endTime",DateUtil.formatTime(new Date()))
                    .andLessThanOrEqualTo("startDate",DateUtil.formatDay(new Date()))
                    .andLessThanOrEqualTo("startTime",DateUtil.formatTime(new Date()));

            example.and().orEqualTo("specificTime",'0')
                         .orLike("specificTime", DateUtil.getWeekOfDate(new Date()));

            List<ActConsume> actConsumeList = actConsumeMapper.selectByExample(example);

            for(int i = 0;i < actConsumeList.size();i++){

                ActConsume actConsume = actConsumeList.get(i);
                List<SpecificProgram> specificProgramList = specificProgramService.getSpecificProgramList(actConsume.getConsumeId(),"actConsume");
                actConsume.setSpecificProgramList(specificProgramList);
                actConsumeList.set(i,actConsume);
            }

            return actConsumeList;
        }catch (Exception e){
            LOG.error("根据storeId查询有效的消费活动异常", e);
            return null;
        }
    }

}
