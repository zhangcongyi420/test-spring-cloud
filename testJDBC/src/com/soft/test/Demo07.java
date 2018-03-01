package com.soft.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.PreparedStatement;
import com.soft.utils.JdbcUtil;

public class Demo07 {
	
	//字符串转时间的格式
	public static long convertStrToTime(String dateStr){
		//将传入的字符串转化为指定的时间格式
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = JdbcUtil.getConnection();
			String sql = " select * from t_user where regTime > ? and regTime < ? order by regTime desc";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			Timestamp start = new Timestamp(convertStrToTime("2015-4-18 8:10:20"));
			Timestamp end = new Timestamp(convertStrToTime("2015-4-18  9:9:10"));
			
			ps.setObject(1, start);
			ps.setObject(2, end);
			
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps);
		}
		
	}
}
