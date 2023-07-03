package com.ruoyi.pill.service.impl;

import com.ruoyi.pill.domain.PillFactory;
import com.ruoyi.pill.mapper.PillFactoryMapper;
import com.ruoyi.pill.service.IPillFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PillFactoryServiceImpl
 * @Description TODO
 * @Author JiangXiong
 * @Date 2023/7/3 10:08
 * @Version 1.0
 */
@Service
public class PillFactoryServiceImpl implements IPillFactoryService {

    @Autowired
    private PillFactoryMapper pillFactoryMapper;

    @Override
    public List<PillFactory> selectPillFactoryList(PillFactory factory) {
        return pillFactoryMapper.selectPillFactoryList(factory);
    }
}
