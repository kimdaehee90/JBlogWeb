package com.fastcampus.jblog.biz.category;

import lombok.Data;

@Data
public class CategoryVO {
	private int blogId;
	private int categoryId;
	private String categoryName;
	private String displayType;
	private int cntDisplayPost;
	private String description;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public int getCntDisplayPost() {
		return cntDisplayPost;
	}
	public void setCntDisplayPost(int cntDisplayPost) {
		this.cntDisplayPost = cntDisplayPost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
