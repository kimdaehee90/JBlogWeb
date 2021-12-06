package com.fastcampus.jblog.biz.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;
@Repository
public class CategoryDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

private final String CATEGORY_INSERT     = "insert into category(category_id, category_name, display_type, cnt_display_post, description, blog_id) " + 
            								"values ((select nvl(max(category_id), 0) + 1 from category), ?, ?, ?, ?,?) ";
private final String CATEGORY_UPDATE     = "update category set category_name = ?, display_type = ? , cnt_display_post = ?,description = ? where category_id = ?";
private final String CATEGORY_DELETE     = "delete category where category_id = ?";
private final String CATEGORY_GET        = "select * from category";
private final String CATEGORY_GET_LIST     = "select * from category where blog_id = ? order by category_id desc";

	
	
	public void insertCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_INSERT);
			stmt.setString(1, vo.getCategoryName());
			stmt.setString(2, vo.getDisplayType());
			stmt.setInt(3, vo.getCntDisplayPost());
			stmt.setString(4, vo.getDescription());
			stmt.setInt(5, vo.getBlogId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	
	public void updateCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_UPDATE);
			stmt.setString(1, vo.getCategoryName());
			stmt.setString(2, vo.getDisplayType());
			stmt.setInt(3, vo.getCntDisplayPost());
			stmt.setString(4, vo.getDescription());
			stmt.setInt(4, vo.getCategoryId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
	public void deleteCategory(CategoryVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_DELETE);
			stmt.setInt(1, vo.getCategoryId());
			stmt.executeUpdate();
		} catch (SQLException e) {
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
				category.setCategoryId(rs.getInt("CATEGORY_ID"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));
				category.setDisplayType(rs.getString("DISPLAY_TYPE"));
				category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt, conn);
		}
		return category;
	}
	
	public List<CategoryVO> getCategoryList(CategoryVO vo) {
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CATEGORY_GET_LIST);
			stmt.setInt(1, vo.getBlogId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				CategoryVO category = new CategoryVO();
				category.setCategoryId(rs.getInt("CATEGORY_ID"));
				category.setCategoryName(rs.getString("CATEGORY_NAME"));
				category.setDisplayType(rs.getString("DISPLAY_TYPE"));
				category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				category.setDescription(rs.getString("DESCRIPTION"));
				categoryList.add(category);
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,stmt, conn);
		}
		return categoryList;
	}
	
}

