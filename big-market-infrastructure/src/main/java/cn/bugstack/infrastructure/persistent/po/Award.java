package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @description: 奖品描述
 * @author：linzexu
 * @date: 2024/6/16
 */
@Data
public class Award {
    /*
    自增ID
     */
    private Long id;
    /*
    抽奖ID
     */
    private Integer awardId;
    /*
    奖品对接标识-奖品对应的发奖策略
     */
    private String awardKey;
    /*
    奖品配置信息
     */
    private String awardConfig;
    /*
    奖品内容描述
     */
    private String awardDesc;
    /*
    创建时间
     */
    private Date createTime;
    /*
    更新时间
     */
    private Date updateTime;
}
