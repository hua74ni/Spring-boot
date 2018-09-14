package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangdonghua on 09/04/2018.
 */
@Table(name = "t_specific_program")
public class SpecificProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "program_id")
    private String programId;
    @Column(name = "act_id_type")
    private String actIdType;
    @Column(name = "act_id")
    private String actId;

    List<SpecificGift> specificGiftList;

    @Column(name = "specific_type")
    private String specificType;
    @Column(name = "program_name")
    private String programName;
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

    public List<SpecificGift> getSpecificGiftList() {
        return specificGiftList;
    }

    public void setSpecificGiftList(List<SpecificGift> specificGiftList) {
        this.specificGiftList = specificGiftList;
    }

    public String getSpecificType() {
        return specificType;
    }

    public void setSpecificType(String specificType) {
        this.specificType = specificType;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
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
        return "SpecificProgram{" +
                "programId='" + programId + '\'' +
                ", actIdType='" + actIdType + '\'' +
                ", actId='" + actId + '\'' +
                ", specificGiftList=" + specificGiftList +
                ", specificType='" + specificType + '\'' +
                ", programName='" + programName + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                ", limitedTimeType='" + limitedTimeType + '\'' +
                ", actTotalLimitAmount=" + actTotalLimitAmount +
                ", everybodyLimitAmount=" + everybodyLimitAmount +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
