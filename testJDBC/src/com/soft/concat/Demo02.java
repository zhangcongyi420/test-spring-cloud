package com.soft.concat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soft.pojo.Emp;
import com.soft.utils.JdbcUtil;

//使用map进行封装
public class Demo02 {
	
	//用map封装单条记录
	public static Map getOneMapData(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement("select * from emp where id = 2");
//			ps.setObject(1, 1);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()){
				for(int i=0; i<count; i++){
					String columnName = rsmd.getColumnName(i+1);
					Object columnValue = rs.getObject(columnName);
					if(columnValue == null){  
						columnValue = "";  
	                } 
					map.put(columnName, columnValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps, rs);
		}
		return map;
	}
	
	//用list封装多条记录
	public static List getListData(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(" select * from emp where id > ? ");
			ps.setObject(1, 1);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();  
				for(int i=0; i<count; i++){
					String columnName = rsmd.getColumnName(i+1);
					Object columnValue = rs.getObject(columnName);
					if(columnValue == null){  
						columnValue = "";  
	                } 
					map.put(columnName, columnValue);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.closeConnection(conn, ps, rs);
		}
		return list;
	}
	
	//用javabean进行数据封装
	public static Emp getEmpData(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Emp emp = new Emp();
		try {
			
			conn = JdbcUtil.getConnection();
			String sql = "select * from emp where id = 2";
			ps = conn.prepareStatement(sql);
//			ps.setObject(1, 1);
			rs = ps.executeQuery();
			while(rs.next()){
				emp.setAge(rs.getInt("age"));
				emp.setBirthday(rs.getDate("birthday"));
				emp.setEmpname(rs.getString("empname"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setDeptId(rs.getInt("deptId"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	//用list封装javaBean
	public static List<Emp> getDataList(String sql){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Emp> list = new ArrayList<Emp>();
		
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 1);
			rs = ps.executeQuery();
			Emp emp = new Emp();
			while(rs.next()){
				emp.setAge(rs.getInt("age"));
				emp.setBirthday(rs.getDate("birthday"));
				emp.setEmpname(rs.getString("empname"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setDeptId(rs.getInt("deptId"));
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void main(String[] args) {
//		System.out.println(getOneMapData());
//		System.out.println("---------");
//		System.out.println(getListData());
//		System.out.println(getEmpData().getEmpname());
//		System.out.println(getDataList().get(0).getSalary());
//		System.out.println(getDataList().size());
	}
}
