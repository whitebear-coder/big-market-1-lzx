package cn.bugstack.domain.strategy_service.rule.tree.impl;

import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy_service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy_service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 幸运奖
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {

    @Override
    public DefaultTreeFactory.TreeActionActivity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionActivity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardData(DefaultTreeFactory.StrategyAwardData.builder()
                        .awardId(101)
                        .awardRuleValue("1, 100")
                        .build())
                .build();
    }
}
