package com.landicorp.marketing.enums;

/**
 * Created by huangdonghua on 2018/4/17.
 */
public enum MarketingProgram {

    // 默认 name 为 FULL_GIFT
    // title 为 "满多少送"
    FULL_GIFT("满多少送"),
    ACCUMULATION_GIFT("累计满多少送"),
    EVERY_FULL_GIFT("每满多少送"),
    UNCONDITIONAL_GIFT("无条件");

    //由于final修饰 因此 title需要在构造函数中初始化
    private final String title;

    MarketingProgram(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // 通过name 去匹配对应的方案 如果不存在 则使用默认方案 无条件
    public static MarketingProgram match(String name){
        if(name != null){
            for(MarketingProgram item:MarketingProgram.values()){
                if(name.equals(item.name())){
                    return item;
                }
            }
        }
        return UNCONDITIONAL_GIFT;
    }

}
