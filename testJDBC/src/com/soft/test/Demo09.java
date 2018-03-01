package com.soft.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.soft.utils.JdbcUtil;

//�����ƴ�����ʹ��,��ȡ���ݿ���ļ��������ļ�д�����ص��ļ�Ŀ¼
public class Demo09 {
	
	public static void main(String[] args) throws FileNotFoundException, SQLException, IOException {
		ResultSet rs = null ;
		InputStream is =null;
		OutputStream os = null;
		
		String insertSql = "insert into t_user(username,pwd,myInfo) values(?,?,?)";
		String querySql = "select * from t_user where id = ?";
		
		String localFilePath = "D:/testFile/file1/a.png";
		
		//ִ�в���
		boolean flag = insertData(querySql, "png", "png",localFilePath);
		if(flag){
			//�������ɹ����ٽ�������Ĳ���
			rs = queryData(querySql, 1);
		}
		
		while(rs.next()){
			Blob blob = rs.getBlob("myInfo");
			is = blob.getBinaryStream();
			os = new FileOutputStream("D:/testFile/file2/b.png");
			int temp = 0;
			while((temp = is.read()) != -1){
				os.write(temp);
			}
		}
		System.out.println("ִ�гɹ�");
	}
	
	//����һ��sql��䣬����ִ�е������Ĳ������ݿ�Ĳ���
	public static boolean insertData(String sql,Object param1,Object param2,String fileSourcePath){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, param1);
			ps.setObject(2, param2);
			ps.setObject(3, new FileInputStream(fileSourcePath));
			flag = ps.execute();
			System.out.println("���ݲ���ɹ�");
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps);
		}
		return flag;
	}
	
	//���ݿ�Ĳ�ѯ����������һ��sql��䣬������ɵ������ݿ��¼�Ĳ�ѯ
	public static ResultSet queryData(String sql,Object param){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, param);
			rs = ps.executeQuery();
			if(rs != null){
				return rs;
			}
			
			System.out.println("���ݲ�ѯ�ɹ�");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			JdbcUtil.closeConnection(conn, ps, rs);
		}
		return null;
	}
	
	public void testReadBlob() throws FileNotFoundException, SQLException, IOException{
		
		ResultSet rs = null ;
		InputStream is =null;
		OutputStream os = null;
		
		String insertSql = "insert into t_user(username,pwd,myInfo) values(?,?,?)";
		String querySql = "select * from t_user where id = ?";
		
		String localFilePath = "D:/testFile/file1/a.png";
		
		//ִ�в���
		insertData(querySql, "png", "png",localFilePath);
		
		rs = queryData(querySql, 1);
		while(rs.next()){
			Blob blob = rs.getBlob("myInfo");
			is = blob.getBinaryStream();
			os = new FileOutputStream("D:/testFile/file2/b.png");
			int temp = 0;
			while((temp = is.read()) != -1){
				os.write(temp);
			}
		}
		System.out.println("ִ�гɹ�");
		
	}
}
