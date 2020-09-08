package com.quantumn.future;

import com.quantumn.future.model.Trade;
import com.quantumn.future.model.TradeAuditBo;
import org.drools.core.RuleBaseConfiguration;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Date;

public class KieTest {

    public static void main(String[] args) {
        System.out.println("rule engine start...");
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
            kieModuleModel.newKieBaseModel("simpleRule");
            KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
            String rule1 = "package com.faster.fasterboot\n" +
                    "\n" +
                    "import com.quantumn.future.model.TradeAuditBo\n" +
                    "\n" +
                    "rule \"txnaudit\"\n" +
                    "    no-loop true\n" +
                    "    when\n" +
                    "        bo : TradeAuditBo(amount>5)\n" +
                    "    then\n" +
                    "        System.out.println(\"hit rule: amount>5\");\n" +
                    "end\n";

            kieFileSystem.write("src/main/resources/trade0429.drl",rule1);
            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
            kieBuilder.buildAll();
            Results results = kieBuilder.getResults();
            if (results.hasMessages(Message.Level.ERROR)) {
                throw new RuntimeException(results.getMessages().toString());
            }else{
                KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
                KieBaseConfiguration config = new RuleBaseConfiguration();
                KieBase kieBase = kieContainer.newKieBase(config);
                TradeAuditBo tradeAuditBo = new TradeAuditBo(1, "123", 29L, new Date());
                KieSession kieSession = kieBase.newKieSession();
                kieSession.insert(tradeAuditBo);
                kieSession.fireAllRules();
                kieSession.halt();
                kieSession.dispose();

            }

        } catch (Exception e) {
            System.out.println("rule engine exception");
        }
        System.out.println("rule engine end...");
    }

}
