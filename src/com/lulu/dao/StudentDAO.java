package com.lulu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lulu.util.JDBCTools;
import com.lulu.vo.Student;

/**
 * student相关的SQL语句 都在这里执行
 * 
 * @author 韩亮
 *
 */
public class StudentDAO {
	// 引用 JDBC工具类
	JDBCTools tools = new JDBCTools();
	
	public List<Student> getStudents(int pageNumber,int pageSize){
		int beginIndex = (pageNumber-1)*pageSize; // 计算开始的索引
		String sql="select s.userID,s.userName,s.sex,s.birthYear,s.grade,c.collegeName "
				+ " from student s,college c "
				+ "where s.collegeID=c.collegeID order by userID limit ?,?";
		ResultSet rs = tools.executeQuery(sql,beginIndex,pageSize);
		// 将rs 组装成 list ?
		List<Student> list = new ArrayList<Student>();
		try {
			while(rs.next()){ // 一次取出一行数据，存入rs
				// 一行数据 保存到 一个Student里面
				Student s = new Student();
				s.setUserID(rs.getInt(1));
				s.setUserName(rs.getString(2));
				s.setSex(rs.getString(3));
				s.setBirthYear(rs.getDate(4));
				s.setGrade(rs.getDate(5));
				s.setCollegeName(rs.getString(6));
				
				list.add(s);// 将当前学生信息 存入列表中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回数据
		return list;
	}
	
	public List<Student> getStudents(int pageNumber,int pageSize,String search){
		int beginIndex = (pageNumber-1)*pageSize; // 计算开始的索引
		String sql="select s.userID,s.userName,s.sex,s.birthYear,s.grade,c.collegeName "
				+ " from student s,college c "
				+ "where s.collegeID=c.collegeID and s.userName like ?"
				+ " order by userID limit ?,?";
		ResultSet rs = tools.executeQuery(sql,"%"+search+"%",beginIndex,pageSize);
		// 将rs 组装成 list ?
		List<Student> list = new ArrayList<Student>();
		try {
			while(rs.next()){ // 一次取出一行数据，存入rs
				// 一行数据 保存到 一个Student里面
				Student s = new Student();
				s.setUserID(rs.getInt(1));
				s.setUserName(rs.getString(2));
				s.setSex(rs.getString(3));
				s.setBirthYear(rs.getDate(4));
				s.setGrade(rs.getDate(5));
				s.setCollegeName(rs.getString(6));
				
				list.add(s);// 将当前学生信息 存入列表中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回数据
		return list;
	}	
	
	/**
	 * 根据学生id删除 学生信息
	 * @param id
	 * @return
	 */
	public int deletById(int id){
		// 先删除课程
		tools.executeUpdate("delete from selectedcourse where studentID=?", id);
		return tools.executeUpdate("delete from student where userID=?", id);
	}
	
	/**
	 * 添加学生信息
	 * @param s  学生信息
	 * @return   受影响的行数
	 */
	public int insert(Student s){
		String sql = "insert into student(userName,sex,birthYear,grade,collegeID)"
				+ "values(?,?,?,?,?)";
		return tools.executeUpdate(sql, s.getUserName(),
				s.getSex(),s.getBirthYear(),s.getGrade(),s.getCollegeID());
	}
	
	/**
	 * 根据学号查询 学生信息
	 * @param id 学号
	 * @return  对应学号的学生信息
	 */
	public Student getById(int id){
		String sql = "select * from student where userID=?";
		// 执行查询语句
		ResultSet rs = tools.executeQuery(sql, id);
		
		Student stu = new Student();
		try {
			while(rs.next()){
				// 将结果集 封装成 学生类
				stu.setUserID(rs.getInt(1));
				stu.setUserName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setBirthYear(rs.getDate(4));
				stu.setGrade(rs.getDate(5));
				stu.setCollegeID(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stu;
	}
	
	/**
	 * 根据id更新学生信息
	 * @param stu  学生信息
	 * @return    受影响的行数
	 */
	public int updateById(Student stu){
		String sql="update student set userName=?,sex=?,"
				+ "birthYear=?,grade=?,collegeid=? where userID=?";
		
		return tools.executeUpdate(sql, stu.getUserName(),stu.getSex(),stu.getBirthYear()
				,stu.getGrade(),stu.getCollegeID(),stu.getUserID());
	}
	
	/**
	 * 查询总的行数
	 * @return  总函数
	 */
	public int getTotalCount(){
		String sql = "select count(*) from student s,college c "
				+ "where s.collegeID=c.collegeID";
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
	
	public int getTotalCount(String search){//查询
		String sql = "select count(*) from student s,college c "
				+ "where s.collegeID=c.collegeID and s.userName like ?";
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
	
	//改变密码
	public int chanagePassword(String password,String userName){
		return tools.executeUpdate(
				"update userlogin set password=? where userName=?", password,userName);
	}
	
	
}
