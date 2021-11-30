package com.fastcampus.jblog.biz.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

}
