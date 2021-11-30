package com.fastcampus.jblog.biz.post;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAOMybatis {
	// JDBC 관련 변수
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public PostVO getPost(PostVO vo) {		
		return (PostVO) mybatis.selectOne("PostDAO.getPost", vo);
	}
	
	public List<PostVO> getPostList(PostVO vo) {	
		return mybatis.selectList("PostDAO.getPostList", vo);
	}
	
	public void insertPost(PostVO vo) {		
		mybatis.insert("PostDAO.insertPost", vo);
	}
	
	public void deletePost(PostVO vo) {
		mybatis.delete("PostDAO.deletePost", vo);
	}

	public void updatePost(PostVO vo) {		
		mybatis.update("PostDAO.updatePost", vo);
	}	
}
