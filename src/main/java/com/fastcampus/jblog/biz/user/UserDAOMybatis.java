package com.fastcampus.jblog.biz.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public UserVO getUser(UserVO vo) {
		System.out.println("---> 마이바티스 기반으로 로그인 처리");
		return (UserVO) mybatis.selectOne("UserDAO.getUser", vo);
	}
}

