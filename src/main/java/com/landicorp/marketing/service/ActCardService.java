package com.landicorp.marketing.service;

import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.ActCard;
import com.landicorp.marketing.entities.CardProgram;

import java.util.List;

/**
 * Created by huangdonghua on 08/04/2018.
 */
public interface ActCardService {

    public Integer insertActCard(ActCard actCard);

    public Integer delActCardByIds(List<String> deleteIds);

    public Integer updateCard(ActCard actCard);

    public TableData queryActCardTable(ActCard actCard, int currentPage, int pageSize);

    public ActCard getActCardById(String cardId);

    public TableData queryCardProgramByCardId(String cardId, int currentPage, int pageSize);

    public Integer changeState(ActCard actCard);

    public List<ActCard> queryActCardByValid(String storeId);

}
