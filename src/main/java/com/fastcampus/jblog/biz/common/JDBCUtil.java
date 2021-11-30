package com.fastcampus.jblog.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

	public static Connection getConnection() {
		try {
			String url = "jdbc:h2:tcp://localhost/~/test";
			Class.forName("org.h2.Driver");
			return DriverManager.getConnection(url, "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(PreparedStatement stmt, Connection conn) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			stmt = null;
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			conn = null;
		}
	}

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			rs = null;
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			stmt = null;
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			conn = null;
		}
	}
}
