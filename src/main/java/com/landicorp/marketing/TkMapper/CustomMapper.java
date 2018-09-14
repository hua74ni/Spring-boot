package com.landicorp.marketing.TkMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by huangdonghua on 08/04/2018.
 */
//Mapper接口：基本的增、删、改、查方法
//MySqlMapper：针对MySQL的额外补充接口，支持批量插入
public interface CustomMapper<T> extends Mapper<T>, MySqlMapper<T>{



}
