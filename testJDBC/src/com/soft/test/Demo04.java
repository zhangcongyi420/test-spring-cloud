package com.soft.test;

import java.sql.Connection;
import java.sql.Statement;

import com.soft.utils.JdbcUtil;

//����������ķ���
public class Demo04 {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = JdbcUtil.getConnection();
			//�����ֶ��ύ����
			conn.setAutoCommit(false);
			st = conn.createStatement();
			
			long start = System.currentTimeMillis();
			
			for(int i=0;i<20;i++){
				st.addBatch("insert into t_user (username,pwd,regTime) values ('zcy"+i+"','123456','2012-12-21')");
			}
			st.executeBatch();
			
			conn.commit();
			System.out.println("������ɹ�");
			
			long end = System.currentTimeMillis();
			
			System.out.println("���ĵ�ʱ����:" + (end-start) + "����");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
