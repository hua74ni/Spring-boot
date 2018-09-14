package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by huangdonghua on 08/04/2018.
 */
@Table(name = "t_act_card")
public class ActCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    @Column(name = "card_id")
    private String cardId;

    // 方案配置
    private List<CardProgram> cardProgramList;

    @Column(name = "card_name")
    private String cardName;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String carId) {
        this.cardId = carId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String carName) {
        this.cardName = carName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public List<CardProgram> getCardProgramList() {
        return cardProgramList;
    }

    public void setCardProgramList(List<CardProgram> cardProgramList) {
        this.cardProgramList = cardProgramList;
    }

    @Override
    public String toString() {
        return "ActCard{" +
                "carId=" + cardId +
                ", carName='" + cardName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isNotice='" + isNotice + '\'' +
                ", noticeStartDatetime='" + noticeStartDatetime + '\'' +
                ", noticeEndDatetime='" + noticeEndDatetime + '\'' +
                ", noticePhone='" + noticePhone + '\'' +
                ", state='" + state + '\'' +
                ", cardProgramList=" + cardProgramList +
                ", storeIds='" + storeIds + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
