package com.quantumn.future;
/**
 * 这是为了未来
 */

import com.quantumn.future.auditor.RuleAuditor;
import com.quantumn.future.model.Trade;
import com.quantumn.future.model.TradeAuditBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FutureApplicationTests {

    @Qualifier("RuleAuditor")
    @Autowired
    RuleAuditor ruleAuditor;

    @Test
    public void contextLoads() {
    }

    @Test
    public void simpleExecuteRule() {
        TradeAuditBo tradeAuditBo = new TradeAuditBo();
        tradeAuditBo.setPast24hSuccTradeCnt(6);
        ruleAuditor.audit(tradeAuditBo);
    }

    @Test
    public void executeRule() {
        Trade trade = new Trade();
        trade.setId(101L);
        trade.setCard("622021001131680503");
        trade.setAmount(2000L);
        trade.setTradeTime(new Date());
        trade.setFlag("F");
        ruleAuditor.audit(trade);
        Trade trade1 = new Trade();
        trade1.setId(102L);
        trade1.setCard("622021001131680505");
        trade1.setAmount(1000L);
        trade1.setTradeTime(new Date());
        trade1.setFlag("S");
        ruleAuditor.audit(trade1);
    }


}

