package com.fastcampus.jblog.biz.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class BlogDAO {
	
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// SQL 명령어들
	private String BLOG_INSERT 		 	= "insert into blog(blog_id, title, user_id) values(?, ?, ?)";
	private String BLOG_GET 		 	= "select * from blog where blog_id=?";
	private String BLOG_LIST_TITLE   	= "select b.blog_id, b.cnt_display_post, b.status, b.title, b.tag, u.user_name from blog b, blog_user u where b.user_id=u.user_id and b.title like '%'||?||'%' order by b.title";
	private String BLOG_LIST_TAG     	= "select b.blog_id, b.cnt_display_post, b.status, b.title, b.tag, u.user_name from blog b, blog_user u where b.user_id=u.user_id and b.tag like '%'||?||'%' order by b.title";
	private String BLOG_LIST_BLOGGER 	= "select b.blog_id, b.cnt_display_post, b.status, b.title, b.tag, u.user_name from blog b, blog_user u where b.user_id=u.user_id and u.user_name like '%'||?||'%' order by b.title";
	private String BLOG_UPDATE		 	= "update blog set title=?, tag=?, cnt_display_post=? where blog_id=?";
	private String BLOG_STATUS_CHANGE 	= "update blog set status=? where blog_id=?";
	private String BLOG_DELETE			= "delete blog where blog_id=? ";
	
	
	// CRUD 기능의 메소드
	public List<BlogVO> getBlogList(BlogVO vo) {
		List<BlogVO> blogList = new ArrayList<BlogVO>();
		try {
			conn = JDBCUtil.getConnection();
			if (vo.getSearchCondition().equals("TITLE")) {
				stmt = conn.prepareStatement(BLOG_LIST_TITLE);
			} else if (vo.getSearchCondition().equals("TAG")) {
				stmt = conn.prepareStatement(BLOG_LIST_TAG);
			} else if (vo.getSearchCondition().equals("BLOGGER")) {
				stmt = conn.prepareStatement(BLOG_LIST_BLOGGER);
			}
			stmt.setString(1, vo.getSearchKeyword());
			rs = stmt.executeQuery();
			while (rs.next()) {
				BlogVO blog = new BlogVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setTag(rs.getString("TAG"));
				blog.setUserName(rs.getString("USER_NAME"));
				blogList.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blogList;
	}
	
	public void insertBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_INSERT);
			stmt.setInt(1, vo.getBlogId());
			stmt.setString(2, vo.getTitle());
			stmt.setInt(3, vo.getUserId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public BlogVO getBlog(BlogVO vo) {
		BlogVO blog = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_GET);
			stmt.setInt(1, vo.getBlogId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				blog = new BlogVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blog.setStatus(rs.getString("STATUS"));
				blog.setTag(rs.getString("TAG"));
				blog.setUserId(rs.getInt("USER_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return blog;
	}

	public void updateBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getTag());
			stmt.setInt(3, vo.getCntDisplayPost());
			stmt.setInt(4, vo.getBlogId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
	}

	public void blogDeleteRequest(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_STATUS_CHANGE);
			stmt.setString(1, vo.getStatus());
			stmt.setInt(2, vo.getBlogId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}	
		
	}

	public void deleteBlog(int blogId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_DELETE);
			stmt.setInt(1, blogId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}		
	}
 }
