package com.quantumn.future.service;

import com.quantumn.future.model.Trade;

public interface RuleAuditor {
    void audit(Trade trade);
}
