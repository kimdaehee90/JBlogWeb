package com.fastcampus.jblog.biz.post;

import java.sql.Date;

import lombok.Data;

@Data
public class PostVO {
	private int postID;
	private int categoeryID;
	private String title;
	private String content;
	private Date createdDate;
	

}
