package cn.bugstack.domain.activity.service;

import cn.bugstack.domain.activity.model.valobj.ActivitySkuStockKeyVO;

public interface IRaffleActivitySkuStockService {

    ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 清空队列
     */
    void clearQueueValue();
    /**
     * 延迟队列
     */
    void updateActivitySkuStock(Long sku);
    /**
     * 缓存库存以消耗完毕，清空数据库库存
     */
    void clearActvitySkuStock(Long sku);
}
