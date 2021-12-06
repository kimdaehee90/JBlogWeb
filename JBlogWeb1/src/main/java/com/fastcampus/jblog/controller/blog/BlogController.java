package com.fastcampus.jblog.controller.blog;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.user.UserVO;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	// 인덱스 페이지에 대한 요청 처리
	@RequestMapping("/")
	public String index() {
		return "forward:/index.jsp";
	}

	@RequestMapping("/insertBlog.do")
	public String insertBlog(BlogVO blogVO, CategoryVO categoryVO, HttpSession session) {
		BlogVO blog = blogService.getBlog(blogVO);
		session.setAttribute("blog", blog);
//		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setDisplayType("MIXED");
		categoryVO.setCategoryName("미지정");
		categoryVO.setCntDisplayPost(5);
		categoryVO.setDescription("기본 카테고리");
		blogService.insertBlog(blogVO);
		return "forward:/getBlog.do";
	}

	@RequestMapping("/getBlog.do")
	public String getBlog(BlogVO vo,HttpSession session, Model model) {
//		vo = (BlogVO) session.getAttribute("vo");
		model.addAttribute("blog", blogService.getBlog(vo));
		System.out.println("getBlog.do : " + blogService.getBlog(vo));
		return "forward:index.jsp";
	}
	
	@RequestMapping("/updateBlog.do")
	public String updateBlog(BlogVO vo,HttpSession session, Model model) {
		blogService.updateBlog(vo);
		model.addAttribute("blog", blogService.getBlog(vo));
		System.out.println("getBlog.do : " + blogService.getBlog(vo));
		return "blogmain";
	}
	
	@RequestMapping("/getBlogList.do")
	public String getBlogList(BlogVO vo, HttpSession session,Model model) {
//		vo = (BlogVO) session.getAttribute("vo");
//		model.addAttribute("blog", blogService.getBlog(vo));
		model.addAttribute("blogList", blogService.getBlogList(vo));
//		System.out.println("getBlogList.do : " + blogService.getBlog(vo));
		System.out.println("getBlogList.do : " + blogService.getBlogList(vo));
		return "forward:index.jsp";
	}

	@RequestMapping("/createView.do")
	public String createView() {
		return "blogcreate";
	}
	@RequestMapping("/deleteBlog.do")
	public String deleteBlog(BlogVO vo) {
		blogService.deleteblog(vo);
		return "forward:/";
	}

	@RequestMapping("/blogmainView.do")
	public String blogmainView(BlogVO vo, Model model ) {
		model.addAttribute("blog",blogService.getBlog(vo));
//		System.out.println("blogmainView.do : " + blogService.getBlogList(vo));
		return "blogmain";
	}
	@RequestMapping("/adminView.do")
	public String insertPostView(BlogVO vo, Model model ) {
		model.addAttribute("blog",blogService.getBlog(vo));
		System.out.println("adminView.do : " + blogService.getBlog(vo));
		return "blogadmin_basic";
	}
}
