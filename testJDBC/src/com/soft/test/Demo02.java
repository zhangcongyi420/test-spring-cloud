package com.soft.test;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.soft.utils.JdbcUtil;

//测试sql注入
public class Demo02 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = JdbcUtil.getConnection();
			//String sql = "insert into t_user(username,pwd,regTime) values(?,?,?)";
			String updateSql = "update t_user set username = 'zcy13' where id = ?";
			ps = (PreparedStatement) conn.prepareStatement(updateSql);
			ps.setInt(1, 6);
			ps.executeUpdate();
			
			int count = ps.executeUpdate();
			System.out.println(count);
			System.out.println("修改成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps);
		}
	}
}
