package com.fastcampus.jblog.biz.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class BlogDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private String BLOG_INSERT = "insert into blog(blog_id,title, tag, cnt_display_post, status,user_id) values (?,?,?,?,?,?)";
	private String BLOG_UPDATE     = "update blog set title = ?, tag = ?, cnt_display_post = ? where blog_id = ?";
	private String BLOG_GET = "select * from blog" ;
	private String BLOG_GET_List = "select b.blog_id, b.title, b.status, u.user_name from blog b, blog_user u where b.blog_id = u.user_id";
	private String BLOG_GET_List_TITLE = "select b.blog_id, b.title, b.status, u.user_name from blog b, blog_user u where b.blog_id = u.user_id and title like '%'||?||'%' ";
	private String BLOG_GET_List_TAG = "select b.blog_id, b.title, b.status, u.user_name from blog b, blog_user u where b.blog_id = u.user_id and tag like '%'||?||'%'";
	private String BLOG_GET_List_BLOGGER = "select b.blog_id, b.title, b.status, u.user_name from blog b, blog_user u where b.blog_id = u.user_id and u.user_name like '%'||?||'%'";
	private String BLOG_DELETE = "delete blog where blog_id = ?";
	
	public void insertBlog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_INSERT);
			stmt.setInt(1, vo.getBlogId());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getTag());
			stmt.setInt(4, vo.getCntDisplayPost());
			stmt.setString(5, vo.getStatus());
			stmt.setInt(6, vo.getBlogId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
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
	
	public BlogVO getBlog(BlogVO vo) {
		BlogVO blog = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_GET);
//			stmt.setInt(1, vo.getUserId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				blog = new BlogVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blog.setUserId(rs.getInt("USER_ID"));
				blog.setStatus(rs.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return blog;
	}
	
	public List<Map<String, String>> getBlogList(BlogVO vo) {
		List<Map<String, String>> blogList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			if(vo.getSearchCondition().equals("TITLE")) {
				stmt = conn.prepareStatement(BLOG_GET_List_TITLE);
			}else if(vo.getSearchCondition().equals("TAG")) {
				stmt = conn.prepareStatement(BLOG_GET_List_TAG);
			}else if(vo.getSearchCondition().equals("BLOGGER")) {
				stmt = conn.prepareStatement(BLOG_GET_List_BLOGGER);
			}
			stmt.setString(1, vo.getSearchKeyword());
			rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String, String> blog = new HashMap<String, String>();
				blog.put("BLOG_ID", rs.getString("BLOG_ID"));
				blog.put("TITLE", rs.getString("TITLE"));
//				blog.put("TAG", rs.getString("TAG"));
				blog.put("USER_NAME", rs.getString("USER_NAME"));
				blog.put("STATUS", rs.getString("STATUS"));
				blogList.add(blog);
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt, conn);
		}
		return blogList;
	}
	public void deleteblog(BlogVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_DELETE);
			stmt.setInt(1, vo.getBlogId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
}