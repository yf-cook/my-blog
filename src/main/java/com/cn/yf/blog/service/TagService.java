package com.cn.yf.blog.service;

import com.cn.yf.blog.util.PageQueryUtil;
import com.cn.yf.blog.util.PageResult;
import com.cn.yf.blog.entity.BlogTagCount;

import java.util.List;

public interface TagService {

    /**
     * 查询标签的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    int getTotalTags();

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<BlogTagCount> getBlogTagCountForIndex();
}
