package com.soft.call;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.soft.utils.JdbcUtil;

public class TestJdbcCall {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try {
			
			conn = JdbcUtil.getConnection();
			cs = conn.prepareCall("call insert_emp(?,?)");		// 调用存储过程  ;
			cs.setObject(1, "test_call_name1");
			cs.setObject(2, 29);
			boolean hasResult = cs.execute();
			if(hasResult){
				rs = cs.getResultSet();
				while(rs.next()){
					System.out.println(rs.getObject("empname"));
					System.out.println(rs.getObject("age"));
					System.out.println(rs.getObject("salary"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
