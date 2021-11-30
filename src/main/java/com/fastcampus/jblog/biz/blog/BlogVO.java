package com.fastcampus.jblog.biz.blog;

import lombok.Data;

@Data
public class BlogVO {
	private int blogId;
	private String title;
	private String tag;
	private int cntDisplayPost;
	private String status;
	private int userId;
	
	// 검색을 위한 변수
	private String searchCondition;
	private String searchKeyword;
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCntDisplayPost() {
		return cntDisplayPost;
	}
	public void setCntDisplayPost(int cntDisplayPost) {
		this.cntDisplayPost = cntDisplayPost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userName;
}
