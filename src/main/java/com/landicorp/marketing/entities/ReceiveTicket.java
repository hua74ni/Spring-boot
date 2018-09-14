package com.landicorp.marketing.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by huangdonghua on 2018/3/21.
 */
@Table(name = "t_receive_ticket")
public class ReceiveTicket {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "receive_ticket_id")
    private int receiveTicketId;
    @Column(name = "ticket_id")
    private int ticketId;
    @Column(name = "ticket_batch")
    private String ticketBatch;
    @Column(name = "effective_start_time")
    private String effectiveStartTime;
    @Column(name = "effective_end_time")
    private String effectiveEndTime;
    @Column(name = "is_force_associator")
    private String isForceAssociator;
    @Column(name = "state")
    private String state;
    @Column(name = "qr_code")
    private String qrCode;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private long updateTime;

    public int getReceiveTicketId() {
        return receiveTicketId;
    }

    public void setReceiveTicketId(int receiveTicketId) {
        this.receiveTicketId = receiveTicketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketBatch() {
        return ticketBatch;
    }

    public void setTicketBatch(String ticketBatch) {
        this.ticketBatch = ticketBatch;
    }

    public String getEffectiveStartTime() {
        return effectiveStartTime;
    }

    public void setEffectiveStartTime(String effectiveStartTime) {
        this.effectiveStartTime = effectiveStartTime;
    }

    public String getEffectiveEndTime() {
        return effectiveEndTime;
    }

    public void setEffectiveEndTime(String effectiveEndTime) {
        this.effectiveEndTime = effectiveEndTime;
    }

    public String getIsForceAssociator() {
        return isForceAssociator;
    }

    public void setIsForceAssociator(String isForceAssociator) {
        this.isForceAssociator = isForceAssociator;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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
        return "ReceiveTicket{" +
                "receiveTicketId=" + receiveTicketId +
                ", ticketId=" + ticketId +
                ", ticketBatch='" + ticketBatch + '\'' +
                ", effectiveStartTime='" + effectiveStartTime + '\'' +
                ", effectiveEndTime='" + effectiveEndTime + '\'' +
                ", isForceAssociator='" + isForceAssociator + '\'' +
                ", state='" + state + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
