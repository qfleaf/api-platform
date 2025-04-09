package com.qfleaf.yunapi.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyBatisPlusAuditableHandler implements MetaObjectHandler {
    // 插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入数据时自动填充...");
        this.strictInsertFill(metaObject, "createdAt", Date.class, new Date());
        this.strictInsertFill(metaObject, "updatedAt", Date.class, new Date());
    }

    // 更新时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新数据时自动填充...");
        this.strictUpdateFill(metaObject, "updatedAt", Date.class, new Date());
    }
}