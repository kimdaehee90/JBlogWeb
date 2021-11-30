package com.fastcampus.jblog.biz.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class PostDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// SQL 명령어들
	private String POST_LIST 			= "select * from post where blog_id=? order by post_id desc";
	private String POST_LIST_CATEGORY 	= "select * from post where blog_id=? and category_id=? order by post_id desc";
	private String POST_DELETE 			= "delete post where post_id=?";
	private String POST_INSERT 			= "insert into post(post_id, blog_id, category_id, title, content, created_date) values((select nvl(max(post_id), 0) + 1 from post), ?, ?, ?, ?, sysdate) ";
	private String POST_GET 			= "select * from post where post_id=?  ";
	private String POST_UPDATE 			= "update post set category_id=?, title=?, content=? where post_id=? ";
		
	public void insertPost(PostVO vo) {		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_INSERT);
			stmt.setInt(1, vo.getBlogId());
			stmt.setInt(2, vo.getCategoryId());
			stmt.setString(3, vo.getTitle());
			stmt.setString(4, vo.getContent());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public List<PostVO> getPostList(PostVO vo) {
		List<PostVO> postList = new ArrayList<PostVO>();
		try {
			conn = JDBCUtil.getConnection();
			if (vo.getCategoryId() != 0) {
				stmt = conn.prepareStatement(POST_LIST_CATEGORY);
				stmt.setInt(1, vo.getBlogId());
				stmt.setInt(2, vo.getCategoryId() );
			} else {
				stmt = conn.prepareStatement(POST_LIST);
				stmt.setInt(1,vo.getBlogId());
			}	
						
			rs = stmt.executeQuery();
			while(rs.next()) {
				PostVO post = new PostVO();
				post.setBlogId(rs.getInt("BLOG_ID"));
				post.setCategoryId(rs.getInt("CATEGORY_ID"));
				post.setContent(rs.getString("CONTENT"));
				post.setPostId(rs.getInt("POST_ID"));
				post.setCreatedDate(rs.getDate("CREATED_DATE"));
				post.setTitle(rs.getString("TITLE"));			
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return postList;
	}
	
	public void deletePost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_DELETE);
			stmt.setInt(1, vo.getPostId());
			stmt.executeUpdate(); // SQL --> H2 전송
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
	}

	
	public PostVO getPost(PostVO vo) {
		PostVO post = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_GET);
			stmt.setInt(1, vo.getPostId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				post = new PostVO();
				post.setPostId(rs.getInt("POST_ID"));
				post.setBlogId(rs.getInt("BLOG_ID"));
				post.setCategoryId(rs.getInt("CATEGORY_ID"));
				post.setTitle(rs.getString("TITLE"));
				post.setContent(rs.getString("CONTENT"));
				post.setCreatedDate(rs.getDate("CREATED_DATE"));		
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return post;

	}

	public void updatePost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_UPDATE);
			stmt.setInt(1, vo.getCategoryId());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getContent());
			stmt.setInt(4, vo.getPostId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	

}
