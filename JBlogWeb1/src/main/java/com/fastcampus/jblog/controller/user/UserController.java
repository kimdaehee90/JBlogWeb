package com.fastcampus.jblog.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.user.UserService;
import com.fastcampus.jblog.biz.user.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginView.do")
	public String loginView() {
		System.out.println("loginView.do 시도");
		return "bloglogin";
	}
	
	@RequestMapping("/insertUser.do")
	public String insertUser(UserVO vo) {
		userService.insertUser(vo);
		return "/";
	}
	
	@RequestMapping("/login.do")
	public String login(UserVO vo, HttpSession session) {
		UserVO user = userService.getUser(vo);
		if (user != null) {
			// 로그인 성공한 경우 세션에 사용자 정보를 저장한다.
			session.setAttribute("user", user);
			System.out.println("login.do :" + user);
		}
		return "forward:/getBlog.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
