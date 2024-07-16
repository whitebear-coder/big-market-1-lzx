package cn.bugstack.domain.strategy_model.entity;

import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import lombok.*;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity>{

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();
    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();
    private String ruleModel;
    private T data;


    static public class RaffleEntity{

    }
    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity{
        /** 策略ID **/
        private Long strategyId;
        /** 权重值key **/
        private String ruleWeightValueKey;
        /** 奖品ID **/
        private Integer awardId;
    }

    //抽奖之中
    static public class RaffleCenterEntity extends RaffleEntity{

    }
    //抽奖之后
    static public class RaffleAfterEntity extends RaffleEntity{

    }
}
