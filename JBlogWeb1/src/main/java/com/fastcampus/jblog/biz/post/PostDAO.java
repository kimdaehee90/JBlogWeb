package com.fastcampus.jblog.biz.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class PostDAO{
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private final String POST_INSERT = "insert into post(post_Id, category_Id, title, content) " + 
										"values ((select nvl(max(seq), 0) + 1 from post), ?, ?, ?)";
	private final String POST_UPDATE = "update post set title = ?, content = ? where postId = ?";
	private final String POST_DELETE = "delete post where postId = ?";
	private final String POST_GET 	 = "select * from post where post_Id = ?";
//	private final String POST_LIST = "select * from post where category_Id = ? order by category_Id desc";
	private final String POST_LIST = "SELECT p.POST_ID, p.CATEGORY_ID, p.TITLE, p.CONTENT, p.CREATED_DATE, bu.user_name from post p, blog_user bu WHERE category_id in (SELECT c.category_id FROM category c WHERE c.blog_id = 1) AND bu.user_id = 1";
	
	public void insertPost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_INSERT);
			stmt.setInt(1, vo.getCategoeryID());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	
	
	public void updatePost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getPostID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
	
	public void deletePost(PostVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_DELETE);
			stmt.setInt(1, vo.getPostID());
			stmt.executeUpdate();
		} catch (SQLException e) {
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
			stmt.setInt(1, vo.getPostID());
			rs = stmt.executeQuery();
			if(rs.next()) {
				post = new PostVO();
				post.setPostID(rs.getInt("POST_ID"));
				post.setCategoeryID(rs.getInt("CATEGORY_ID"));
				post.setTitle(rs.getString("TITLE"));
				post.setContent(rs.getString("CONTENT"));
				post.setCreatedDate(rs.getDate("CREATED_DATE"));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt, conn);
		}
		return post;
	}
	
	public List<PostVO> getPostList(BlogVO vo) {
		List<PostVO> postList = new ArrayList<PostVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(POST_LIST);
			stmt.setInt(1, vo.getBlogId());
			stmt.setInt(2, vo.getBlogId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				PostVO post = new PostVO();
				post.setPostID(rs.getInt("POST_ID"));
				post.setCategoeryID(rs.getInt("CATEGORY_ID"));
				post.setTitle(rs.getString("TITLE"));
				post.setContent(rs.getString("CONTENT"));
				post.setCreatedDate(rs.getDate("CREATED_DATE"));
				postList.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return postList;
	}

}
