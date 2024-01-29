package com.ruoyi.pill.controller;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.config.SaCookieConfig;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaCookie;
import cn.dev33.satoken.context.model.SaRequest;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.core.controller.BaseController;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.ruoyi.common.exception.base.BaseException;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试Controller
 *
 * @author jiangxiong
 * @date 2024-01-25
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    /**
     * 测试统一认证4A平台免密登录接口
     */
    @GetMapping("/loginByUsername")
    public Object logintByUsername(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        // todo 获取code，根据code获取token，再根据token获取用户名
        // 通过用户名进行免密登录，获取4A平台token
        String res = "";
        // 所有权限
        // String username = "admin";
        // 部分权限
        // String username = "12002071";
        // 已停用的用户
        String username = "liuquanhui";
        try {
            HttpRequest httpRequest = HttpUtil.createPost("http://192.168.232.185:9090/dev-api/loginByUsername")
                    .header("Content-Type", "application/json;charset=UTF-8")  // 使用 JSON 格式
                    .body("{\"username\": \"" + username + "\"}"); // 构建JSON数据格式
            HttpResponse httpResponse = httpRequest.execute();
            res = httpResponse.body();
        } catch (Exception e){
            return error("登录失败，对内免密登录接口调用异常：" + e.getMessage());
        }
        // 获取返回code
        Integer code = (Integer) JSONUtil.getByPath(JSONUtil.parse(res), "code");
        if(!ObjectUtil.equal(code, 200)){
            return error((String) JSONUtil.getByPath(JSONUtil.parse(res), "msg"));
        }
        // 获取token
        String token = (String) JSONUtil.getByPath(JSONUtil.parse(res), "token");
        // token存在cookie中
        setTokenValueToCookie("Admin-Token", token,14400);
        setTokenValueToCookie("userName", username, 14400);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("token", token);
        // 重定向到4A平台首页
        // return new RedirectView("http://192.168.232.185/apply/index");
        // 重定向到特定的路由（该路由会自动重定向到4A平台首页）
        return new RedirectView("http://192.168.160.149/autologin?token=" + token);
    }

    public void setTokenValueToCookie(String key, String value, int cookieTimeout) {
        SaCookieConfig cfg = SaManager.getConfig().getCookie();
        SaCookie cookie = new SaCookie()
                .setName(key)
                .setValue(value)
                .setMaxAge(cookieTimeout)
                .setDomain(cfg.getDomain())
                .setPath(cfg.getPath());
        SaHolder.getResponse().addCookie(cookie);
    }

}