package com.miya.demo.model.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果集
 *
 * @author Caixiaowei
 * @date 2021/09/22
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总记录数")
    private Long totalCount;

    @ApiModelProperty("每页记录数")
    private Long pageSize;

    @ApiModelProperty("总页数")
    private Long totalPage;

    @ApiModelProperty("当前页数")
    private Long currPage;

    @ApiModelProperty("列表数据")
    private List<T> list;

    public PageResult(Long totalCount, Long pageSize, Long currPage, Long totalPage, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.list = list;
    }

    public static<T> PageResult<T> instance(Page page,List<T> list) {
        PageResult<T> pageResult = new PageResult<T>();
        //当数值过大时会出现缺失问题 对应属性需要改成long类型
        pageResult.setTotalCount(page.getTotal());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPage(page.getPages());
        pageResult.setCurrPage(page.getCurrent());
        pageResult.setList(list);
        return pageResult;
    }

    public static <T> PageResult<T> buildEmpty(PageRequest req) {
        PageResult<T> pageResult = new PageResult<T>();
        pageResult.setTotalCount(0L);
        pageResult.setPageSize(req.getPageSize());
        pageResult.setTotalPage(0L);
        pageResult.setCurrPage(req.getPageNum());
        pageResult.setList(Collections.emptyList());
        return pageResult;
    }
}
