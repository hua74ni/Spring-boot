package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangdonghua on 09/04/2018.
 */
@Table(name = "t_act_birthday")
public class ActBirthday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "birthday_id")
    private String birthdayId;

    // 方案配置
    private List<BirthdayProgram> birthdayProgramList;

    @Column(name = "birthday_name")
    private String birthdayName;
    @Column(name = "advance_trigger_day")
    private Integer advanceTriggerDay;
    @Column(name = "trigger_time")
    private String triggerTime;
    @Column(name = "is_notice")
    private String isNotice;
    @Column(name = "notice_start_datetime")
    private String noticeStartDatetime;
    @Column(name = "notice_end_datetime")
    private String noticeEndDatetime;
    @Column(name = "notice_phone")
    private String noticePhone;
    @Column(name = "state")
    private String state;
    @Column(name = "store_ids")
    private String storeIds;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private Long updateTime;

    public String getBirthdayId() {
        return birthdayId;
    }

    public void setBirthdayId(String birthdayId) {
        this.birthdayId = birthdayId;
    }

    public List<BirthdayProgram> getBirthdayProgramList() {
        return birthdayProgramList;
    }

    public void setBirthdayProgramList(List<BirthdayProgram> birthdayProgramList) {
        this.birthdayProgramList = birthdayProgramList;
    }

    public String getBirthdayName() {
        return birthdayName;
    }

    public void setBirthdayName(String birthdayName) {
        this.birthdayName = birthdayName;
    }

    public Integer getAdvanceTriggerDay() {
        return advanceTriggerDay;
    }

    public void setAdvanceTriggerDay(Integer advanceTriggerDay) {
        this.advanceTriggerDay = advanceTriggerDay;
    }

    public String getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(String triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(String isNotice) {
        this.isNotice = isNotice;
    }

    public String getNoticeStartDatetime() {
        return noticeStartDatetime;
    }

    public void setNoticeStartDatetime(String noticeStartDatetime) {
        this.noticeStartDatetime = noticeStartDatetime;
    }

    public String getNoticeEndDatetime() {
        return noticeEndDatetime;
    }

    public void setNoticeEndDatetime(String noticeEndDatetime) {
        this.noticeEndDatetime = noticeEndDatetime;
    }

    public String getNoticePhone() {
        return noticePhone;
    }

    public void setNoticePhone(String noticePhone) {
        this.noticePhone = noticePhone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
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
        return "ActBirthday{" +
                "birthdayId='" + birthdayId + '\'' +
                ", birthdayProgramList=" + birthdayProgramList +
                ", birthdayName='" + birthdayName + '\'' +
                ", advanceTriggerDay='" + advanceTriggerDay + '\'' +
                ", triggerTime='" + triggerTime + '\'' +
                ", isNotice='" + isNotice + '\'' +
                ", noticeStartDatetime='" + noticeStartDatetime + '\'' +
                ", noticeEndDatetime='" + noticeEndDatetime + '\'' +
                ", noticePhone='" + noticePhone + '\'' +
                ", state='" + state + '\'' +
                ", storeIds='" + storeIds + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
