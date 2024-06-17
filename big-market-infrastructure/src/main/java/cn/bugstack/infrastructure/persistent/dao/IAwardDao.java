package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/16
 */
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
