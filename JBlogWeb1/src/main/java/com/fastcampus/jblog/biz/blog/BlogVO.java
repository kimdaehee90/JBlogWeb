package com.fastcampus.jblog.biz.blog;

import lombok.Data;

@Data
public class BlogVO {
	private int blogId;
	private String title;
	private String tag = null ;
	private int cntDisplayPost = 1;
	private int userId;
	private String status = "운영";
	
	private String searchCondition;
	private String searchKeyword;
}
