package com.cn.yf.blog.service;

import com.cn.yf.blog.util.PageQueryUtil;
import com.cn.yf.blog.util.PageResult;
import com.cn.yf.blog.entity.BlogTool;

import java.util.List;

public interface ToolService {

    /**
     * 查询工具的分页数据
     * @param pageQueryUtil
     * @return
     */
    PageResult getBlogToolPage(PageQueryUtil pageQueryUtil);

    int getTotalTools();

    Boolean saveTool(BlogTool blogTool);

    BlogTool selectById(Integer id);

    Boolean updateTool(BlogTool blogTool);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTool> getBlogToolList();
}
