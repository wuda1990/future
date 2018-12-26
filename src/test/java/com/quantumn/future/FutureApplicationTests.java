package com.quantumn.future;

import com.quantumn.future.auditor.RuleAuditor;
import com.quantumn.future.model.Trade;
import com.quantumn.future.model.TradeAuditBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FutureApplicationTests {

    @Autowired
    RuleAuditor ruleAuditor;

    @Test
    public void contextLoads() {
    }

    @Test
    public void executeRule() {
        ruleAuditor.initRuleEngine();
        Trade trade = new Trade();
        trade.setId(101L);
        trade.setCard("622021001131680503");
        trade.setAmount(2000L);
        trade.setTradeTime(new Date());
        TradeAuditBo tradeAuditBo = new TradeAuditBo();
        tradeAuditBo.setPast24hSuccTradeCnt(6);
        ruleAuditor.audit(tradeAuditBo);
    }

}

