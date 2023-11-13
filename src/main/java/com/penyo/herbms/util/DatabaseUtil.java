package com.penyo.herbms.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * 初始化数据库。
 * 
 * @author Penyo
 */
public class DatabaseUtil {
	/** 公共驱动 */
	private static String driver;
	/** 公共地址 */
	private static String url;
	/** 公共账户的用户名 */
	private static String username;
	/** 公共账户的密码 */
	private static String password;

	static {
		Properties properties = new Properties();
		InputStream inputStream = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			properties.load(inputStream);
			driver = properties.getProperty("driverClass");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取连接实例。
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 尝试关闭临时对象。
	 */
	public static void close(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 尝试关闭临时对象。
	 */
	public static void close(PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
