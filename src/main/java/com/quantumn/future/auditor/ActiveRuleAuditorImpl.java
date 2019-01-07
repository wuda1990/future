package com.quantumn.future.auditor;

import com.quantumn.future.model.Trade;
import com.quantumn.future.model.TradeAuditBo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service("ActiveRuleAuditor")
public class ActiveRuleAuditorImpl implements RuleAuditor {
    private static final Logger logger = LogManager.getLogger(RuleAuditorImpl.class);
    KieSession kieSession;
    @PostConstruct
    @Override
    public void initRuleEngine() {
        KieContainer kieContainer = KieContainerFactory.getKieContainer();
        kieSession = kieContainer.newKieSession("trade-rules");
        new Thread(
                ()->{
                    kieSession.fireUntilHalt();
                }
        ).start();
    }

    @Override
    public Map audit(TradeAuditBo tradeAuditBo) {
        return null;
    }

    @Override
    public void audit(Trade trade) {
        logger.info("insert trade to the kieSession:"+trade.toString());
        kieSession.insert(trade);
    }

    @Override
    public void shutdown() {
        kieSession.halt();
        kieSession.dispose();
    }
}
