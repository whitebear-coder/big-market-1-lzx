package cn.bugstack.domain.strategy.service;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

public interface IRaffleAward {

    /**
     * 根据策略ID查询抽奖奖品列表配置
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategy);


}
