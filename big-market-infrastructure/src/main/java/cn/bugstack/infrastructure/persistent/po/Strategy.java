package cn.bugstack.infrastructure.persistent.po;

import java.util.Date;

/**
 * @description: 抽奖策略
 * @author：linzexu
 * @date: 2024/6/16
 */
public class Strategy {
    /*
    自增ID
     */
    private Long id;
    /*
    抽奖策略ID
     */
    private Integer strategyId;
    /*
    抽奖策略描述
     */
    private String strategyDesc;
    /*
    创建时间
     */
    private Date createTime;
    /*
    更新时间
     */
    private Date updateTime;
}
