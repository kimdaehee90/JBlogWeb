package com.fastcampus.jblog.controller.category;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/adminCategory.do")
	public String adminCategory(CategoryVO categoryVO, HttpSession session, Model model) {
		// adminCategory.jsp 페이지 뷰 이동	
		if(categoryVO.getBlogId() == 0) {
			BlogVO blog = (BlogVO) session.getAttribute("blog");
			categoryVO.setBlogId(blog.getBlogId());
		}
						
		List<CategoryVO> categoryList = categoryService.getCategoryList(categoryVO);
		model.addAttribute("categoryList", categoryList);
	
		model.addAttribute("categoryFlag", "insertCategory");
			
		return "adminCategory";
	}
	
	@RequestMapping("/insertCategory.do")
	public String insertCategory(CategoryVO categoryVO, Model model) {
		
		categoryService.insertCategory(categoryVO);
		
		model.addAttribute("categoryFlag", "insertCategory");
					
		return "forward:/adminCategory.do";
		
	}
	
	@RequestMapping("updateCategoryView.do") 
	public String updateCategoryView(CategoryVO categoryVO, Model model) {
		// 카테고리 목록 가져오기
		List<CategoryVO> categoryList = categoryService.getCategoryList(categoryVO);
		model.addAttribute("categoryList", categoryList);
	
		// 사용자가 클릭한 카테고리 하나를 조회하여 Model에 등록한다.
		CategoryVO category = categoryService.getCategory(categoryVO);
		model.addAttribute("category", category);		
		
		// 카테고리 목록화면에서 수정
		model.addAttribute("categoryFlag", "updateCategory");
			
		return "adminCategory";	
	}
	
	
	@RequestMapping("updateCategory.do")
	public String updateCategory(CategoryVO categoryVO, Model model) {
		// 1. 카테고리 업데이트
		categoryService.updateCategory(categoryVO);	
		
		// 2. 카테고리 플래그 변경
		model.addAttribute("categoryFlag", "insertCategory");
		return "forward:/adminCategory.do";
		
	}
	
	@RequestMapping("deleteCategory.do")
	public String deleteCategory(CategoryVO categoryVO) {
		categoryService.deleteCategory(categoryVO.getCategoryId());
		return "forward:/adminCategory.do";
	}
	
}
