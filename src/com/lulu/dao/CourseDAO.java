package com.lulu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lulu.util.JDBCTools;
import com.lulu.vo.Course;
import com.lulu.vo.Student;

//关于课程表的所有sql语句都在这里
public class CourseDAO {
	JDBCTools tools=new JDBCTools();
	//****查询课程信息*******************************************
	public List<Course> getCourses(int pageNumber,int pageSize){
		int beginIndex = (pageNumber-1)*pageSize; 
		String sql="select c.courseID,c.courseName,c.teacherID,"
				+ "c.courseTime,c.classRoom,c.courseWeek,c.courseType,"
				+ "c.score from course as c order by courseID limit ?,?";
		
		ResultSet rs= tools.executeQuery(sql, beginIndex,pageSize);
		//把rs里的内容放在 list中
		List<Course> list= new ArrayList<Course>();
		
		try {
			while(rs.next()){
				Course course=new Course();
				course.setCourseID(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setTeacherID(rs.getInt(3));
				course.setCourseTime(rs.getString(4));
				course.setClassRoom(rs.getString(5));
				course.setCourseWeek(rs.getInt(6));
				course.setCourseType(rs.getString(7));
				course.setScore(rs.getInt(8));
				
				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	//********搜索查询****************************
	public List<Course> getCourses(int pageNumber,int pageSize,String search){
		int beginIndex = (pageNumber-1)*pageSize; 
		String sql="select c.courseID,c.courseName,c.teacherID,"
				+ "c.courseTime,c.classRoom,c.courseWeek,c.courseType,"
				+ "c.score from course as c where c.courseName like ? order by courseID limit ?,?";
		
		ResultSet rs= tools.executeQuery(sql,"%"+search+"%", beginIndex,pageSize);
		//把rs里的内容放在 list中
		List<Course> list= new ArrayList<Course>();
		
		try {
			while(rs.next()){
				Course course=new Course();
				course.setCourseID(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setTeacherID(rs.getInt(3));
				course.setCourseTime(rs.getString(4));
				course.setClassRoom(rs.getString(5));
				course.setCourseWeek(rs.getInt(6));
				course.setCourseType(rs.getString(7));
				course.setScore(rs.getInt(8));
				
				list.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
		
	}
	
	
	
	//*****删除课程信息*****************************
	public int deletById(int id){
		// 先删除课程
		tools.executeUpdate("delete from selectedcourse where courseID=?", id);
		return tools.executeUpdate("delete from course where courseID=?", id);
	}
	
	//*****添加课程信息******************************
	public int insert(Course c){
		String sql = "insert into course(courseName,teacherID,courseTime,classRoom,courseWeek,courseType,score)"
				+ "values(?,?,?,?,?,?,?)";
		return tools.executeUpdate(sql, c.getCourseName(),
				c.getTeacherID(),c.getCourseTime(),c.getClassRoom(),c.getCourseWeek(),c.getCourseType(),c.getScore());
	}
	
	//*****查询要修改的课程号*********************
	public Course getById(int id){
		String sql = "select * from course where courseID=?";
		// 执行查询语句
		ResultSet rs = tools.executeQuery(sql, id);
		
		Course c = new Course();
		try {
			while(rs.next()){
				// 将结果集 封装成 学生类
				c.setCourseID(rs.getInt(1)); 
				c.setCourseName(rs.getString(2)); 
				c.setTeacherID(rs.getInt(3)); 
				c.setCourseTime(rs.getString(4));
				c.setClassRoom(rs.getString(5));
				c.setCourseWeek(rs.getInt(6));
				c.setCourseType(rs.getString(7));
				c.setScore(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//*****根据课程号更新课程信息****************
	public int updateById(Course c){
		String sql="update course set courseName=?,teacherID=?,"
				+ "courseTime=?,classRoom=?,courseWeek=?,courseType=?,score=? where courseID=?";
		
		return tools.executeUpdate(sql, c.getCourseName(),c.getTeacherID(),c.getCourseTime()
				,c.getClassRoom(),c.getCourseWeek(),c.getCourseType(),c.getScore(),c.getCourseID());
	}
	
	
	
	
	
	
	
	
	
	
	
	//****查询总行数*************************
	public int getTotalCount(){
		String sql = "select count(*) from course ";
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
	
	
	//****************************搜索里的总行数******
	public int getTotalCount(String search){//查询
		String sql = "select count(*) from course   "
				+ "where  course.courseName like ?";
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
