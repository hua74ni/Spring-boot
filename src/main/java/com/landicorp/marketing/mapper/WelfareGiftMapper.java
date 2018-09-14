package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.WelfareGift;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
@Repository
public interface WelfareGiftMapper extends CustomMapper<WelfareGift> {

    public List<WelfareGift> getWelfareGiftListByWelfareId(Integer welfareId);

}
