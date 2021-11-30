package com.fastcampus.jblog.controller.blog;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;
	
	@RequestMapping("/") 
	public String loginView() {
		// 로그인 화면을 리턴한다.
		return "forward:/index.jsp";
	}
		
	@RequestMapping("/blogMain.do")
	public String blogMain(BlogVO blogVO, CategoryVO categoryVO, PostVO postVO, Model model, HttpSession session) {
		// 1. [내블로그가기] 링크를 눌러 요청한 경우에는 세션에 등록된 블로그 아이디를 이용하여 검색한다.
		if(blogVO.getBlogId() == 0) {
			BlogVO blog = (BlogVO) session.getAttribute("blog");
			blogVO.setBlogId(blog.getBlogId());
			categoryVO.setBlogId(blog.getBlogId());
			postVO.setBlogId(blog.getBlogId());
		}			
		// 2. 블로그 메인 화면에 출력할 블로그 정보를 가져와 Model에 등록한다. 
		BlogVO blog = blogService.getBlog(blogVO);
		model.addAttribute("blog", blog);
		
		//System.out.println(postVO);
		// postId가 0이 아닌 경우는 postController에서 추가 또는 수정 후의 상태로 postId와 CategoryId 삭제 필요
		if(postVO.getTitle() != null) {		
			postVO.setCategoryId(0);
			categoryVO.setCategoryId(0);
		}
		
		if(categoryVO.getCategoryId() != 0 ) {
			postVO.setCategoryId(categoryVO.getCategoryId());			
		}		
	
		// 2. 블로그 메인 화면에 출력할 포스트 목록을 가져와 Model에 등록한다. 
		List<PostVO> postList = postService.getPostList(postVO);
		model.addAttribute("postList", postList);
		
		// 3. 블로그 메인 화면에 출력할 카테고리 목록을 가져와 Model에 등록한다.
		List<CategoryVO> categoryList = categoryService.getCategoryList(categoryVO);
		model.addAttribute("categoryList", categoryList);
		
		CategoryVO category = categoryService.getCategory(categoryVO);
		model.addAttribute("category", category);	

		// 4. 블로그 메인 화면으로 이동한다. 
		return "blogmain";
	}		
	
	@RequestMapping("/getBlogList.do")
	public String getBlogList(BlogVO blogVO, Model model) {
		// 1. 블로그 목록을 검색하여 Model에 등록한다. 
		List<BlogVO> blogList = blogService.getBlogList(blogVO);
		model.addAttribute("blogList", blogList);
		
		// 2. 검색 상태를 유지하기 위해서 Model에 BlogVO 정보를 등록한다. 
		model.addAttribute("search", blogVO);
		return "forward:/";
	}
	
	@RequestMapping("/insertBlog.do") 
	public String insertBlog(BlogVO blogVO, CategoryVO categoryVO, HttpSession session) {
		// 1. 블로그 등록을 처리한다. 
		blogService.insertBlog(blogVO);
		
		// 2. 방금 등록한 블로그를 검색하여 인덱스 페이지에서 
		// 블로그 유무를 판단할 수 있도록 세션에 등록한다.
		BlogVO blog = blogService.getBlog(blogVO);
		session.setAttribute("blog", blog);
		
		// 3. "미분류" 카테고리를 생성된 블로그에 등록한다. 
		// blogId를 제외한 나머지 값들을 설정한다. 
		categoryVO.setCategoryName("미분류");
		categoryVO.setCntDisplayPost(5);
		categoryVO.setDescription("기본 카테고리입니다.");
		categoryVO.setDisplayType("MIXED"); // TITLE 아니면 MIXED 둘 중 하나
		categoryService.insertCategory(categoryVO);
		
		// 4. 블로그 등록 후에는 인덱스 페이지로 이동한다. 
		return "forward:/";
	}
	
	@RequestMapping("/insertBlogView.do") 
	public String insertBlogView() {
		// 블로그 등록 화면으로 이동한다. 
		return "blogcreate";
	}
	
	@RequestMapping("/adminBasic.do")
	public String adminBasic() {
		// adminBasic 화면으로 이동한다.			
		return "adminBasic";
	}
	
	@RequestMapping("/updateBlog.do")
	public String updateBlog(BlogVO blogVO, HttpSession session) {
		// 1. 블로그를 수정 처리한다.
		blogService.updateBlog(blogVO);		
		System.err.println(blogVO);
		// 2. 수정된 블로그를 다시 검색하여 세션을 갱신한다.
		// 그리고 나서 블로그 메인 페이지로 이동한다.
		BlogVO blog = blogService.getBlog(blogVO);
		session.setAttribute("blog", blog);	

	
		return "forward:/blogMain.do";
	}
	
	@RequestMapping("/blogDeleteRequest.do")
	public String blogDeleteRequest (BlogVO blogVO) {
		BlogVO blog = blogService.getBlog(blogVO);
		System.out.println(blog);	
		if(blog.getStatus().equals("운영")) {
			blog.setStatus("삭제요청");
		} else if (blog.getStatus().equals("삭제요청")) {
			blog.setStatus("운영");
		}		
		System.out.println(blog);	
		
		blogService.blogDeleteRequest(blog);			
			
		return "forward:/logout.do";
	}
	
	@RequestMapping("/deleteBlog.do")
	public String deleteBlog (BlogVO blogVO, Model model) {
		// 1. 블로그 목록을 검색하여 Model에 등록한다. 
		List<BlogVO> blogList = blogService.getBlogList(blogVO);
		model.addAttribute("blogList", blogList);	
		
		blogService.deleteBlog(blogVO.getBlogId());
		
		// 2. 검색 상태를 유지하기 위해서 Model에 BlogVO 정보를 등록한다. 
		model.addAttribute("search", blogVO);
		
		return "forward:/getBlogList.do";
	}
	
	
}
