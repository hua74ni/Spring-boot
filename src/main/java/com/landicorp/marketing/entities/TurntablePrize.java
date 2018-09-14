package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Table(name = "t_turntable_prize")
public class TurntablePrize {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "prize_id")
    private int prizeId;
    @Column(name = "turntable_id")
    private int turntableId;
    @Column(name = "prize_name")
    private String prizeName;
    @Column(name = "prize_grade")
    private String prizeGrade;
    @Column(name = "prize_type")
    private String prizeType;
    @Column(name = "gift_nbr")
    private int giftNbr;
    @Column(name = "prize_picture")
    private String prizePicture;
    @Column(name = "actual_send_nbr")
    private int actualSendNbr;
    @Column(name = "display_nbr")
    private int displayNbr;
    @Column(name = "winning_probability")
    private String winningProbability;
    @Column(name = "is_open")
    private String isOpen;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private long updateTime;

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public int getTurntableId() {
        return turntableId;
    }

    public void setTurntableId(int turntableId) {
        this.turntableId = turntableId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeGrade() {
        return prizeGrade;
    }

    public void setPrizeGrade(String prizeGqrade) {
        this.prizeGrade = prizeGqrade;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public int getGiftNbr() {
        return giftNbr;
    }

    public void setGiftNbr(int giftNbr) {
        this.giftNbr = giftNbr;
    }

    public String getPrizePicture() {
        return prizePicture;
    }

    public void setPrizePicture(String prizePicture) {
        this.prizePicture = prizePicture;
    }

    public int getActualSendNbr() {
        return actualSendNbr;
    }

    public void setActualSendNbr(int actualSendNbr) {
        this.actualSendNbr = actualSendNbr;
    }

    public int getDisplayNbr() {
        return displayNbr;
    }

    public void setDisplayNbr(int displayNbr) {
        this.displayNbr = displayNbr;
    }

    public String getWinningProbability() {
        return winningProbability;
    }

    public void setWinningProbability(String winningProbability) {
        this.winningProbability = winningProbability;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
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
        return "TurntablePrize{" +
                "prizeId=" + prizeId +
                ", turntableId=" + turntableId +
                ", prizeName='" + prizeName + '\'' +
                ", prizeGqrade='" + prizeGrade + '\'' +
                ", prizeType='" + prizeType + '\'' +
                ", giftNbr=" + giftNbr +
                ", prizePicture='" + prizePicture + '\'' +
                ", actualSendNbr=" + actualSendNbr +
                ", displayNbr=" + displayNbr +
                ", winningProbability='" + winningProbability + '\'' +
                ", isOpen='" + isOpen + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
