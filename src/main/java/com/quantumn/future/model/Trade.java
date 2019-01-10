package com.quantumn.future.model;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.util.Date;

@Role(Role.Type.EVENT)
//@Timestamp("tradeTime")
@Expires("1h")
public class Trade {
    long id;
    String card;
    long amount;
    Date tradeTime;
    String flag;

    public Trade() {
    }

    /**
     *
     * @param id
     * @param card
     * @param amount
     * @param tradeTime
     * @param flag
     */
    public Trade(long id, String card, long amount, Date tradeTime, String flag) {
        this.id = id;
        this.card = card;
        this.amount = amount;
        this.tradeTime = tradeTime;
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", card='" + card + '\'' +
                ", amount=" + amount +
                ", tradeTime=" + tradeTime +
                ", flag='" + flag + '\'' +
                '}';
    }
}
