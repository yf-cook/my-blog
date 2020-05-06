package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.BlogTool;
import com.site.blog.my.core.util.PageQueryUtil;

import java.util.List;

public interface BlogToolMapper {
    int deleteByPrimaryKey(Integer toolId);

    int insert(BlogTool record);

    int insertSelective(BlogTool record);

    BlogTool selectByPrimaryKey(Integer toolId);

    int updateByPrimaryKeySelective(BlogTool record);

    int updateByPrimaryKey(BlogTool record);

    List<BlogTool> findToolList(PageQueryUtil pageUtil);

    int getTotalTools(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

}
