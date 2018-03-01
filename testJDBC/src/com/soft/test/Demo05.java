package com.soft.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.soft.utils.JdbcUtil;

//测试事务的基本概念和用法
public class Demo05 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			conn = JdbcUtil.getConnection();
			
			conn.setAutoCommit(false);
			String sql = "insert into t_user(username,pwd) values (?,?)";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, "kobi");
			ps.setObject(2, "123456");
			ps.execute();
			
			conn.commit();
			System.out.println("执行成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			JdbcUtil.closeConnection(conn, ps, rs);
		}
		
	}
	
}
