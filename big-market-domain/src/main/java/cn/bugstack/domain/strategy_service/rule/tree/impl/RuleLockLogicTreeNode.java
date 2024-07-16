package cn.bugstack.domain.strategy_service.rule.tree.impl;

import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy_service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy_service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 次数锁节点
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    @Override
    public DefaultTreeFactory.TreeActionActivity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionActivity.builder().
                ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }

}
