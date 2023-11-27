package com.ruoyi.framework.interceptor.impl;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class ExecutorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            //插入操作时，自动插入env
            System.out.println("------insert------");
            Field fieldCreate = ReflectionUtils.findField(object.getClass(), "createTime");
            fieldCreate.setAccessible(true);
            fieldCreate.set(object, new Date());
        }else{
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                System.out.println("------update------");
                //update时，自动更新updated_at
                Field fieldUpdate = ReflectionUtils.findField(object.getClass(), "updateTime");
                fieldUpdate.setAccessible(true);
                fieldUpdate.set(object, new Date());
            }
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 在这里配置拦截器的属性
    }
}

