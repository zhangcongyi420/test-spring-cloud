package com.soft.test;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.soft.utils.JdbcUtil;

//测试ResultSet结果集的基本用法
public class Demo03 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			conn = JdbcUtil.getConnection();
			String sql = "select id ,username ,pwd from t_user where id > ? order by id desc";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, 2);
			
			rs = ps.executeQuery();
//			System.out.println(rs);
			while(rs.next()){
				System.out.println(rs.getString(1)+"---"+rs.getString(2)+"---"+rs.getString(3));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
