package com.landicorp.marketing.entities;

import javax.persistence.*;

/**
 * Created by huangdonghua on 2018/4/13.
 */
@Table(name = "t_birthday_program")
public class BirthdayProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "program_id")
    private String programId;
    @Column(name = "birthday_id")
    private String birthdayId;
    @Column(name = "program_name")
    private String programName;
    @Column(name = "gift_type")
    private String giftType;
    @Column(name = "gift_item")
    private String giftItem;
    @Column(name = "gift_item_label")
    private String giftItemLabel;
    @Column(name = "gift_amount")
    private String giftAmount;
    @Column(name = "membership_level")
    private String membershipLevel;
    @Column(name = "limited_time_type")
    private String limitedTimeType;
    @Column(name = "act_total_limit_amount")
    private Integer actTotalLimitAmount;
    @Column(name = "everybody_limit_amount")
    private Integer everybodyLimitAmount;
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

    public String getBirthdayId() {
        return birthdayId;
    }

    public void setBirthdayId(String birthdayId) {
        this.birthdayId = birthdayId;
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

    public String getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(String giftAmount) {
        this.giftAmount = giftAmount;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public String getLimitedTimeType() {
        return limitedTimeType;
    }

    public void setLimitedTimeType(String limitedTimeType) {
        this.limitedTimeType = limitedTimeType;
    }

    public Integer getActTotalLimitAmount() {
        return actTotalLimitAmount;
    }

    public void setActTotalLimitAmount(Integer actTotalLimitAmount) {
        this.actTotalLimitAmount = actTotalLimitAmount;
    }

    public Integer getEverybodyLimitAmount() {
        return everybodyLimitAmount;
    }

    public void setEverybodyLimitAmount(Integer everybodyLimitAmount) {
        this.everybodyLimitAmount = everybodyLimitAmount;
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
        return "BirthdayProgram{" +
                "programId='" + programId + '\'' +
                ", birthdayId='" + birthdayId + '\'' +
                ", programName='" + programName + '\'' +
                ", giftType='" + giftType + '\'' +
                ", giftItem='" + giftItem + '\'' +
                ", giftItemLabel='" + giftItemLabel + '\'' +
                ", giftAmount='" + giftAmount + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", limitedTimeType='" + limitedTimeType + '\'' +
                ", actTotalLimitAmount=" + actTotalLimitAmount +
                ", everybodyLimitAmount=" + everybodyLimitAmount +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
