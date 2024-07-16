package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RaffleAwardEntity {

    /** 奖品顺序号 **/
    private Integer awardId;

    /** 奖品配置信息 **/
    private String awardConfig;

    /** 奖品顺序号 */
    private Integer sort;

}
