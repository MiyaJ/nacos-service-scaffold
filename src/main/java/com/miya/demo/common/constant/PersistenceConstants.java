package com.miya.demo.common.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;


/**
 * 持久性常量
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PersistenceConstants {

    public static final String PROPERTY_CREATE_TIME = "createTime";
    public static final String PROPERTY_CREATE_BY = "createBy";
    public static final String PROPERTY_UPDATE_TIME = "updateTime";
    public static final String PROPERTY_UPDATE_BY = "updateBy";
    public static final String PROPERTY_DELETED = "deleteStatus";

}
