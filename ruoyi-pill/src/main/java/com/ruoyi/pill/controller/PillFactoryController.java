package com.ruoyi.pill.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pill.domain.PillFactory;
import com.ruoyi.pill.service.IPillFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @ClassName PillFactoryController
 * @Description TODO
 * @Author JiangXiong
 * @Date 2023/7/3 10:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/pill/factory")
public class PillFactoryController extends BaseController {

    @Autowired
    private IPillFactoryService factoryService;

    /**
     * 获取厂商列表
     */
//    @PreAuthorize("@ss.hasPermi('pill:factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(PillFactory pillFactory)
    {
        startPage();
        List<PillFactory> list = factoryService.selectPillFactoryList(pillFactory);
        return getDataTable(list);
    }
}
