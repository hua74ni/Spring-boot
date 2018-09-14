package com.landicorp.marketing.entities;

import javax.persistence.*;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Table(name = "t_turntable")
public class Turntable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "turntable_id")
    private int turntableId;
    @Column(name = "title")
    private String title;
    @Column(name = "tags")
    private String tags;
    @Column(name = "preheating_time")
    private String preheatingTime;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "state")
    private String state;
    @Column(name = "is_daily_Redraw")
    private String isDailyRedraw;
    @Column(name = "daily_Redraw_nbr")
    private int dailyRedrawNbr;
    @Column(name = "everbody_init_nbr")
    private int everbodyInitNbr;
    @Column(name = "everbody_max_nbr")
    private int everbodyMaxNbr;
    @Column(name = "everbody_daily_max_nbr")
    private int everbodyDailyMaxNbr;
    @Column(name = "everbody_max_winning_nbr")
    private int everbodyMaxWinningNbr;
    @Column(name = "is_force_associator")
    private String isForceAssociator;
    @Column(name = "act_Descr")
    private String actDescr;
    @Column(name = "preheating_act_background_picture")
    private String preheatingActBackgroundPicture;
    @Column(name = "preheating_act_descr")
    private String preheatingActDescr;
    @Column(name = "share_cofig_benefit")
    private int shareCofigBenefit;
    @Column(name = "share_msg_title")
    private String shareMsgTitle;
    @Column(name = "share_msg_picture")
    private String shareMsgPicture;
    @Column(name = "share_msg_summary")
    private String shareMsgSummary;
    @Column(name = "act_template")
    private String actTemplate;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private long updateTime;

    public int getTurntableId() {
        return turntableId;
    }

    public void setTurntableId(int turntableId) {
        this.turntableId = turntableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPreheatingTime() {
        return preheatingTime;
    }

    public void setPreheatingTime(String preheatingTime) {
        this.preheatingTime = preheatingTime;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsDailyRedraw() {
        return isDailyRedraw;
    }

    public void setIsDailyRedraw(String isDailyRedraw) {
        this.isDailyRedraw = isDailyRedraw;
    }

    public int getDailyRedrawNbr() {
        return dailyRedrawNbr;
    }

    public void setDailyRedrawNbr(int dailyRedrawNbr) {
        this.dailyRedrawNbr = dailyRedrawNbr;
    }

    public int getEverbodyInitNbr() {
        return everbodyInitNbr;
    }

    public void setEverbodyInitNbr(int everbodyInitNbr) {
        this.everbodyInitNbr = everbodyInitNbr;
    }

    public int getEverbodyMaxNbr() {
        return everbodyMaxNbr;
    }

    public void setEverbodyMaxNbr(int everbodyMaxNbr) {
        this.everbodyMaxNbr = everbodyMaxNbr;
    }

    public int getEverbodyDailyMaxNbr() {
        return everbodyDailyMaxNbr;
    }

    public void setEverbodyDailyMaxNbr(int everbodyDailyMaxNbr) {
        this.everbodyDailyMaxNbr = everbodyDailyMaxNbr;
    }

    public int getEverbodyMaxWinningNbr() {
        return everbodyMaxWinningNbr;
    }

    public void setEverbodyMaxWinningNbr(int everbodyMaxWinningNbr) {
        this.everbodyMaxWinningNbr = everbodyMaxWinningNbr;
    }

    public String getIsForceAssociator() {
        return isForceAssociator;
    }

    public void setIsForceAssociator(String isForceAssociator) {
        this.isForceAssociator = isForceAssociator;
    }

    public String getActDescr() {
        return actDescr;
    }

    public void setActDescr(String preheatingAct) {
        this.actDescr = preheatingAct;
    }

    public String getPreheatingActBackgroundPicture() {
        return preheatingActBackgroundPicture;
    }

    public void setPreheatingActBackgroundPicture(String preheatingActBackgroundPicture) {
        this.preheatingActBackgroundPicture = preheatingActBackgroundPicture;
    }

    public String getPreheatingActDescr() {
        return preheatingActDescr;
    }

    public void setPreheatingActDescr(String preheatingActDescr) {
        this.preheatingActDescr = preheatingActDescr;
    }

    public int getShareCofigBenefit() {
        return shareCofigBenefit;
    }

    public void setShareCofigBenefit(int shareCofigBenefit) {
        this.shareCofigBenefit = shareCofigBenefit;
    }

    public String getShareMsgTitle() {
        return shareMsgTitle;
    }

    public void setShareMsgTitle(String shareMsgTitle) {
        this.shareMsgTitle = shareMsgTitle;
    }

    public String getShareMsgPicture() {
        return shareMsgPicture;
    }

    public void setShareMsgPicture(String shareMsgPicture) {
        this.shareMsgPicture = shareMsgPicture;
    }

    public String getShareMsgSummary() {
        return shareMsgSummary;
    }

    public void setShareMsgSummary(String shareMsgSummary) {
        this.shareMsgSummary = shareMsgSummary;
    }

    public String getActTemplate() {
        return actTemplate;
    }

    public void setActTemplate(String actTemplate) {
        this.actTemplate = actTemplate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Turntable{" +
                "turntableId=" + turntableId +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", preheatingTime='" + preheatingTime + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state='" + state + '\'' +
                ", isDailyRedraw='" + isDailyRedraw + '\'' +
                ", dailyRedrawNbr=" + dailyRedrawNbr +
                ", everbodyInitNbr=" + everbodyInitNbr +
                ", everbodyMaxNbr=" + everbodyMaxNbr +
                ", everbodyDailyMaxNbr=" + everbodyDailyMaxNbr +
                ", everbodyMaxWinningNbr=" + everbodyMaxWinningNbr +
                ", isForceAssociator='" + isForceAssociator + '\'' +
                ", actDescr='" + actDescr + '\'' +
                ", preheatingActBackgroundPicture='" + preheatingActBackgroundPicture + '\'' +
                ", preheatingActDescr='" + preheatingActDescr + '\'' +
                ", shareCofigBenefit=" + shareCofigBenefit +
                ", shareMsgTitle='" + shareMsgTitle + '\'' +
                ", shareMsgPicture='" + shareMsgPicture + '\'' +
                ", shareMsgSummary='" + shareMsgSummary + '\'' +
                ", actTemplate='" + actTemplate + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
