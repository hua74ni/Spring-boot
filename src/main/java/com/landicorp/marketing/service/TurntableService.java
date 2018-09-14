package com.landicorp.marketing.service;



import com.landicorp.marketing.common.TableData;
import com.landicorp.marketing.entities.Turntable;

import java.util.List;

/**
 * Created by huangdonghua on 2018/3/21.
 */
public interface TurntableService {

    public TableData queryTurntableTable(Turntable turntable, int currentPage, int pageSize);

    public Integer insertTurntable(Turntable turntable);

    public Integer delTurntable(List<Integer> deleteIds);

    public Integer updateTurntable(Turntable turntable);

    public List<Turntable> queryturntableList(Turntable turntable);
}
