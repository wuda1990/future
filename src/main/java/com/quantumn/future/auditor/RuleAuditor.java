package com.quantumn.future.auditor;

import com.quantumn.future.model.Trade;

import java.util.Map;

public interface RuleAuditor {
    void initRuleEngine(String kieSessionName);
    Map audit(Trade trade);
}
