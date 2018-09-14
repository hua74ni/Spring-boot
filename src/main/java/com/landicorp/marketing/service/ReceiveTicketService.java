package com.landicorp.marketing.service;


import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ReceiveTicket;

/**
 * Created by huangdonghua on 2018/3/23.
 */
public interface ReceiveTicketService {

    public TableData queryReceiveTicketTable(ReceiveTicket receiveTicket, int currentPage, int pageSize);

    public Integer insertReceiveTicket(ReceiveTicket receiveTicket);

    public Integer delReceiveTicket(Integer receiveTicketId);

    public Integer updateReceiveTicket(ReceiveTicket receiveTicket);

}
