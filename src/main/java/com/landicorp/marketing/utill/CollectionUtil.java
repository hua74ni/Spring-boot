package com.landicorp.marketing.utill;

import java.util.Collection;

/**
 * 集合工具类
 * Created by jiangjt on 2017/9/25.
 */
public class CollectionUtil {
    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        if(collection==null||collection.size()==0){
            return true;
        }else{
            return false;
        }
    }
}
