package cn.bugstack.domain.strategy_service.rule.tree.factory.engine;

import cn.bugstack.domain.strategy_service.rule.tree.factory.DefaultTreeFactory;

public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);
}
