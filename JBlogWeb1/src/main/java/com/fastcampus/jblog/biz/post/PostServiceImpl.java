package com.fastcampus.jblog.biz.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.jblog.biz.blog.BlogVO;
@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDAO postDAO;
	@Override
	public void insertPost(PostVO vo) {
		postDAO.insertPost(vo);

	}

	@Override
	public void updatePost(PostVO vo) {
		postDAO.updatePost(vo);

	}

	@Override
	public void deletePost(PostVO vo) {
		postDAO.deletePost(vo);

	}

	@Override
	public PostVO getPost(PostVO vo) {
		return postDAO.getPost(vo);
	}

	
	public List<PostVO> getPostList(BlogVO vo){
		return postDAO.getPostList(vo);
	}

}
