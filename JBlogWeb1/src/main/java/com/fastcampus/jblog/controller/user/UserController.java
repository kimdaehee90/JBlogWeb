package com.fastcampus.jblog.controller.user;

import javax.servlet.http.HttpSession;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.user.UserService;
import com.fastcampus.jblog.biz.user.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;
	
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
	
//	@RequestMapping("/login.do")
//	public String login(UserVO userVO, BlogVO blogVO, HttpSession session) {
//		UserVO user = userService.getUser(userVO);
//		if (user != null) {
//			// 로그인 성공한 경우 세션에 사용자 정보를 저장한다.
//			session.setAttribute("user", user);
//			System.out.println("login.do :" + user);
//
//			blogVO.setBlogId(user.getUserId());
//			BlogVO blog = blogService.getBlog(blogVO);
//			if(blog != null) {
//				session.setAttribute("blog", blog);
//				System.out.println("login.do : " + blogService.getBlog(blogVO));
//			}
//		}
//		return "redirect:/";
//	}
	@RequestMapping("/login.do")
	public String login(UserVO userVO, BlogVO blogVO, HttpSession session) {
		// 로그인 인증 처리
		UserVO user = userService.getUser(userVO);
		if (user != null) {
			// 로그인 성공 시에 세션에 사용자 정보를 등록한다.
			session.setAttribute("user", user);

			// 2. 방금 등록한 블로그를 검색하여 인덱스 페이지에서
			// 블로그 유무를 판단할 수 있도록 세션에 등록한다.
			blogVO.setBlogId(user.getUserId());
			BlogVO blog = blogService.getBlog(blogVO);
			session.setAttribute("blog", blog);
			System.out.println("login.do : blogService.getBlog(blogVO) : " + blogService.getBlog(blogVO));
		}

		// 인덱스 페이지로 이동한다.
		return "forward:/";
	}
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
