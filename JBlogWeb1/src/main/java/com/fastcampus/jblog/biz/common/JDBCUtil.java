package com.fastcampus.jblog.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// JDBC 1단계 : 드라이버 로딩 
			DriverManager.registerDriver(new org.h2.Driver());
//			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa",""); 
				} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void close(Statement stmt, Connection conn) {
		try {
			stmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
			
		try {
			conn.close(); // 고속버스를 먼저 치우고 고속도로를 없앤다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		// JDBC 5단계 : Connection 종료
		try {
			rs.close(); // 고속버스를 먼저 치우고 고속도로를 없앤다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
		
		try {
			stmt.close(); // 고속버스를 먼저 치우고 고속도로를 없앤다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		
		try {
			conn.close(); // 고속버스를 먼저 치우고 고속도로를 없앤다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
}
}
