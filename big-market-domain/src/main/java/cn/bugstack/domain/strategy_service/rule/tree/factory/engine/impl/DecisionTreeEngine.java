package cn.bugstack.domain.strategy_service.rule.tree.factory.engine.impl;


import cn.bugstack.domain.strategy_model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy_model.valobj.RuleTreeNodeLineVO;
import cn.bugstack.domain.strategy_model.valobj.RuleTreeNodeVO;
import cn.bugstack.domain.strategy_model.valobj.RuleTreeVO;
import cn.bugstack.domain.strategy_service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy_service.rule.tree.factory.DefaultTreeFactory;
import cn.bugstack.domain.strategy_service.rule.tree.factory.engine.IDecisionTreeEngine;

import java.util.List;
import java.util.Map;

/**
 * 决策树引擎
 */
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId) {
        DefaultTreeFactory.StrategyAwardData strategyAwardData = null;
        
        //获取基础信息
        String nextNode = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();
        RuleTreeNodeVO ruleTreeNode = treeNodeMap.get(nextNode);
        while(null!=nextNode){
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNode.getRuleKey());
            DefaultTreeFactory.TreeActionActivity logicEntity = logicTreeNode.logic(userId, strategyId, awardId);

            RuleLogicCheckTypeVO ruleLogicCheckTypeVO = logicEntity.getRuleLogicCheckType();
            strategyAwardData = logicEntity.getStrategyAwardData();

            nextNode = nextNode(ruleLogicCheckTypeVO.getCode(), ruleTreeNode.getTreeNodeLineVOList());
            ruleTreeNode = treeNodeMap.get(nextNode);


        }
        return strategyAwardData;
    }

    private String nextNode(String matterValue, List<RuleTreeNodeLineVO> ruleTreeNodeLineVOList){
        if(null == ruleTreeNodeLineVOList || ruleTreeNodeLineVOList.isEmpty()) return null;

        for(RuleTreeNodeLineVO nodeLine : ruleTreeNodeLineVOList){
            if(decisionLogic(matterValue, nodeLine)){
                return nodeLine.getRuleNodeTo();
            }
        }
        return "false";
    }

    public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine){
        switch (nodeLine.getRuleLimitType()){
            case EQUAL:
                return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
            // 以下规则暂时不需要实现
            case GT:
            case LT:
            case GE:
            case LE:
            default:
                return false;

        }
    }
}
