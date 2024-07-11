package cn.bugstack.domain.strategy_service.rule.chain.impl;

import cn.bugstack.domain.strategy_service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy_service.rule.chain.AbstractLogicChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {

    @Resource
    protected IStrategyDispatch strategyDispatch;

    @Override
    protected String ruleModel() {
        return "default";
    }

    @Override
    public Integer logic(String userId, Long strategyId) {
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        log.info("抽奖责任链-默认处理 userId:{}, strategyId:{}, awardId:{}", userId, strategyId, awardId);

        return awardId;
    }
}
