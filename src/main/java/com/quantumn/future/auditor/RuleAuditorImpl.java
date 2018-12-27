package com.quantumn.future.auditor;

import com.quantumn.future.model.Trade;
import com.quantumn.future.model.TradeAuditBo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drools.compiler.kie.builder.impl.KieContainerImpl;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.command.CommandFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RuleAuditorImpl implements RuleAuditor {
    private static final Logger logger = LogManager.getLogger(RuleAuditorImpl.class);
    private KieContainer kieContainer;
    private Map scoreMap = new ConcurrentHashMap<Long,AtomicLong>();
    KieBase kieBase;

    @PostConstruct
    @Override
    public void initRuleEngine() {
        logger.info("init rule engine start...");
        try {
            kieContainer = KieContainerFactory.getKieContainer();
            KieBaseConfiguration kieBaseConfig = KieServices.get().newKieBaseConfiguration();
            kieBaseConfig.setOption(EventProcessingOption.STREAM);
            kieBase = kieContainer.newKieBase("rules",kieBaseConfig);
        } catch (Exception e) {
            logger.error("init rule engine exception", e);
        }
        logger.info("init rule engine end...");
    }

    public Map audit(TradeAuditBo tradeAuditBo) {
        KieSessionConfiguration kieSessionConfig = KieServices.get().newKieSessionConfiguration();
        kieSessionConfig.setOption(ClockTypeOption.get("pseudo"));
        StatelessKieSession kieSession = kieBase.newStatelessKieSession(kieSessionConfig);

        if (kieSession == null) {
            logger.warn("kieSession is null!");
            return null;
        }
        List cmds = new ArrayList<>();
        cmds.add(CommandFactory.newInsert(tradeAuditBo));
        kieSession.execute(CommandFactory.newBatchExecution(cmds));
        return scoreMap;
    }
}
