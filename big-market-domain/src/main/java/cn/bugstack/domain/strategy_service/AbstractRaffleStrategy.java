package cn.bugstack.domain.strategy_service;

import cn.bugstack.domain.strategy_model.entity.RaffleAwardEntity;
import cn.bugstack.domain.strategy_model.entity.RaffleFactorEntity;
import cn.bugstack.domain.strategy_model.entity.RuleActionEntity;
import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy_model.valobj.StrategyAwardRuleModelVO;
import cn.bugstack.domain.strategy_repository.IStrategyRepository;
import cn.bugstack.domain.strategy_service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy_service.rule.chain.ILogicChain;
import cn.bugstack.domain.strategy_service.rule.chain.factory.DefaultChainFactory;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy{

    //策略仓储服务
    protected IStrategyRepository repository;

    //策略调度服务
    protected IStrategyDispatch strategyDispatch;

    //抽奖责任链
    private final DefaultChainFactory defaultChainFactory;

    public AbstractRaffleStrategy(IStrategyDispatch strategyDispatch, IStrategyRepository repository, DefaultChainFactory defaultChainFactory) {
        this.strategyDispatch = strategyDispatch;
        this.repository = repository;
        this.defaultChainFactory = defaultChainFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleEntity) {
        //参数校验
        String userId = raffleEntity.getUserId();
        Long strategyId = raffleEntity.getStrategyId();

        if(strategyId ==null || StringUtils.isBlank(userId)){
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }
        //获取责任链
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);

        //通过责任链获取奖品Id
        Integer awardId = logicChain.logic(userId, strategyId);

        // 4. 查询奖品规则「抽奖中（拿到奖品ID时，过滤规则）、抽奖后（扣减完奖品库存后过滤，抽奖中拦截和无库存则走兜底）」
        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);

        // 抽奖中，规则过滤
        // 5. 抽奖中 - 规则过滤
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

        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
    }

    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String... logics);


}
