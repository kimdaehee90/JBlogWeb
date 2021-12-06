package com.fastcampus.jblog.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;

@Controller
public class PostController {
	
	@Autowired
	private PostService postServive;
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping("/insertPost.do")
	public String insertPost(BlogVO bvo,PostVO vo, Model model) {
		postServive.insertPost(vo);
		model.addAttribute("blog",blogService.getBlog(bvo));
		model.addAttribute("post",postServive.getPostList(bvo));
		System.out.println("/insertPost.do : " + blogService.getBlog(bvo));
		System.out.println("/insertPost.do : " + blogService.getBlog(bvo));
		return "blogmain";
	}
	@RequestMapping("/getPostList.do")
	public String getPostList(BlogVO bvo,PostVO vo,Model model) {
		model.addAttribute("postList",postServive.getPostList(bvo));
		System.out.println("getPostList.do : " + postServive.getPostList(bvo));
		return "blogmain";
	}
	@RequestMapping("/createPostView.do")
	public String adminCategoryView(BlogVO vo,Model model) {
		model.addAttribute("blog",blogService.getBlog(vo));
		System.out.println("acreatePostView.do: " + blogService.getBlog(vo));
		return "adminPost";
	}

}
