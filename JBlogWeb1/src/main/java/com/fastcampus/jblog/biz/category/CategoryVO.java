package com.fastcampus.jblog.biz.category;

import java.sql.Date;

import lombok.Data;

@Data
public class CategoryVO {
	private int blogId;
	private int categoryId;
	private String categoryName;
	private String displayType;
	private int cntDisplayPost;
	private String description;
	private Date createdDate;
	private Date modifiedDate;
}
