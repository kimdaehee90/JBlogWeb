package com.fastcampus.jblog.biz.blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

	void insertBlog(BlogVO vo);

	BlogVO getBlog(BlogVO vo);
	
	List<Map<String, String>> getBlogList(BlogVO vo);
	
	public void deleteblog(BlogVO vo);
	
	public void updateBlog(BlogVO vo);
}