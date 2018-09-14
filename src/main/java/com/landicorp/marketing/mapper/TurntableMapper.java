package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.Turntable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Repository
public interface TurntableMapper extends CustomMapper<Turntable> {

    public List<Turntable> queryTurnTableTable(@Param("turntable") Turntable turntable, @Param("offset") int offset, @Param("pageSize") int pageSize);

    public void delTurntable(@Param("deleteIds") List<Integer> deleteIds);


}
