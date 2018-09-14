package com.landicorp.marketing.entities;

import javax.persistence.*;

/**
 * Created by huangdonghua on 2018/4/14.
 */
@Table(name = "t_act_user_gift")
public class ActUserGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "user_gift_id")
    private String userGiftId;

    @Column(name = "open_id")
    private String openId;
    @Column(name = "act_id_type")
    private String actIdType;
    @Column(name = "act_id")
    private String actId;
    @Column(name = "store_id")
    private String storeId;
    @Column(name = "act_program_id")
    private String actProgramId;
    @Column(name = "money_stream_id")
    private String moneyStreamId;
    @Column(name = "specific_type")
    private String specificType;
    @Column(name = "special_condition")
    private Double specialCondition;
    @Column(name = "gift_type")
    private String giftType;
    @Column(name = "gift_item")
    private String giftItem;
    @Column(name = "gift_item_label")
    private String giftItemLabel;
    @Column(name = "gift_amount")
    private Integer giftAmount;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private Long updateTime;

    public String getUserGiftId() {
        return userGiftId;
    }

    public void setUserGiftId(String userGiftId) {
        this.userGiftId = userGiftId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getActIdType() {
        return actIdType;
    }

    public void setActIdType(String actIdType) {
        this.actIdType = actIdType;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getActProgramId() {
        return actProgramId;
    }

    public void setActProgramId(String actProgramId) {
        this.actProgramId = actProgramId;
    }

    public String getMoneyStreamId() {
        return moneyStreamId;
    }

    public void setMoneyStreamId(String moneyStreamId) {
        this.moneyStreamId = moneyStreamId;
    }

    public String getSpecificType() {
        return specificType;
    }

    public void setSpecificType(String specificType) {
        this.specificType = specificType;
    }

    public Double getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(Double specialCondition) {
        this.specialCondition = specialCondition;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getGiftItem() {
        return giftItem;
    }

    public void setGiftItem(String giftItem) {
        this.giftItem = giftItem;
    }

    public String getGiftItemLabel() {
        return giftItemLabel;
    }

    public void setGiftItemLabel(String giftItemLabel) {
        this.giftItemLabel = giftItemLabel;
    }

    public Integer getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Integer giftAmount) {
        this.giftAmount = giftAmount;
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
        return "ActUserGift{" +
                "userGiftId='" + userGiftId + '\'' +
                ", openId='" + openId + '\'' +
                ", actIdType='" + actIdType + '\'' +
                ", actId='" + actId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", actProgramId='" + actProgramId + '\'' +
                ", moneyStreamId='" + moneyStreamId + '\'' +
                ", specificType='" + specificType + '\'' +
                ", specialCondition=" + specialCondition +
                ", giftType='" + giftType + '\'' +
                ", giftItem='" + giftItem + '\'' +
                ", giftItemLabel='" + giftItemLabel + '\'' +
                ", giftAmount=" + giftAmount +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
