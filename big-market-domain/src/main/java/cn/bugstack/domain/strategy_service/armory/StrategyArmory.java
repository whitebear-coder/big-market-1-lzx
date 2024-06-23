package cn.bugstack.domain.strategy_service.armory;

import cn.bugstack.domain.strategy_model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy_respository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/23
 */
@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory{

    @Resource
    private IStrategyRepository respository;

    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        //查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities = respository.queryStrategyAwardList(strategyId);

        //获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        //获取概率值总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //用1 % 0.0001获得概率范围 向上取整
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        //生成策略奖品概率查找表
        List<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());

        for(StrategyAwardEntity strategyAward:strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            //计算每个概率值需要存放到查找表的数量，循环填充
            for(int i=0;i<rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue();i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }
        //对存储的奖品乱序操作
        Collections.shuffle(strategyAwardSearchRateTables);
        //生成Map集合
        Map<Integer, Integer> shuffleStrategyAwardSearchRateTable = new LinkedHashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardSearchRateTable.put(i, strategyAwardSearchRateTables.get(i));
        }

        //存放到Redis
        respository.storeStrategyAwardSearchRateTable(strategyId, shuffleStrategyAwardSearchRateTable.size(), shuffleStrategyAwardSearchRateTable);

        return true;
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = respository.getRateRange(strategyId);
        return respository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
