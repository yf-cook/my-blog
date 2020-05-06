package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.BlogToolMapper;
import com.site.blog.my.core.entity.BlogTool;
import com.site.blog.my.core.service.ToolService;
import com.site.blog.my.core.util.PageQueryUtil;
import com.site.blog.my.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {
    @Autowired
    BlogToolMapper blogToolMapper;

    @Override
    public PageResult getBlogToolPage(PageQueryUtil pageQueryUtil) {
        //查找该页下的数据
        List<BlogTool> toolList = blogToolMapper.findToolList(pageQueryUtil);
        //查找总共有多少条数据
        int totalTools = blogToolMapper.getTotalTools(pageQueryUtil);
        PageResult pageResult = new PageResult(toolList,totalTools,pageQueryUtil.getLimit(),pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalTools() {
        int totalTools = blogToolMapper.getTotalTools(null);
        return totalTools;
    }

    @Override
    public Boolean saveTool(BlogTool blogTool) {
       return blogToolMapper.insertSelective(blogTool) > 0;
    }

    @Override
    public BlogTool selectById(Integer id) {
        BlogTool blogTool = blogToolMapper.selectByPrimaryKey(id);
        return blogTool;
    }

    @Override
    public Boolean updateTool(BlogTool blogTool) {
        return blogToolMapper.updateByPrimaryKeySelective(blogTool) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return blogToolMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogTool> getBlogToolList() {
        //获取所有的工具数据
        List<BlogTool> toolList = blogToolMapper.findToolList(null);
        return toolList;
    }
}
