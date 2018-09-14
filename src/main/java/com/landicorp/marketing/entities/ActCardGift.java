package com.landicorp.marketing.entities;

import javax.persistence.*;

/**
 * Created by huangdonghua on 2018/4/17.
 */
@Table(name = "t_act_card_gift")
public class ActCardGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "card_gift_id")
    private String cardGiftId;

    @Column(name = "opend_id")
    private String openId;
    @Column(name = "store_id")
    private String storeId;
    @Column(name = "act_card_id")
    private String actCardId;
    @Column(name = "card_gift_id")
    private String cardProgramId;
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

    public String getCardGiftId() {
        return cardGiftId;
    }

    public void setCardGiftId(String cardGiftId) {
        this.cardGiftId = cardGiftId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String open_id) {
        this.openId = open_id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getActCardId() {
        return actCardId;
    }

    public void setActCardId(String actCardId) {
        this.actCardId = actCardId;
    }

    public String getCardProgramId() {
        return cardProgramId;
    }

    public void setCardProgramId(String cardProgramId) {
        this.cardProgramId = cardProgramId;
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
        return "ActCardGift{" +
                "cardGiftId='" + cardGiftId + '\'' +
                ", open_id='" + openId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", actCardId='" + actCardId + '\'' +
                ", cardProgramId='" + cardProgramId + '\'' +
                ", giftType='" + giftType + '\'' +
                ", giftItem='" + giftItem + '\'' +
                ", giftItemLabel='" + giftItemLabel + '\'' +
                ", giftAmount=" + giftAmount +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
