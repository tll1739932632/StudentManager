/**
 * 
 */
package com.lulu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理项目中所有的SQL语句
 * @author Admin
 *
 */
public class JDBCTools {
	String url = "jdbc:mysql://localhost:3306/tesuad?characterEncoding=utf8";
	String user = "root";
	String password = "123456";
	
	public JDBCTools(){// 类初始化的时候直接就加载驱动
		// 1. 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 2.写一个方法建立连接，目的为了让连接更自由
	public Connection getConnection(){
		Connection conn=null;
		try {
			 conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("建立连接成功!");
		return conn;
	}

	// 3. 写一个方法 执行所有的select语句
	// select * from teacher
	// select * from teacher where tid = ?
	// select * from teacher where tid = ? and tage=?
	/**
	 * @param sql     需要被执行的SQL语句
	 * @param params  可变参数，表示 SQL语句中 需要用到的参数的列表
	 * @return
	 */
	public  ResultSet  executeQuery(String sql, Object ... params){
		ResultSet rs=null; // 初始化变量
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 往ps对象中设置参数，但是不确定sql中有多少个问号
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			// 执行SQL语句并返回
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	/**
	 * 执行所有的 insert update  delete SQL语句
	 * @param sql     被执行的SQL语句，可以带 ？
	 * @param params  SQL语句中参数的列表，个数必须跟SQL语句中？的个数相等
	 * @return  SQL语句执行的结果 表示受影响的行数
	 */
	public int executeUpdate(String sql, Object... params){
		int count =0;
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
            // 执行sql语句 并接收结果
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
