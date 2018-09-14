package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActCard;
import com.landicorp.marketing.entities.ActConsume;
import com.landicorp.marketing.entities.CardProgram;
import com.landicorp.marketing.entities.SpecificProgram;
import com.landicorp.marketing.mapper.ActCardMapper;
import com.landicorp.marketing.mapper.CardProgramMapper;
import com.landicorp.marketing.service.ActCardService;
import com.landicorp.marketing.utill.DateUtil;
import com.landicorp.marketing.utill.StringUtils;
import org.apache.logging.log4j.LogManager;
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
 * Created by huangdonghua on 08/04/2018.
 */
@Service("actCardService")
@Transactional
public class ActCardServiceImpl implements ActCardService {
    private static final Logger LOG = LoggerFactory.getLogger(ActCardServiceImpl.class);

    @Autowired
    private ActCardMapper actCardMapper;

    @Autowired
    private CardProgramMapper cardProgramMapper;

    @Override
    public TableData queryActCardTable(ActCard actCard, int currentPage, int pageSize) {
        LOG.info("查询开卡活动列表 actCard=" + JSON.toJSONString(actCard));
        TableData tableData = new TableData();
        try{

            Example example = new Example(ActCard.class);
            Example.Criteria criteria = example.createCriteria();
            if(actCard.getCardName() != null && !actCard.getCardName().trim().equals("")){
                criteria.andLike("cardName","%"+actCard.getCardName()+"%");
            }
            if(actCard.getState() != null && !actCard.getState().trim().equals("")){
                criteria.andEqualTo("state",actCard.getState());
            }
            if(actCard.getStartDate() != null && !actCard.getStartDate().trim().equals("")){
                criteria.andGreaterThanOrEqualTo("startDate",actCard.getStartDate());
            }
            if(actCard.getEndDate() != null && !actCard.getEndDate().trim().equals("")){
                criteria.andLessThanOrEqualTo("endDate",actCard.getEndDate());
            }
            PageHelper.startPage(currentPage,pageSize);
            List<ActCard> actCardList = actCardMapper.selectByExample(example);
            PageInfo<ActCard> pageInfo = new PageInfo<ActCard>(actCardList);

            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(pageInfo.getTotal());

            return tableData;
        }catch (Exception e){
            LOG.error("查询开卡活动列表异常", e);
            return null;
        }
    }


    @Override
    public Integer insertActCard(ActCard actCard) {
        LOG.info("新增开卡活动 actCard=" + JSON.toJSONString(actCard));

        try{

            String cardId = UUID.randomUUID().toString();
            actCard.setCardId(cardId);
            actCard.setState("unpublished");
            if(actCard.getIsNotice().equals("0")){
                actCard.setNoticeStartDatetime(null);
                actCard.setNoticeEndDatetime(null);
            }

            actCardMapper.insertSelective(actCard);
            List<CardProgram> cardProgramList = actCard.getCardProgramList();
            for(int i = 0;i < cardProgramList.size();i++){
                CardProgram cardProgram = cardProgramList.get(i);
                cardProgram.setProgramId(UUID.randomUUID().toString());
                cardProgram.setCardId(cardId);
//                if(cardProgram.getGiftAmount() == 0){
//                    cardProgram.setGiftAmount(null);
//                }
                cardProgramMapper.insertSelective(cardProgram);
            }

            return 1;
        }catch (Exception e){
            LOG.error("新增开卡活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer delActCardByIds(List<String> deleteIds) {
        LOG.info("删除开卡活动 cardId=" + JSON.toJSONString(deleteIds));

        try{
            for(int i = 0;i < deleteIds.size();i++){
                String birthdayId = deleteIds.get(i);
                Example example = new Example(CardProgram.class);
                example.createCriteria().andEqualTo("cardId",birthdayId);
                cardProgramMapper.deleteByExample(example);
            }
            actCardMapper.delActCards(deleteIds);
            return 1;
        }catch (Exception e){
            LOG.error("删除开卡活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public Integer updateCard(ActCard actCard) {
        LOG.info("修改开卡活动actCard=" + JSON.toJSONString(actCard));

        try{
            actCardMapper.updateByPrimaryKeySelective(actCard);
            //需要同时修改 CardProgram
            return 1;
        }catch (Exception e){
            LOG.error("修改开卡活动异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }


    @Override
    public ActCard getActCardById(String cardId) {
        LOG.info("根据Id查询开卡活动 cardId=" + JSON.toJSONString(cardId));

        try{
            ActCard actCard = actCardMapper.selectByPrimaryKey(cardId);
            Example example = new Example(CardProgram.class);
            example.createCriteria().andEqualTo("cardId",actCard.getCardId());
            List<CardProgram> cardProgramList = cardProgramMapper.selectByExample(example);
            actCard.setCardProgramList(cardProgramList);
            return actCard;
        }catch (Exception e){
            LOG.error("修改开卡活动异常", e);
            return null;
        }
    }

    @Override
    public TableData queryCardProgramByCardId(String cardId, int currentPage, int pageSize) {
        LOG.info("根据Id查询开卡活动的方案配置 cardId=" + JSON.toJSONString(cardId));

        try{

            TableData tableData = new TableData();

            Example example = new Example(CardProgram.class);
            example.createCriteria().andEqualTo("cardId",cardId);

            PageHelper.startPage(currentPage,pageSize);
            List<CardProgram> cardProgramList = cardProgramMapper.selectByExample(example);
            PageInfo<CardProgram> pageInfo = new PageInfo<CardProgram>(cardProgramList);

            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(pageInfo.getTotal());

            return tableData;
        }catch (Exception e){
            LOG.error("修改开卡活动异常", e);
            return null;
        }
    }

    @Override
    public Integer changeState(ActCard actCard) {
        LOG.info("根据Id修改开卡活动的状态 actCard=" + JSON.toJSONString(actCard));

        try{
            actCardMapper.updateByPrimaryKeySelective(actCard);
            return 1;
        }catch (Exception e){
            LOG.error("修改开卡活动状态异常", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    public List<ActCard> queryActCardByValid(String storeId) {
        LOG.info("根据storeId查询有效的开卡活动 storeId=" + JSON.toJSONString(storeId));

        try{

            Example example = new Example(ActCard.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("state","processing")
                    .andLike("storeIds","%"+storeId+"%")
                    .andGreaterThanOrEqualTo("endDate", DateUtil.formatDay(new Date()))
                    .andGreaterThanOrEqualTo("endTime",DateUtil.formatTime(new Date()))
                    .andLessThanOrEqualTo("startDate",DateUtil.formatDay(new Date()))
                    .andLessThanOrEqualTo("startTime",DateUtil.formatTime(new Date()));

            List<ActCard> actCardList = actCardMapper.selectByExample(example);

            for(int i = 0;i < actCardList.size();i++){

                ActCard actCard = actCardList.get(i);
                Example example1 = new Example(CardProgram.class);
                example1.createCriteria().andEqualTo("cardId",actCard.getCardId());
                List<CardProgram> cardProgramList = cardProgramMapper.selectByExample(example1);
                actCard.setCardProgramList(cardProgramList);
                actCardList.set(i,actCard);
            }

            return actCardList;
        }catch (Exception e){
            LOG.error("根据storeId查询有效的开卡活动异常", e);
            return null;
        }
    }
}
