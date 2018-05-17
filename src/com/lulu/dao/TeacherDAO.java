package com.lulu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lulu.util.JDBCTools;
import com.lulu.vo.Student;
import com.lulu.vo.Teacher;

public class TeacherDAO {

	
	
	// 引用 JDBC工具类
		JDBCTools tools = new JDBCTools();
		//***********************查询显示老师信息**********
		public List<Teacher> getTeachers(int pageNumber,int pageSize){
			int beginIndex = (pageNumber-1)*pageSize; // 计算开始的索引
			String sql="select s.userID,s.userName,s.sex,s.birthYear,s.degree,s.title,s.grade,c.collegeID "
					+ " from teacher s,college c "
					+ "where s.collegeID=c.collegeID order by userID limit ?,?";
			ResultSet rs = tools.executeQuery(sql,beginIndex,pageSize);
			// 将rs 组装成 list ?
			List<Teacher> list = new ArrayList<Teacher>();
			try {
				while(rs.next()){ // 一次取出一行数据，存入rs
					// 一行数据 保存到 一个Student里面
					Teacher t = new Teacher();
					t.setUserID(rs.getInt(1));
					t.setUserName(rs.getString(2));
					t.setSex(rs.getString(3));
					t.setBirthYear(rs.getDate(4));
					t.setDegree(rs.getString(5));
					t.setTitle(rs.getString(6));
					t.setGrade(rs.getDate(7));
					t.setCollegeID(rs.getInt(8));
					
					list.add(t);// 将当前学生信息 存入列表中
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 返回数据
			return list;
		}
		///******搜索查询********
		public List<Teacher> getTeachers(int pageNumber,int pageSize,String search){
			int beginIndex = (pageNumber-1)*pageSize; // 计算开始的索引
			String sql="select s.userID,s.userName,s.sex,s.birthYear,s.degree,s.title,s.grade,c.collegeID "
					+ " from teacher s,college c "
					+ "where s.collegeID=c.collegeID and s.userName like ? "
					+ "order by userID limit ?,?";
			ResultSet rs = tools.executeQuery(sql,"%"+search+"%",beginIndex,pageSize);
			// 将rs 组装成 list ?
			List<Teacher> list = new ArrayList<Teacher>();
			try {
				while(rs.next()){ // 一次取出一行数据，存入rs
					// 一行数据 保存到 一个Student里面
					Teacher t = new Teacher();
					t.setUserID(rs.getInt(1));
					t.setUserName(rs.getString(2));
					t.setSex(rs.getString(3));
					t.setBirthYear(rs.getDate(4));
					t.setDegree(rs.getString(5));
					t.setTitle(rs.getString(6));
					t.setGrade(rs.getDate(7));
					t.setCollegeID(rs.getInt(8));
					
					list.add(t);// 将当前学生信息 存入列表中
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 返回数据
			return list;
		}
		
		
		
		//*********删除老师新*****************
		public int deletById(int id){
			// 先删除课程
			
			return tools.executeUpdate("delete from teacher where userID=?", id);
		}
		
		//********添加老师**********
		public int insert(Teacher t){
			String sql = "insert into teacher(userName,sex,birthYear,degree,title,grade,collegeID)"
					+ "values(?,?,?,?,?,?,?)";
			return tools.executeUpdate(sql, t.getUserName(),
					t.getSex(),t.getBirthYear(),t.getDegree(),t.getTitle(),t.getGrade(),t.getCollegeID());
		}
		
		//******要修改的教师编号信息********
		public Teacher getById(int id){
			String sql = "select * from teacher where userID=?";
			// 执行查询语句
			ResultSet rs = tools.executeQuery(sql, id);
			
			Teacher tea = new Teacher();
			try {
				while(rs.next()){
					// 将结果集 封装成 学生类
					tea.setUserID(rs.getInt(1));
					tea.setUserName(rs.getString(2));
					tea.setSex(rs.getString(3));
					tea.setBirthYear(rs.getDate(4));
					tea.setDegree(rs.getString(5));
					tea.setTitle(rs.getString(6));
					tea.setGrade(rs.getDate(7));
					tea.setCollegeID(rs.getInt(8));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tea;
		}
		
		//******根据教师编号信息 修改教师信息**********************
		public int updateById(Teacher tea){
			String sql="update teacher set userName=?,sex=?,"
					+ "birthYear=?,degree=?,title=?,grade=?,collegeid=? where userID=?";
			
			return tools.executeUpdate(sql, tea.getUserName(),tea.getSex(),tea.getBirthYear()
					,tea.getDegree(),tea.getTitle(),tea.getGrade(),tea.getCollegeID(),tea.getUserID());
		}
		
		
		
		//******查询总函数*******
		/**
		 * 查询总的行数
		 * @return  总函数
		 */
		public int getTotalCount(){
			String sql = "select count(*) from teacher t,college c "
					+ "where t.collegeID=c.collegeID";
			ResultSet rs = tools.executeQuery(sql);
			int count =0;
			try {
				if(rs.next()){
					count = rs.getInt(1);// 1表示结果集中的第1列
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}
		//*******查询搜索的总行数*********
		public int getTotalCount(String search){
			String sql = "select count(*) from teacher t,college c "
					+ "where t.collegeID=c.collegeID and t.userName like ? ";
			ResultSet rs = tools.executeQuery(sql,"%"+search+"%");
			int count =0;
			try {
				if(rs.next()){
					count = rs.getInt(1);// 1表示结果集中的第1列
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}

}
