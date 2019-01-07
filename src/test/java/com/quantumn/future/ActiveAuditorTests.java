package com.quantumn.future;

import com.quantumn.future.auditor.RuleAuditor;
import com.quantumn.future.model.Trade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveAuditorTests {

    @Autowired
    @Qualifier("ActiveRuleAuditor")
    RuleAuditor ruleAuditor;

    @Test
    public void execute() {
        Trade trade1 = new Trade();
        trade1.setId(101L);
        trade1.setCard("622021001131680503");
        trade1.setAmount(2000L);
        trade1.setFlag("F");
        trade1.setTradeTime(new Date());
        ruleAuditor.audit(trade1);

        Trade trade2 = new Trade();
        trade2.setId(102L);
        trade2.setCard("622021001131680505");
        trade2.setAmount(1000L);
        trade2.setTradeTime(new Date());
        trade2.setFlag("F");
        ruleAuditor.audit(trade2);
    }
}
