package com.fastcampus.jblog.biz.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class CategoryDAO {

	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// SQL 명령어들
	private String CATEGORY_INSERT 	= "insert into category(blog_id, category_id, category_name, display_type, description, cnt_display_post) values(?, (select nvl(max(category_id), 0) + 1 from category), ?, ?, ?, ?)";
	private String CATEGORY_LIST	= "select * from category where blog_id=? order by category_id asc";
	private String CATEGORY_GET 	= "select * from category where category_id=?  ";
	private String CATEGORY_UPDATE	= "update category set category_name=?, display_type=?, cnt_display_post=?, description=? where category_id=?";
	private String CATEGORY_DELETE	= "delete category where category_id=?";
	
	public void insertCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_INSERT);
			stmt.setInt(1, vo.getBlogId());
			stmt.setString(2, vo.getCategoryName());
			stmt.setString(3, vo.getDisplayType());
			stmt.setString(4, vo.getDescription());
			stmt.setInt(5, vo.getCntDisplayPost());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public CategoryVO getCategory(CategoryVO vo) {
		CategoryVO category = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_GET);
			stmt.setInt(1, vo.getCategoryId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				category = new CategoryVO();
				category.setBlogId(rs.getInt("BLOG_ID"));
				category.setCategoryId(rs.getInt("CATEGORY_ID"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));
				category.setDisplayType(rs.getString("DISPLAY_TYPE"));
				category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}				
		return category;
	}

	public List<CategoryVO> getCategoryList(CategoryVO vo) {
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_LIST);
			stmt.setInt(1, vo.getBlogId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setBlogId(rs.getInt("BLOG_ID"));
				category.setCategoryId(rs.getInt("CATEGORY_ID"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));
				category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
				category.setDisplayType(rs.getString("DISPLAY_TYPE"));
				categoryList.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return categoryList;
	}
	
	public void updateCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_UPDATE);
			stmt.setString(1, vo.getCategoryName());
			stmt.setString(2, vo.getDisplayType());
			stmt.setInt(3, vo.getCntDisplayPost());
			stmt.setString(4, vo.getDescription());			
			stmt.setInt(5,  vo.getCategoryId());
			
			stmt.executeUpdate();		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	public void deleteCategory(int categoryId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_DELETE);
			stmt.setInt(1, categoryId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}	

}
