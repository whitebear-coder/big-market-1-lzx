package cn.bugstack.domain.strategy_service.rule.tree.factory;

import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy_model.valobj.RuleTreeVO;
import cn.bugstack.domain.strategy_service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy_service.rule.tree.factory.engine.IDecisionTreeEngine;
import cn.bugstack.domain.strategy_service.rule.tree.factory.engine.impl.DecisionTreeEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 规则树工厂
 */
@Service
public class DefaultTreeFactory {

    @Resource
    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup){
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO){
        return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
    }

    /**
     *
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeActionActivity{
        private RuleLogicCheckTypeVO ruleLogicCheckType;
        private StrategyAwardData strategyAwardData;

    }
    /**
     * 工厂提供返回对象
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StrategyAwardData{

        private Integer awardId;

        private String awardRuleValue;
    }
}
