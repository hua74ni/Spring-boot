package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Table(name = "t_welfare_gift")
public class WelfareGift {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "gift_act_id")
    private int giftActId;
    @Column(name = "welfare_id")
    private int welfareId;
    @Column(name = "ticket_id")
    private int ticketId;
    @Column(name = "prize_amount")
    private int prizeAmount;
    @Column(name = "gift_act_type")
    private String giftActType;
    @Column(name = "winner_winning_probability")
    private String winnerWinningProbability;
    @Column(name = "loser_winning_probability")
    private String loserWinningProbability;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private long updateTime;

    public int getGiftActId() {
        return giftActId;
    }

    public void setGiftActId(int giftActId) {
        this.giftActId = giftActId;
    }

    public int getWelfareId() {
        return welfareId;
    }

    public void setWelfareId(int welfareId) {
        this.welfareId = welfareId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public String getGiftActType() {
        return giftActType;
    }

    public void setGiftActType(String giftActType) {
        this.giftActType = giftActType;
    }

    public String getWinnerWinningProbability() {
        return winnerWinningProbability;
    }

    public void setWinnerWinningProbability(String winnerWinningProbability) {
        this.winnerWinningProbability = winnerWinningProbability;
    }

    public String getLoserWinningProbability() {
        return loserWinningProbability;
    }

    public void setLoserWinningProbability(String loserWinningProbability) {
        this.loserWinningProbability = loserWinningProbability;
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
        return "WelfareGift{" +
                "giftActId=" + giftActId +
                ", welfareId=" + welfareId +
                ", ticketId=" + ticketId +
                ", prizeAmount=" + prizeAmount +
                ", giftActType='" + giftActType + '\'' +
                ", winnerWinningProbability='" + winnerWinningProbability + '\'' +
                ", loserWinningProbability='" + loserWinningProbability + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
