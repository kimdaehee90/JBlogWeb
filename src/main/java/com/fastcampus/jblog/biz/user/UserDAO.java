package com.fastcampus.jblog.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@Repository
public class UserDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// SQL 명령어들
	private String USER_GET = "select * from blog_user where id=? and password=?";

	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new UserVO();
				user.setUserId(rs.getInt("USER_ID"));
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return user;
	}

}
