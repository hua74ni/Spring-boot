package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Table(name = "t_welfare")
public class Welfare {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "welfare_id")
    private int welfareId;
    @Column(name = "act_title")
    private String actTitle;
    @Column(name = "act_slogan")
    private String actSlogan;
    @Column(name = "welfare_amount")
    private int welfareAmount;
    @Column(name = "welfare_price")
    private String welfarePrice;
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
    @Column(name = "everybody_max_act_nbr")
    private int everybodyMaxActNbr;
    @Column(name = "everybody_daily_max_act_nbr")
    private int everybodyDailyMaxActNbr;
    @Column(name = "everybody_daily_winning_nbr")
    private int everybodyDailyWinningNbr;
    @Column(name = "is_force_associator")
    private String isForceAssociator;
    @Column(name = "act_rule_descr")
    private String actRuleDescr;
    @Column(name = "purchased_gift_amount")
    private int purchasedGiftAmount;
    @Column(name = "winner_winning_nbr")
    private int winnerWinningNbr;
    @Column(name = "loser_winning_nbr")
    private int loserWinningNbr;
    @Column(name = "share_msg_title")
    private String shareMsgTitle;
    @Column(name = "share_msg_picture")
    private String shareMsgPicture;
    @Column(name = "share_msg_summary")
    private String shareMsgSummary;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private long updateTime;

    public int getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(int welfareId) {
        this.welfareId = welfareId;
    }

    public String getActTitle() {
        return actTitle;
    }

    public void setActTitle(String actTitle) {
        this.actTitle = actTitle;
    }

    public String getActSlogan() {
        return actSlogan;
    }

    public void setActSlogan(String actSlogan) {
        this.actSlogan = actSlogan;
    }

    public int getWelfareAmount() {
        return welfareAmount;
    }

    public void setWelfareAmount(int welfareAmount) {
        this.welfareAmount = welfareAmount;
    }

    public String getWelfarePrice() {
        return welfarePrice;
    }

    public void setWelfarePrice(String welfarePrice) {
        this.welfarePrice = welfarePrice;
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

    public int getEverybodyMaxActNbr() {
        return everybodyMaxActNbr;
    }

    public void setEverybodyMaxActNbr(int everybodyMaxActNbr) {
        this.everybodyMaxActNbr = everybodyMaxActNbr;
    }

    public int getEverybodyDailyMaxActNbr() {
        return everybodyDailyMaxActNbr;
    }

    public void setEverybodyDailyMaxActNbr(int everybodyDailyMaxActNbr) {
        this.everybodyDailyMaxActNbr = everybodyDailyMaxActNbr;
    }

    public int getEverybodyDailyWinningNbr() {
        return everybodyDailyWinningNbr;
    }

    public void setEverybodyDailyWinningNbr(int everybodyDailyWinningNbr) {
        this.everybodyDailyWinningNbr = everybodyDailyWinningNbr;
    }

    public String getIsForceAssociator() {
        return isForceAssociator;
    }

    public void setIsForceAssociator(String isForceAssociator) {
        this.isForceAssociator = isForceAssociator;
    }

    public String getActRuleDescr() {
        return actRuleDescr;
    }

    public void setActRuleDescr(String actRuleDescr) {
        this.actRuleDescr = actRuleDescr;
    }

    public int getPurchasedGiftAmount() {
        return purchasedGiftAmount;
    }

    public void setPurchasedGiftAmount(int purchasedGiftAmount) {
        this.purchasedGiftAmount = purchasedGiftAmount;
    }

    public int getWinnerWinningNbr() {
        return winnerWinningNbr;
    }

    public void setWinnerWinningNbr(int winnerWinningNbr) {
        this.winnerWinningNbr = winnerWinningNbr;
    }

    public int getLoserWinningNbr() {
        return loserWinningNbr;
    }

    public void setLoserWinningNbr(int loserWinningNbr) {
        this.loserWinningNbr = loserWinningNbr;
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
        return "Welfare{" +
                "welfareId=" + welfareId +
                ", actTitle='" + actTitle + '\'' +
                ", actSlogan='" + actSlogan + '\'' +
                ", welfareAmount=" + welfareAmount +
                ", welfarePrice='" + welfarePrice + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state='" + state + '\'' +
                ", everybodyMaxActNbr=" + everybodyMaxActNbr +
                ", everybodyDailyMaxActNbr=" + everybodyDailyMaxActNbr +
                ", everybodyDailyWinningNbr=" + everybodyDailyWinningNbr +
                ", isForceAssociator='" + isForceAssociator + '\'' +
                ", actRuleDescr='" + actRuleDescr + '\'' +
                ", purchasedGiftAmount=" + purchasedGiftAmount +
                ", winnerWinningNbr=" + winnerWinningNbr +
                ", loserWinningNbr=" + loserWinningNbr +
                ", shareMsgTitle='" + shareMsgTitle + '\'' +
                ", shareMsgPicture='" + shareMsgPicture + '\'' +
                ", shareMsgSummary='" + shareMsgSummary + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
