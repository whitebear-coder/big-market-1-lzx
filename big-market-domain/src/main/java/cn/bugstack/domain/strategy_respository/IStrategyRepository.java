package cn.bugstack.domain.strategy_respository;

import cn.bugstack.domain.strategy_model.entity.StrategyAwardEntity;

import java.util.List;
import java.util.Map;

/**
 * @description: 策略服务仓储接口
 * @author：linzexu
 * @date: 2024/6/23
 */
public interface IStrategyRepository {

    /**
     * 获取策略对应奖品实体
     * @param strategyId
     * @return
     */
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    /**
     * 存储表
     * @param strategyId
     * @param rateRange
     * @param strategyAwardSearchRateTable
     */
    void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<Integer, Integer> strategyAwardSearchRateTable);

    /**
     * 查找对应奖品信息
     * @param strategyId
     * @param rateKey
     * @return
     */
    Integer getStrategyAwardAssemble(Long strategyId, Integer rateKey);

    int getRateRange(Long strategyId);
}
