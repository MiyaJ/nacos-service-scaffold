package com.miya.demo.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.miya.demo.common.constant.CommonConstant;
import com.miya.demo.common.constant.PersistenceConstants;
import com.miya.demo.common.util.UserSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis通用字段处理类
 *
 * @author Toby Xue
 */
@Component
@Slf4j
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime createTime = LocalDateTime.now();
        log.info("createTime：" + DateUtil.format(createTime, CommonConstant.DATE_FORMAT_FULL));
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_CREATE_TIME)) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_CREATE_TIME, createTime, metaObject);
        }
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_CREATE_BY) && UserSecurityContextHolder.exists()) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_CREATE_BY,
                    UserSecurityContextHolder.getLoginUser().getId(), metaObject);
        }
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_UPDATE_TIME)) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_UPDATE_TIME, createTime, metaObject);
        }
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_UPDATE_BY) && UserSecurityContextHolder.exists()) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_UPDATE_BY,
                    UserSecurityContextHolder.getLoginUser().getId(), metaObject);
        }
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_DELETED)) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_DELETED, Boolean.FALSE, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime updateTime = LocalDateTime.now();
        log.info("updateTime now：" + DateUtil.format(updateTime, CommonConstant.DATE_FORMAT_FULL));
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_UPDATE_TIME)) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_UPDATE_TIME, updateTime, metaObject);
        }
        if (metaObject.hasSetter(PersistenceConstants.PROPERTY_UPDATE_BY) && UserSecurityContextHolder.exists()) {
            this.setFieldValByName(PersistenceConstants.PROPERTY_UPDATE_BY, UserSecurityContextHolder.getLoginUser().getId(), metaObject);
        }
    }
}