package com.landicorp.marketing.enums;

import com.landicorp.marketing.utill.StringUtils;

/**
 * Created by huangdonghua on 2018/4/17.
 */
public enum GiftType {

    // 默认 name 为 INTEGRAL
    // title 为 "积分"
    INTEGRAL("积分"),
    GIFT_MONEY("赠送金"),
    COUPON("优惠卷"),
    GIFT_PACKAGE("礼包"),
    GOODS("物件"),
    GRADE("等级");

    private final String title;

    GiftType(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static GiftType match(String name){

        if(StringUtils.isNotNullAndBlank(name)){
            for (GiftType item:
                 GiftType.values()) {
                if(name.equals(item.getTitle())){
                    return item;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(GiftType.values().toString());
        System.out.println(GiftType.COUPON.title);
    }

}
