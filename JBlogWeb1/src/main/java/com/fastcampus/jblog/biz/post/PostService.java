package com.fastcampus.jblog.biz.post;

import java.util.List;

import com.fastcampus.jblog.biz.blog.BlogVO;

public interface PostService {

	void insertPost(PostVO vo);

	void updatePost(PostVO vo);

	void deletePost(PostVO vo);

	PostVO getPost(PostVO vo);

	public List<PostVO> getPostList(BlogVO vo);

}