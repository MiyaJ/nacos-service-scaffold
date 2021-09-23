package com.miya.demo.common.enums;

import com.miya.demo.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * 删除状态枚举
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Getter
@AllArgsConstructor
public enum DeleteStatusEnum {

    // 删除状态枚举
    NORMAL(0, "正常"),
    DEL(1, "删除");

    private final Integer code;
    private final String desc;

    private static final Set<DeleteStatusEnum> ALL = EnumSet.allOf(DeleteStatusEnum.class);

    public static DeleteStatusEnum getName(Integer code) {
        if (code == null) {
            return null;
        }

        return ALL.stream()
                .filter(o -> o.code.equals(code))
                .findAny().orElseThrow(() -> new BusinessException("删除状态: " + code + "未匹配到相关值！"));
    }

    public boolean is(Integer code) {
        return getCode().equals(code);
    }
}
