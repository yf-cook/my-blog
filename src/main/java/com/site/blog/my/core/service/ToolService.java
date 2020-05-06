package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.BlogTool;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;

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
