package com.quantumn.future.auditor;

import com.quantumn.future.model.Trade;
import com.quantumn.future.model.TradeAuditBo;

import java.util.Map;

public interface RuleAuditor {
    /**
     * 引擎初始化
     */
    void initRuleEngine();

    /**
     * 引擎审核
     * @param tradeAuditBo
     * @return
     */
    Map audit(TradeAuditBo tradeAuditBo);

    /**
     * 引擎审核,stream mode
     * @param trade
     */
    void audit(Trade trade);
}
