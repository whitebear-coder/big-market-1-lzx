package cn.bugstack.domain.strategy_service.rule.filter;

import cn.bugstack.domain.strategy_model.entity.RuleActionEntity;
import cn.bugstack.domain.strategy_model.entity.RuleMatterEntity;

/**
 * @description: 抽奖规则过滤接口
 * @author：linzexu
 * @date: 2024/6/27
 */
public interface ILogicFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);
}
