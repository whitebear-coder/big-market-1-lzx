package cn.bugstack.domain.strategy_service.rule.chain;

import cn.bugstack.domain.strategy_service.rule.chain.factory.DefaultChainFactory;

public interface ILogicChain extends ILogicChainArmory{

    DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);

}
