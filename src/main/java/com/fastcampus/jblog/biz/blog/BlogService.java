package com.fastcampus.jblog.biz.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	private BlogDAO blogDAO;

	public void insertBlog(BlogVO vo) {
		blogDAO.insertBlog(vo);
	}

	public BlogVO getBlog(BlogVO vo) {
		return blogDAO.getBlog(vo);
	}

	public List<BlogVO> getBlogList(BlogVO vo) {
		return blogDAO.getBlogList(vo);
	}

	public void updateBlog(BlogVO vo) {
		blogDAO.updateBlog(vo);
		
	}

	public void blogDeleteRequest(BlogVO vo) {
		blogDAO.blogDeleteRequest(vo);		
	}

	public void deleteBlog(int blogId) {
		blogDAO.deleteBlog(blogId);		
	}

}
