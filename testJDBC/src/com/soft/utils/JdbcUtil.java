package com.soft.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

public class JdbcUtil {
	
	static Properties props = null;
	
	//���԰�����ȡ�ʹ�����Դ�ļ��е���Ϣ   ---- ����JDBCUtil���ʱ�����
	static{
		props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ά���������ݿⷽ��
	public static Connection getConnection(){
		try {
			Class.forName(props.getProperty("mysqlDriver"));
			return DriverManager.getConnection(
												props.getProperty("mysqlURL"), 
												props.getProperty("mysqlUser"), 
												props.getProperty("mysqlPwd")
											  );
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//�ر����ݿ�ķ���(PreparedStatement)
	public static void closeConnection(Connection conn,PreparedStatement ps,ResultSet rs){
		
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(ps != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(rs != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ر����ݿ�ķ���(Statement)
	public static void closeConnection(Connection conn,Statement st,ResultSet rs){
		
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(st != null){
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(rs != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ر����ݿ�ķ���(ȱ��ResultSet)
	public static void closeConnection(Connection conn,PreparedStatement ps){
		
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(ps != null){
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ر����ݿ�ķ���(ȱ��ResultSet)
	public static void closeConnection(Connection conn,Statement st){
		
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(st != null){
				st.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	//�ر����ݿ�ķ���(Connection)
	public static void closeConnection(Connection conn){
		
		try {
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
