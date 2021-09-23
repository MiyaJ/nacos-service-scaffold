package com.miya.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

/**
    * 客户表
    */
@Data
@TableName(value = "t_customer")
public class Customer implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 删除标识: 0-未删除; 1-已删除
     */
    @TableField(value = "delete_status", jdbcType = JdbcType.TINYINT, fill = FieldFill.INSERT)
    private Boolean deleteStatus;

    /**
     * 创建人
     */
    @TableField(value = "create_by", jdbcType = JdbcType.INTEGER, fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", jdbcType = JdbcType.DATE, fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by", jdbcType = JdbcType.INTEGER, fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", jdbcType = JdbcType.DATE, fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_EMAIL = "email";

    public static final String COL_DELETE_STATUS = "delete_status";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}