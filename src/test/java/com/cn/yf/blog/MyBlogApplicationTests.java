package com.cn.yf.blog;

import com.cn.yf.blog.util.PageResult;
import com.cn.yf.blog.entity.BlogConfig;
import com.cn.yf.blog.service.BlogService;
import com.cn.yf.blog.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {
	@Autowired
	private BlogService blogService;

	@Autowired
	private TagService tagService;
	@Test
	public void contextLoads() {
		int pageNum=2;
		PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
		int currPage = blogPageResult.getCurrPage();
		int totalPage = blogPageResult.getTotalPage();
		System.out.println("当前页为："+currPage);
		System.out.println("总共页为："+totalPage);
	}

	@Test
	public void text1(){
		int totalTags = tagService.getTotalTags();
		System.out.println("标签总条数为："+totalTags);
	}

	@Test
	public void test2(){
		BlogConfig blogConfig = new BlogConfig();
		blogConfig.setConfigName("娃哈哈");
		blogConfig.setConfigValue("老王来了");
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2008-12-12");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		blogConfig.setCreateTime(date);
		blogConfig.setUpdateTime(new Date());
		System.out.println(blogConfig.toString());
	}



}
