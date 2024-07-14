package cn.bugstack.domain.strategy.service.armory;

/**
 * @description: 策略装配库
 * @author：linzexu
 * @date: 2024/6/23
 */
public interface IStrategyArmory {

    /**
     * 装配抽奖策略配置
     * @param strategyId
     * @return
     */
    boolean assembleLotteryStrategy(Long strategyId);

    /**
     * 获取抽奖策略装配的随机结果
     * @param strategyId
     * @return
     */
    Integer getRandomAwardId(Long strategyId);
}
