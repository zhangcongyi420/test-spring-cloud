package com.soft.concat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soft.utils.JdbcUtil;

//使用Object[]来封装一条记录
public class Demo01 {
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object[]> list = new ArrayList<Object[]>();;
		try {
			
			conn = JdbcUtil.getConnection();
			String sql = "select * from emp where id > ?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 1);
			rs = ps.executeQuery();
			
			while(rs.next()){
				//使用Object[]来封装一条记录
				Object[] objs = new Object[6];
				objs[0] = rs.getObject(1);
				objs[1] = rs.getObject(2);
				objs[2] = rs.getObject(3);
				objs[3] = rs.getObject(4);
				objs[4] = rs.getObject(5);
				objs[5] = rs.getObject(6);
				
				/*System.out.println(rs.getObject(1)+"---"+
								   rs.getObject(2)+"---"+
								   rs.getObject(3)+"---"+
								   rs.getObject(4)+"---"+
								   rs.getObject(5)+"---"+
								   rs.getObject(6));*/
				list.add(objs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps, rs);
		}
		
		System.out.println(list.size());
		System.out.println("-------------");
		System.out.println(list.toString());
		System.out.println("-------------");
		for(Object[] obj: list){
			System.out.println(""+obj[0]+" "+obj[1]+" "+obj[2]);
		}
		
		
	}
}
