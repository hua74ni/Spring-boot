package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangdonghua on 09/04/2018.
 */
@Table(name = "t_specific_gift")
public class SpecificGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "gift_id")
    private String giftId;

    List<SpecificGift> specificGiftList;

    @Column(name = "program_id")
    private String programId;
    @Column(name = "max_gift_amount")
    private Integer maxGiftAmount;
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

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public List<SpecificGift> getSpecificGiftList() {
        return specificGiftList;
    }

    public void setSpecificGiftList(List<SpecificGift> specificGiftList) {
        this.specificGiftList = specificGiftList;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Integer getMaxGiftAmount() {
        return maxGiftAmount;
    }

    public void setMaxGiftAmount(Integer maxGiftAmount) {
        this.maxGiftAmount = maxGiftAmount;
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
        return "SpecificGift{" +
                "giftId='" + giftId + '\'' +
                ", specificGiftList=" + specificGiftList +
                ", programId='" + programId + '\'' +
                ", maxGiftAmount=" + maxGiftAmount +
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
