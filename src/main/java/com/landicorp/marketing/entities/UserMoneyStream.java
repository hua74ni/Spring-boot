package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by huangdonghua on 2018/4/14.
 */
@Table(name = "t_user_money_stream")
public class UserMoneyStream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "money_stream_id")
    private String moneyStreamId;
    @Column(name = "open_id")
    private String openId;
    @Column(name = "store_id")
    private String storeId;
    @Column(name = "operate_type")
    private String operateType;
    @Column(name = "money")
    private String money;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private Long updateTime;

    public String getMoneyStreamId() {
        return moneyStreamId;
    }

    public void setMoneyStreamId(String moneyStreamId) {
        this.moneyStreamId = moneyStreamId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        BigDecimal bigDecimal = new BigDecimal(money);
        this.money = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "UserMoneyStream{" +
                "moneyStreamId='" + moneyStreamId + '\'' +
                ", openId='" + openId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", operateType='" + operateType + '\'' +
                ", money='" + money + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
