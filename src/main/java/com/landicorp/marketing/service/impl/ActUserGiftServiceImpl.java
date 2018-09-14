package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.landicorp.marketing.entities.ActUserGift;
import com.landicorp.marketing.entities.SpecificProgram;
import com.landicorp.marketing.mapper.ActUserGiftMapper;
import com.landicorp.marketing.service.ActUserGiftService;
import com.landicorp.marketing.utill.DateUtil;
import com.landicorp.marketing.utill.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * Created by huangdonghua on 2018/4/14.
 */
@Service("actUserGiftService")
@Transactional
public class ActUserGiftServiceImpl implements ActUserGiftService {
    private static final Logger LOG = LoggerFactory.getLogger(ActUserGiftServiceImpl.class);

    @Autowired
    private ActUserGiftMapper actUserGiftMapper;

    @Override
    public Boolean getActLimitedByAct(String openId, SpecificProgram specificProgram) {

        Example example = new Example(ActUserGift.class);
        example.createCriteria().andEqualTo("actIdType",specificProgram.getActIdType()).andEqualTo("actProgramId",specificProgram.getProgramId());

        Map<String,String> map = null;
        switch (specificProgram.getLimitedTimeType()){
            case "每日":
                map = DateUtil.getTodayTime();
                break;
            case "每周":

                map = DateUtil.getWeekTime();
                break;
            case "每月":
                map = DateUtil.getMonthTime();
                break;
        }

        example.and().andGreaterThanOrEqualTo("createTime",map.get("startTime"))
                     .andLessThanOrEqualTo("createTime",map.get("endTime"));

        if(StringUtils.isNotNullAndBlank(openId)){
            example.and().andEqualTo("openId",openId);
        }

        Integer currentActTotal = actUserGiftMapper.selectCountByExample(example);

        if(StringUtils.isNotNullAndBlank(openId)){
            //当前活动该会员次数 大于等于 当前每个会员限制参与次数  则 该活动无效
            if(currentActTotal >= specificProgram.getEverybodyLimitAmount()){
                return false;
            }
            return true;
        }else {
            //当前活动次数 大于等于 当前限制参与次数  则 该活动无效
            if(currentActTotal >= specificProgram.getActTotalLimitAmount()){
                return false;
            }
            return true;
        }



    }

    @Override
    public void insertActUserGift(ActUserGift actUserGift) {
        LOG.info("插入用户活动数据 actUserGift=" + JSON.toJSONString(actUserGift));
        try{

            actUserGiftMapper.insertSelective(actUserGift);

        }catch (Exception e){
            LOG.error("插入用户活动数据异常",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Integer getActTotalNum(ActUserGift actUserGift) {
        return actUserGiftMapper.getActTotalNum(actUserGift);
    }


}
