package com.ruoyi.pill.test;

import com.dtflys.forest.http.ForestResponse;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pill.domain.PillFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestHttp
 * @Description TODO
 * @Author JiangXiong
 * @Date 2023/7/18 15:04
 * @Version 1.0
 */

@RestController
@RequestMapping("/test/http")
public class TestHttp extends BaseController {

    @Autowired
    CallTicketCreation callTicketCreation;

    /**
     * 获取tokens
     */
    @GetMapping("/getTokens")
    public AjaxResult getTokens()
    {
        ForestResponse<String> getTokenResponse = callTicketCreation.getTokens2();
        System.out.println(getTokenResponse.isSuccess());
        System.out.println(getTokenResponse.getResult());
        return AjaxResult.success(getTokenResponse.getResult());
    }

    /**
     * 创建工单接口
     */
    @PostMapping("/create")
    public AjaxResult create()
    {
        ForestResponse<String> getTokenResponse = callTicketCreation.getTokens2();
        Map<String,Object> jsonBody = new HashMap<>();
        Map<String,Object> entities = new HashMap<>();
        entities.put("entity_type", "Incident");
        entities.put("properties", new Ticket());
        List<Map<String,Object>> entityList = new ArrayList<>();
        entityList.add(entities);
        jsonBody.put("entities", entityList);
        jsonBody.put("operation", "CREATE");
        ForestResponse<String> response = callTicketCreation.sendTickets(getTokenResponse.getResult(), jsonBody);
        System.out.println(response.isSuccess());
        System.out.println(response.getContent());
        return AjaxResult.success(response.isSuccess());
    }

    /**
     * 获取tokens2
     */
    @GetMapping("/getTokens2")
    public AjaxResult getTokens2()
    {
        ForestResponse<String> getTokenResponse = callTicketCreation.getTokens2();
        System.out.println("22222" + getTokenResponse.isSuccess());
        System.out.println("22222" + getTokenResponse.getResult());
        return AjaxResult.success(getTokenResponse.getResult());
    }

    /**
     * 创建工单接口2
     */
    @PostMapping("/create2")
    public AjaxResult create2()
    {
        ForestResponse<String> getTokenResponse = callTicketCreation.getTokens2();
        Map<String,Object> jsonBody = new HashMap<>();
        Map<String,Object> entities = new HashMap<>();
        entities.put("entity_type", "Incident");
        entities.put("properties", new Ticket());
        List<Map<String,Object>> entityList = new ArrayList<>();
        entityList.add(entities);
        jsonBody.put("entities", entityList);
        jsonBody.put("operation", "CREATE");
        ForestResponse<String> response = callTicketCreation.createTickets(getTokenResponse.getResult(), jsonBody);
        System.out.println("创建工单接口2返回：" + response.isSuccess());
        return AjaxResult.success(response.isSuccess());
    }
}
