package com.landicorp.marketing.mapper;


import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.TurntablePrize;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
@Repository
public interface TurntablePrizeMapper extends CustomMapper<TurntablePrize> {

    public List<TurntablePrize> getTurntablePrizeListByTurntableId(int turntableId);

}
