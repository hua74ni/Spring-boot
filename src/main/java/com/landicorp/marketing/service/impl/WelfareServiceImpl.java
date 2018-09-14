package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;

import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.Welfare;
import com.landicorp.marketing.mapper.WelfareMapper;
import com.landicorp.marketing.service.WelfareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
@Service("welfareService")
public class WelfareServiceImpl implements WelfareService {
    private static final Logger LOG = LoggerFactory.getLogger(WelfareServiceImpl.class);

    @Autowired
    private WelfareMapper welfareMapper;

    @Override
    public TableData queryWelfareTable(Welfare welfare, int currentPage, int pageSize) {
        LOG.info("查询福袋列表welfare=" + JSON.toJSONString(welfare));
        TableData tableData = new TableData();
        try{

            List<Welfare> welfareList = welfareMapper.queryWelfareTable(welfare,(currentPage - 1)*pageSize,pageSize);
            long totalCount = welfareMapper.countWelfareTable(welfare);

            tableData.setDataList(welfareList);
            tableData.setCurrentPage(currentPage);
            tableData.setTotalCount(totalCount);

            return tableData;
        }catch (Exception e){
            LOG.error("");
            return null;
        }

    }

    @Override
    public Integer insertWelfare(Welfare welfare) {
        LOG.info("新增福袋welfare=" + JSON.toJSONString(welfare));

        try{
            welfareMapper.insertSelective(welfare);
            return 1;
        }catch (Exception e){
            LOG.error("新增福袋异常", e);
            return 0;
        }
    }

    @Override
    public Integer updateWelfare(Welfare welfare) {
        LOG.info("修改福袋welfare=" + JSON.toJSONString(welfare));

        try{
            welfareMapper.updateByPrimaryKeySelective(welfare);
            return 1;
        }catch (Exception e){
            LOG.error("修改福袋异常", e);
            return 0;
        }
    }

    @Override
    public Integer delWelfare(Welfare welfare) {
        LOG.info("删除福袋welfare=" + JSON.toJSONString(welfare));

        try{
            welfareMapper.deleteByPrimaryKey(welfare);
            return 1;
        }catch (Exception e){
            LOG.error("删除福袋异常", e);
            return 0;
        }

    }

    @Override
    public List<Welfare> queryWelfareList(Welfare welfare) {
        LOG.info("根据ID获取福袋welfare=" + JSON.toJSONString(welfare));

        try{

            Example example = new Example(Welfare.class);
            example.createCriteria().andEqualTo("welfareId",welfare.getWelfareId());

            List<Welfare> welfareList = welfareMapper.selectByExample(example);
            return welfareList;
        }catch (Exception e){
            LOG.error("根据ID获取福袋异常", e);
            return null;
        }
    }
}
