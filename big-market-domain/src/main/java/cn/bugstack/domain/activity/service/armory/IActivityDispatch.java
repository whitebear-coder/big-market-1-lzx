package cn.bugstack.domain.activity.service.armory;

import java.util.Date;

public interface IActivityDispatch {
    /**
     * 根据策略ID和奖品ID，扣减奖品缓存库存
     * @param sku
     * @param endDateTime
     * @return
     */
    boolean subtractionActivitySkuStock(Long sku, Date endDateTime);
}
