package com.vo.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class JdbcConnectionTest {

	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try(Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://13.209.49.227:3306/vo",
				"user",
				"user1!"
				)) {
			
			log.info(connection.toString());
			
			if(connection != null) {
				log.info("DB CONNECTION SUCCESS!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
