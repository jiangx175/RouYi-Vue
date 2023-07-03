package com.ruoyi.pill.service;

import com.ruoyi.pill.domain.PillFactory;

import java.util.List;

/**
 * @ClassName IPillFactoryService
 * @Description TODO
 * @Author JiangXiong
 * @Date 2023/7/3 10:07
 * @Version 1.0
 */
public interface IPillFactoryService {

    /**
     * 查询厂商信息集合
     *
     * @param factory 厂商信息
     * @return 厂商列表
     */
    public List<PillFactory> selectPillFactoryList(PillFactory factory);
}
