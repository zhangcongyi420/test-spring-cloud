package com.soft.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.soft.utils.JdbcUtil;

//测试基本的数据库连接
public class Demo01 {
	
//	private static String DRIVER = "com.mysql.jdbc.Driver";
//	private static String URL = "jdbc:mysql://localhost:3306/test";
//	private static String USERNAME = "root";
//	private static String PASSWORD = "root";
	
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//connection = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
			connection = JdbcUtil.getConnection();
			String sql = " select * from t_user where username = ? and pwd = ? ";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "gaoqi");
			ps.setString(2, "123456");
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString(1)+"---"+rs.getString(2)+"---"+rs.getString(3)+"---"+rs.getString(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(connection, ps, rs);
		}
		
	}
}
