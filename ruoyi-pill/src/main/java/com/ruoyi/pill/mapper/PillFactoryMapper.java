package com.ruoyi.pill.mapper;

import com.ruoyi.pill.domain.PillFactory;

import java.util.List;

/**
 * @ClassName PillFactoryMapper
 * @Description 厂商信息数据层
 * @Author JiangXiong
 * @Date 2023/7/3 10:06
 * @Version 1.0
 */
public interface PillFactoryMapper {

    /**
     * 查询厂商数据集合
     *
     * @param factory 厂商信息
     * @return 厂商数据集合
     */
    public List<PillFactory> selectPillFactoryList(PillFactory factory);
}
