package com.ruoyi.framework.interceptor.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

/**
 * mybatis-plus 自定义拦截器
 * @author ml
 * @date 2023-05-15
 */
@Slf4j
//拦截StatementHandler类中参数类型为Statement的prepare方法（prepare=在预编译SQL前加入修改的逻辑）
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {
                Connection.class, Integer.class})})
public class StatementHandlerInterceptor implements org.apache.ibatis.plugin.Interceptor {

    /**
     * 拦截sql
     *
     * @param invocation
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        // 通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性;：MetaObject是Mybatis提供的一个用于方便、
        // 优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
        //实际执行的sql是经过层层封装，无法利用简单的一层反射获取到需要使用提供的快捷方法或者对获取到关键数据进行拼装
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                new DefaultReflectorFactory());

        // 先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        // id为执行的mapper方法的全路径名，如com.cq.UserMapper.insertUser， 便于后续使用反射
        String id = mappedStatement.getId();
        // sql语句类型 select、delete、insert、update
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
        // 数据库连接信息


        BoundSql boundSql = statementHandler.getBoundSql();
        // 获取到原始sql语句
        String sql = boundSql.getSql();
        log.info("SQL：{}", sql);


        // 增强sql
        // 通过反射，拦截方法上带有自定义@SqlPermission，并增强sql
        //离谱的是之前的反射无法生效，不知道为什么这个可以生效有待研究
        // 如果sql语句为insert，增强sql
        String mSql = sql.replace("count(0)", "count(*)");
        // 直接增强sql
        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, mSql);
        log.info("增强后的SQL：{}", mSql); // 打印：增强后的SQL
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return org.apache.ibatis.plugin.Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        org.apache.ibatis.plugin.Interceptor.super.setProperties(properties);
    }
}
