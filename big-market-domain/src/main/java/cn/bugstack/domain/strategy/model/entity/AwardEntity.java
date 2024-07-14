package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AwardEntity {

    public String userId;

    public Integer awardId;

}
