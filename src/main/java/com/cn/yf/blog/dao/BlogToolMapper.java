package com.cn.yf.blog.dao;

import com.cn.yf.blog.entity.BlogTool;
import com.cn.yf.blog.util.PageQueryUtil;

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
