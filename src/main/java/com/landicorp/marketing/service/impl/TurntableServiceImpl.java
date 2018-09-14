package com.landicorp.marketing.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.Turntable;
import com.landicorp.marketing.mapper.TurntableMapper;
import com.landicorp.marketing.service.TurntableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Service("turntableService")
public class TurntableServiceImpl implements TurntableService {
    private static final Logger LOG = LoggerFactory.getLogger(TurntableServiceImpl.class);

    @Autowired
    private TurntableMapper turntableMapper;

    @Override
    public TableData queryTurntableTable(Turntable turntable, int currentPage, int pageSize) {
        LOG.info("查询大转盘列表 turntable=" + JSON.toJSONString(turntable));

        TableData tableData = new TableData();
        try{
//            List<Turntable> turntableList = turntableDao.queryTurnTableTable(turntable,(currentPage - 1)*pageSize,pageSize);
//            long totalCount = turntableDao.countTurnTableTable(turntable);

            Example example = new Example(Turntable.class);
            Example.Criteria criteria = example.createCriteria();
            if(turntable.getTitle() != null && !turntable.getTitle().trim().equals("")){
                criteria.andLike("title","%"+turntable.getTitle()+"%");
            }
            if(turntable.getState() != null && !turntable.getState().trim().equals("")){
                criteria.andEqualTo("state",turntable.getState());
            }
            if(turntable.getStartDate() != null && !turntable.getStartDate().trim().equals("")){
                criteria.andGreaterThanOrEqualTo("startDate",turntable.getStartDate());
            }
            if(turntable.getEndDate() != null && !turntable.getEndDate().trim().equals("")){
                criteria.andLessThanOrEqualTo("endDate",turntable.getEndDate());
            }
            PageHelper.startPage(currentPage,pageSize);
            List<Turntable> turntableList = turntableMapper.selectByExample(example);
            PageInfo<Turntable> pageInfo = new PageInfo<Turntable>(turntableList);

            tableData.setDataList(pageInfo.getList());
            tableData.setTotalCount(pageInfo.getTotal());
            tableData.setCurrentPage(currentPage);
            return tableData;
        }catch (Exception e){
            LOG.error("查询大转盘列表异常", e);
            return null;
        }
    }

    @Override
    public Integer insertTurntable(Turntable turntable) {
        LOG.info("新增大转盘 turntable=" +JSON.toJSONString(turntable));

        try{
            turntableMapper.insertSelective(turntable);
            return 1;
        }catch (Exception e){
            LOG.error("新增大转盘异常", e);
            return 0;
        }
    }

    @Override
    public Integer delTurntable(List<Integer> deleteIds) {
        LOG.info("删除大转盘 turntableId=" +JSON.toJSONString(deleteIds));

        try{
            turntableMapper.delTurntable(deleteIds);
            return 1;
        }catch (Exception e){
            LOG.error("删除大转盘异常", e);
            return 0;
        }
    }

    @Override
    public Integer updateTurntable(Turntable turntable) {
        LOG.info("修改大转盘 turntable=" +JSON.toJSONString(turntable));

        try{
            turntableMapper.updateByPrimaryKey(turntable);
            return 1;
        }catch (Exception e){
            LOG.error("修改大转盘异常", e);
            return 0;
        }
    }

    @Override
    public List<Turntable> queryturntableList(Turntable turntable) {
        LOG.info("查询大转盘列表 turntable=" +JSON.toJSONString(turntable));

        try{

            Example example = new Example(Turntable.class);
            example.createCriteria().andEqualTo("turntableId",turntable.getTurntableId());

            return turntableMapper.selectByExample(example);
        }catch (Exception e){
            LOG.error("查询大转盘列表异常", e);
            return null;
        }
    }

}
