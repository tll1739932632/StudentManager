package com.lulu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lulu.util.JDBCTools;
import com.lulu.vo.UserLogin;

/**
 * user相关的SQL语句 都在这里执行
 * @author 田露露
 * 
 * */

/**
 * 根据用户名和密码查询用户信息
 * 
 * @author Administrator
 *
 */
public class UserDAO {

	//每一个小功能，可以写一个单独的方法
	
	//select * from userlogin where userName=? and password=?
	JDBCTools tools=new JDBCTools();
	
	public UserLogin getUser(String username,String password){
		
		UserLogin user=null;
		String sql="select * from userlogin where userName=? and password=?";
		ResultSet rSet=tools.executeQuery(sql, username,password);
		try {
			while(rSet.next()){
				user = new UserLogin();
				user.setUserID(rSet.getInt("userID"));
				user.setUserName(rSet.getString("userName"));
				user.setPassword(rSet.getString("password"));
				user.setRole(rSet.getInt("role"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}

}
