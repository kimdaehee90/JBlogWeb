package com.fastcampus.jblog.biz.post;

import java.sql.Date;

import lombok.Data;

@Data
public class PostVO {
	private int postId;
	private int blogId;
	private int categoryId;
	private String title;
	private String content;
	private Date createdDate;
	
	private int cntDisplayPost;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCntDisplayPost() {
		return cntDisplayPost;
	}

	public void setCntDisplayPost(int cntDisplayPost) {
		this.cntDisplayPost = cntDisplayPost;
	}
}
