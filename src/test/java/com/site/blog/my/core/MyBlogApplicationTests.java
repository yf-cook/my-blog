package com.site.blog.my.core;

import com.site.blog.my.core.service.BlogService;
import com.site.blog.my.core.util.PageResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {
	@Autowired
	private BlogService blogService;
	@Test
	public void contextLoads() {
		int pageNum=2;
		PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
		int currPage = blogPageResult.getCurrPage();
		int totalPage = blogPageResult.getTotalPage();
		System.out.println("当前页为："+currPage);
		System.out.println("总共页为："+totalPage);
	}

}
