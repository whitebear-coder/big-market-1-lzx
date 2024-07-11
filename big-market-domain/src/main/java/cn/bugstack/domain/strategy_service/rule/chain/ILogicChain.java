package cn.bugstack.domain.strategy_service.rule.chain;

public interface ILogicChain extends ILogicChainArmory{

    Integer logic(String userId, Long strategyId);
}
