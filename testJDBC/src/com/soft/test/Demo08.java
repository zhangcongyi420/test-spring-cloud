package com.soft.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.soft.utils.JdbcUtil;

//测试大文本对象的使用
public class Demo08 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcUtil.getConnection();
			/*String sql = "insert into t_user(username,pwd,myInfo) values(?,?,?)";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, "testBlob1");
			ps.setObject(2, "testBlob1");
			//ps.setClob(3, new FileReader(new File("D:/testFile/a.txt")));
			ps.setClob(3, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("wewewewewe".getBytes()))));
			ps.execute();*/
			
			//从数据库读取blob之类的文件
			ps = (PreparedStatement) conn.prepareStatement("select * from t_user where id = ?");
			ps.setObject(1, 34);
			rs = ps.executeQuery();
			int temp = 0;
			while(rs.next()){
				Clob c = rs.getClob("myInfo");
				Reader r = c.getCharacterStream();
				while((temp = r.read()) != -1){
					System.out.println((char)temp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps);
		}
	}
	
}
