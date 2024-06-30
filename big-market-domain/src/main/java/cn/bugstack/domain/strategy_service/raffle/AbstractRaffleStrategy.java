package cn.bugstack.domain.strategy_service.raffle;

import cn.bugstack.domain.strategy_model.entity.RaffleAwardEntity;
import cn.bugstack.domain.strategy_model.entity.RaffleFactorEntity;
import cn.bugstack.domain.strategy_model.entity.RuleActionEntity;
import cn.bugstack.domain.strategy_model.entity.StrategyEntity;
import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy_model.valobj.StrategyAwardRuleModelVO;
import cn.bugstack.domain.strategy_repository.IStrategyRepository;
import cn.bugstack.domain.strategy_service.IRaffleStrategy;
import cn.bugstack.domain.strategy_service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy_service.rule.factory.DefaultLogicFactory;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/27
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    @Resource
    protected IStrategyRepository repository;

    @Resource
    protected IStrategyDispatch strategyDispatch;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch){
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleEntity) {
        String userId = raffleEntity.getUserId();
        Long strategyId = raffleEntity.getStrategyId();
        if(strategyId == null || StringUtils.isBlank(userId)){
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        //策略查询
        StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);

        //抽奖前规则过滤
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = this.doCheckRaffleBeforeLogic(RaffleFactorEntity.builder().userId(userId).strategyId(strategyId).build(), strategy.ruleModels());
        if(RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionEntity.getCode())){
            if(DefaultLogicFactory.LogicModel.RULE_BLACKLIST.getCode().equals(ruleActionEntity.getRuleModel())){
                //黑名单返回固定的奖品ID
                return RaffleAwardEntity.builder().awardId(ruleActionEntity.getData().getAwardId()).build();
            }else if(DefaultLogicFactory.LogicModel.RULE_WIGHT.getCode().equals(ruleActionEntity.getRuleModel())){
                RuleActionEntity.RaffleBeforeEntity raffleBeforeEntity = ruleActionEntity.getData();
                String ruleWeightValueKey = raffleBeforeEntity.getRuleWeightValueKey();
                Integer award = strategyDispatch.getRandomAwardId(strategyId, ruleWeightValueKey);
                return RaffleAwardEntity.builder().awardId(award).build();
            }
        }

        //4. 默认抽奖流程
        Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
        //5.过滤抽奖规则
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);
        //6. 抽奖中，规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId)
                .build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());

        if (RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(ruleActionCenterEntity.getCode())){
            log.info("【临时日志】中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。");
            return RaffleAwardEntity.builder()
                    .awardDesc("中奖中规则拦截，通过抽奖后规则 rule_luck_award 走兜底奖励。")
                    .build();
        }

        return RaffleAwardEntity.builder().awardId(awardId).build();

    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);
}
