package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ReceiveTicket;
import com.landicorp.marketing.mapper.ReceiveTicketMapper;
import com.landicorp.marketing.service.ReceiveTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
@Service("ReceiveTicketService")
public class ReceiveTicketServiceImpl implements ReceiveTicketService {
    private static final Logger LOG = LoggerFactory.getLogger(ReceiveTicketServiceImpl.class);

    @Autowired
    private ReceiveTicketMapper receiveTicketMapper;

    @Override
    public TableData queryReceiveTicketTable(ReceiveTicket receiveTicket, int currentPage, int pageSize) {
        LOG.info("查询领劵活动列表receiveTicket=" + JSON.toJSONString(receiveTicket));

        TableData tableData = new TableData();
        try {

            Example example = new Example(ReceiveTicket.class);
            if(receiveTicket.getTicketBatch() != null && !receiveTicket.getTicketBatch().trim().equals("")){
                example.createCriteria().andEqualTo("ticketBatch",receiveTicket.getTicketBatch());
            }
            PageHelper.startPage(currentPage,pageSize);
            List<ReceiveTicket> receiveTicketList = receiveTicketMapper.selectByExample(example);
            PageInfo<ReceiveTicket> pageInfo = new PageInfo<ReceiveTicket>(receiveTicketList);

            tableData.setTotalCount(pageInfo.getTotal());
            tableData.setDataList(pageInfo.getList());
            tableData.setCurrentPage(currentPage);

            return tableData;
        }catch (Exception e){
            LOG.error("查询领劵活动列表异常", e);
            return null;
        }
    }

    @Override
    public Integer insertReceiveTicket(ReceiveTicket receiveTicket) {
        LOG.info("新增领劵活动receiveTicket=" + JSON.toJSONString(receiveTicket));

        try{
            receiveTicketMapper.insertSelective(receiveTicket);
            return 1;
        }catch (Exception e){
            LOG.error("新增领劵活动异常",e);
            return 0;
        }
    }

    @Override
    public Integer delReceiveTicket(Integer receiveTicketId) {
        LOG.info("删除领劵活动receiveTicketId=" + JSON.toJSONString(receiveTicketId));

        try{
            receiveTicketMapper.deleteByPrimaryKey(receiveTicketId);
            return 1;
        }catch(Exception e){
            LOG.error("删除领劵活动异常",e);
            return 0;
        }

    }

    @Override
    public Integer updateReceiveTicket(ReceiveTicket receiveTicket) {
        LOG.info("修改领劵活动receiveTicket" + JSON.toJSONString(receiveTicket));

        try{
            receiveTicketMapper.updateByPrimaryKeySelective(receiveTicket);
            return 1;
        }catch (Exception e){
            LOG.error("修改领劵活动异常",e);
            return 0;
        }

    }
}
