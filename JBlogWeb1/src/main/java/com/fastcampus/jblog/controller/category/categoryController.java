package com.fastcampus.jblog.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;

@Controller
public class categoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/adminCategoryView.do")
	public String adminCategoryView(BlogVO vo, Model model ) {
		model.addAttribute("blog",blogService.getBlog(vo));
		System.out.println("adminCategoryView.do : " + blogService.getBlog(vo));
		return "forward:getCategoryList.do";
	}
	
	@RequestMapping("/insertCategory.do")
	public String insertCategory(CategoryVO cvo,BlogVO vo, Model model ) {
		categoryService.insertCategory(cvo);
		model.addAttribute("blog",blogService.getBlog(vo));
		model.addAttribute("categoryList",categoryService.getCategoryList(cvo));
		System.out.println("insertCategory.do : " + blogService.getBlog(vo));
		System.out.println("insertCategory.do : " + categoryService.getCategoryList(cvo));
		return "blogadmin_category";
	}
	
	@RequestMapping("/getCategoryList.do")
	public String getCategoryList(CategoryVO cvo,BlogVO vo, Model model ) {
		model.addAttribute("blog",blogService.getBlog(vo));
		model.addAttribute("categoryList",categoryService.getCategoryList(cvo));
		System.out.println("blogmainView.do : " + categoryService.getCategoryList(cvo));
		return "blogadmin_category";
	}
}

