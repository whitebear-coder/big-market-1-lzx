package cn.bugstack.domain.strategy_service.rule.tree;

import cn.bugstack.domain.strategy_service.rule.tree.factory.DefaultTreeFactory;

/**
 * 规则树接口
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionActivity logic(String userId, Long strategyId, Integer awardId);
}
