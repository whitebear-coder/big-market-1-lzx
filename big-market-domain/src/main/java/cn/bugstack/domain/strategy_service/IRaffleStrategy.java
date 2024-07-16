package cn.bugstack.domain.strategy_service;

import cn.bugstack.domain.strategy_model.entity.RaffleAwardEntity;
import cn.bugstack.domain.strategy_model.entity.RaffleFactorEntity;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/27
 */
public interface IRaffleStrategy {

    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleEntity);
}
