package com.landicorp.marketing.entities;

import javax.persistence.*;

/**
 * Created by huangdonghua on 09/04/2018.
 */
@Table(name = "t_card_program")
public class CardProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "program_id")
    private String programId;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "program_name")
    private String programName;
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

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
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
        return "CardProgram{" +
                "programId='" + programId + '\'' +
                ", cardId='" + cardId + '\'' +
                ", programName='" + programName + '\'' +
                ", giftType='" + giftType + '\'' +
                ", giftItem='" + giftItem + '\'' +
                ", giftItemLabel='" + giftItemLabel + '\'' +
                ", giftAmount='" + giftAmount + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}

