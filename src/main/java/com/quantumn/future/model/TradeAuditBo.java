package com.quantumn.future.model;

import java.util.Date;

public class TradeAuditBo {
    long id;
    String card;
    long amount;
    Date tradeTime;
    long past24hSuccTradeCnt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public long getPast24hSuccTradeCnt() {
        return past24hSuccTradeCnt;
    }

    public void setPast24hSuccTradeCnt(long past24hSuccTradeCnt) {
        this.past24hSuccTradeCnt = past24hSuccTradeCnt;
    }
}
