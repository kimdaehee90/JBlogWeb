package com.fastcampus.jblog.biz.blog;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;
	
	
	public void insertBlog(BlogVO vo) {
		blogDAO.insertBlog(vo);

	}

	
	public BlogVO getBlog(BlogVO vo) {
		return blogDAO.getBlog(vo);
	}
	public List<Map<String, String>> getBlogList(BlogVO vo){
		return blogDAO.getBlogList(vo);
	}
	public void deleteblog(BlogVO vo) {
		blogDAO.deleteblog(vo);
	}
	public void updateBlog(BlogVO vo) {
		blogDAO.updateBlog(vo);
	}
}
