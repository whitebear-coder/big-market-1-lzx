package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {

    public Long strategyId;

    public Integer awardId;

    public Integer awardCount;

    public Integer awardCountSurplus;

    public BigDecimal awardRate;

}
