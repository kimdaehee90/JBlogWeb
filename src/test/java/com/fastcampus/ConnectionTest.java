package com.fastcampus;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.jblog.biz.common.JDBCUtil;

@SpringBootTest
class ConnectionTest {

	@Test
	void connectionTest() {
		Connection conn = JDBCUtil.getConnection();
		System.out.println(conn.toString());
		assertNotNull(conn);
	}

}
