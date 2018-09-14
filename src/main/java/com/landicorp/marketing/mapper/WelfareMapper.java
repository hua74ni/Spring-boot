package com.landicorp.marketing.mapper;


import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.Welfare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
@Repository
public interface WelfareMapper extends CustomMapper<Welfare> {

    public List<Welfare> queryWelfareTable(@Param("welfare") Welfare welfare, @Param("offset") int offset, @Param("pageSize") int pageSize);

    public long countWelfareTable(Welfare welfare);

}
