package com.landicorp.marketing.mapper;

import com.landicorp.marketing.TkMapper.CustomMapper;
import com.landicorp.marketing.entities.ReceiveTicket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/23.
 */
@Repository
public interface ReceiveTicketMapper extends CustomMapper<ReceiveTicket> {

    public List<ReceiveTicket> queryReceiveTicketTable(@Param("receiveTicket") ReceiveTicket receiveTicket, @Param("offset") int offset, @Param("pageSize") int pageSize);

}
